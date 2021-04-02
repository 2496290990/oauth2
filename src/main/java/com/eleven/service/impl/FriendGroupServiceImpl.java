package com.eleven.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Snowflake;
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
        friendGroupMapper.updateMyGroupNum(myGroup);
        return null;
    }
}
