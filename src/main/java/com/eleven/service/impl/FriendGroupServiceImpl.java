package com.eleven.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eleven.common.Result;
import com.eleven.common.ResultFactory;
import com.eleven.entity.FriendGroup;
import com.eleven.entity.LoginUser;
import com.eleven.mapper.FriendGroupMapper;
import com.eleven.mapper.MyFriendMapper;
import com.eleven.service.FriendGroupService;
import com.eleven.util.SecurityUtils;
import com.eleven.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhaojinhui
 * @date 2021/3/17 19:32
 * @apiNote
 */
@Service
public class FriendGroupServiceImpl extends ServiceImpl<FriendGroupMapper, FriendGroup> implements FriendGroupService {

    @Autowired
    private FriendGroupMapper friendGroupMapper;
    @Autowired
    private MyFriendMapper myFriendMapper;

    @Autowired
    private SnowFlake snowFlake;


    /**
     * 创建好友分组
     *
     * @param friendGroup
     * @return
     */
    @Override
    public Result createFriendGroup(FriendGroup friendGroup) {
        List<FriendGroup> friendGroupList = new ArrayList<>();
        friendGroupList.add(friendGroup);
        return createFriendGroupBat(friendGroupList);
    }

    /**
     * 批量创建好友分组
     * @param friendGroupList
     * @return
     */
    @Override
    public Result createFriendGroupBat(List<FriendGroup> friendGroupList) {
        List<FriendGroup> insertBat = new ArrayList<>();
        LoginUser userInfo = SecurityUtils.getUserInfo();
        for (FriendGroup group : friendGroupList) {
            group.setId(snowFlake.nextId());
            group.setGroupTotal(0);
            group.setUserId(userInfo.getAccount());
            insertBat.add(group);
        }
        if(CollUtil.isNotEmpty(insertBat)){
            friendGroupMapper.insertBat(insertBat);
        }
        return null;
    }

    @Override
    public Result queryMyGroupList(FriendGroup friendGroup) {
        LoginUser userInfo = SecurityUtils.getUserInfo();
        friendGroup.setUserId(userInfo.getAccount());
        List<FriendGroup> friendGroupList = friendGroupMapper.getGroupByLike(friendGroup);
        return ResultFactory.success(friendGroupList);
    }

    @Override
    public Result updateFriendGroup(FriendGroup friendGroup) {
        return ResultFactory.success(friendGroupMapper.updateById(friendGroup));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Result delFriendGroup(FriendGroup friendGroup) {
        LoginUser userInfo = SecurityUtils.getUserInfo();
        //删除好友分组
        friendGroup.setDelFlag("0");
        friendGroupMapper.updateById(friendGroup);
        FriendGroup myGroup = friendGroupMapper.queryMyGroup(userInfo.getAccount());
        Integer effectRow = myFriendMapper.updateGroupToMy(friendGroup.getId(), myGroup.getId());
        myGroup.setGroupTotal(effectRow);
        List<FriendGroup> list = new ArrayList<>();
        list.add(friendGroup);
        myFriendMapper.updateToMyFriendGroup(list, myGroup.getId());
        friendGroupMapper.updateMyGroupNum(myGroup);
        return ResultFactory.success("删除成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public Result updateFriendGroupList(List<FriendGroup> friendGroupList) {
        LoginUser userInfo = SecurityUtils.getUserInfo();
        FriendGroup friendGroup = new FriendGroup();
        friendGroup.setUserId(userInfo.getAccount());
        List<FriendGroup> queryList = friendGroupMapper.getGroupByLike(friendGroup);

        //获取新创建的群组信息
        List<FriendGroup> createList = friendGroupList.stream()
                .filter(group -> StrUtil.isBlank(group.getId()))
                .collect(Collectors.toList());
        if(CollUtil.isNotEmpty(createList)) {
            createList.stream()
                    .forEach(group -> {
                        group.setId(snowFlake.nextId());
                        group.setUserId(userInfo.getAccount());
                    });
            friendGroupMapper.insertBat(createList);
        }
        //将新创建的群组剔除剩下所有需要更新的群组信息
        friendGroupList.removeAll(createList);
        if(CollUtil.isNotEmpty(friendGroupList)) {
            friendGroupMapper.updateBat(friendGroupList);
        }
        //获取传入的分组id列表
        List<String> idList = friendGroupList.stream()
                .map(group -> group.getId())
                .collect(Collectors.toList());
        //获取已经删除的群组信息
        String id = "";
        for (FriendGroup group : queryList) {
            if(group.getNickname().equals("我的好友")){
                id = group.getId();
                break;
            }
        }
        List<FriendGroup> delList = queryList.stream()
                .filter(query -> !idList.contains(query.getId()))
                .collect(Collectors.toList());
        if(CollUtil.isNotEmpty(delList)) {
            myFriendMapper.updateToMyFriendGroup(delList, id);
            friendGroupMapper.delBat(delList,userInfo.getAccount());
        }
        return ResultFactory.success("更新成功");
    }
}
