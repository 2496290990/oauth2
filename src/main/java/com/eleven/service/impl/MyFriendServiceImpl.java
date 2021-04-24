package com.eleven.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eleven.common.Result;
import com.eleven.common.ResultFactory;
import com.eleven.entity.FriendGroup;
import com.eleven.entity.LoginUser;
import com.eleven.entity.MyFriend;
import com.eleven.mapper.FriendGroupMapper;
import com.eleven.mapper.MyFriendMapper;
import com.eleven.service.MyFriendService;
import com.eleven.util.SecurityUtils;
import com.eleven.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 好友关系表 服务实现类
 * </p>
 *
 * @author eleven
 * @since 2021-02-22
 */
@Service
public class MyFriendServiceImpl extends ServiceImpl<MyFriendMapper, MyFriend> implements MyFriendService {

    @Autowired
    private MyFriendMapper myFriendMapper;

    @Autowired
    private FriendGroupMapper friendGroupMapper;

    @Autowired
    private SnowFlake snowFlake;

    @Override
    public Result queryMyFriend() {
        LoginUser userInfo = SecurityUtils.getUserInfo();
        List<FriendGroup> friendGroupList = friendGroupMapper.getGroupByAccount(userInfo.getAccount());
        return ResultFactory.success(friendGroupList);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Result addFriend(MyFriend myFriend) {
        LoginUser userInfo = SecurityUtils.getUserInfo();
        myFriend.setMyAccount(userInfo.getAccount());
        MyFriend  queryFriend = myFriendMapper.getFriendBy2Account(myFriend);
        if(queryFriend == null) {
            //获取当前登录人的好友分组信息
            FriendGroup group = new FriendGroup();
            group.setNickname("我的好友");
            List<FriendGroup> friendGroupList = friendGroupMapper.getGroupByLike(group);
            FriendGroup defaultGroup = friendGroupList.get(0);
            //批量插入好友关系
            List<MyFriend> myFriendList = new ArrayList<>();
            if (StrUtil.isBlank(myFriend.getFriendGroup())) {
                myFriend.setFriendGroup(defaultGroup.getId());
            }
            myFriend.setId(snowFlake.nextId());
            myFriend.setMyAccount(userInfo.getAccount());
            myFriend.setParticular("0");
            myFriend.setBlocked("0");
            MyFriend hisFriend = new MyFriend();
            hisFriend.setId(snowFlake.nextId());
            hisFriend.setMyAccount(myFriend.getFriendAccount());
            hisFriend.setFriendAccount(userInfo.getAccount());
            hisFriend.setFriendGroup(defaultGroup.getId());
            hisFriend.setNickname(userInfo.getUsername());
            hisFriend.setParticular("0");
            hisFriend.setBlocked("0");
            Collections.addAll(myFriendList, myFriend, hisFriend);
            myFriendMapper.insertBat(myFriendList);
            return ResultFactory.success("添加成功");
        }else if("1".equals(queryFriend.getBlocked())){
            queryFriend.setBlocked("0");
            myFriendMapper.updateById(queryFriend);
            return ResultFactory.success("恢复好友成功");
        }else{
            return ResultFactory.failed("已经是好友啦,添加好友失败");
        }

    }

    @Override
    public Result updateMyFriend(MyFriend myFriend) {
        return ResultFactory.success(myFriendMapper.updateById(myFriend));
    }

    @Override
    public Result queryByAccount(String account) {
        LoginUser userInfo = SecurityUtils.getUserInfo();
        MyFriend myFriend = myFriendMapper.getMyFriendByAccount(userInfo.getAccount(), account);
        return ResultFactory.success(myFriend);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public Result editFriend(MyFriend myFriend) {
        LoginUser userInfo = SecurityUtils.getUserInfo();
        myFriend.setFriendGroup(myFriend.getGroupId());
        int effectRow = myFriendMapper.updateById(myFriend);
        //获取原来数据库中的数据
        MyFriend queryFriend = myFriendMapper.getMyFriendByAccount(userInfo.getAccount(), myFriend.getFriendAccount());
        //两个群组id不一致，更新群组信息
        String oldGroup = queryFriend.getGroup().getId();
        if(!oldGroup.equals(myFriend.getGroupId()) && effectRow > 0){
            friendGroupMapper.updateGroupTotal(oldGroup,-1);
            friendGroupMapper.updateGroupTotal(myFriend.getGroupId(),1);
        }
        return effectRow > 0 ? ResultFactory.success("修改成功"): ResultFactory.failed("修改失败");
    }

    @Override
    public Result joinBlock(MyFriend myFriend) {
        LoginUser userInfo = SecurityUtils.getUserInfo();
        MyFriend queryFriend = myFriendMapper.getMyFriendByAccount(userInfo.getAccount(),myFriend.getFriendAccount());
        return null;
    }
}
