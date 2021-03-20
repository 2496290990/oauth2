package com.eleven.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.scripting.MybatisFreeMarkerLanguageDriver;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eleven.common.Result;
import com.eleven.common.ResultFactory;
import com.eleven.entity.FriendApply;
import com.eleven.entity.LoginUser;
import com.eleven.entity.MyFriend;
import com.eleven.mapper.FriendApplyMapper;
import com.eleven.mapper.LoginUserMapper;
import com.eleven.mapper.MyFriendMapper;
import com.eleven.service.FriendApplyService;
import com.eleven.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 好友申请记录表 服务实现类
 * </p>
 *
 * @author eleven
 * @since 2021-02-22
 */
@Service
public class FriendApplyServiceImpl extends ServiceImpl<FriendApplyMapper, FriendApply> implements FriendApplyService {

    @Autowired
    private FriendApplyMapper friendApplyMapper;

    @Autowired
    private MyFriendMapper myFriendMapper;

    @Autowired
    private LoginUserMapper loginUserMapper;

    @Override
    public Result applyToAdd(FriendApply friendApply) {
        LoginUser loginUser = loginUserMapper.queryUserByAccount(friendApply.getFriendId());
        if(loginUser == null){
            return ResultFactory.failed("系统内暂无该账号");
        }
        friendApplyMapper.insert(friendApply);
        return ResultFactory.success("验证消息发送成功");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Result operationApply(FriendApply friendApply) {
        LoginUser userInfo = SecurityUtils.getUserInfo();
        friendApply.setUpdateBy(friendApply.getFriendId());
        friendApplyMapper.updateById(friendApply);
        //查询好友的信息
        LoginUser friend = loginUserMapper.queryUserByAccount(friendApply.getFriendId());
        //如果同意的话,添加一条好友信息
        List<MyFriend> myFriendList = new ArrayList<>();
        if(friendApply.getState() == 1){
            MyFriend myFriend = new MyFriend();
            myFriend.setMyAccount(userInfo.getAccount());
            myFriend.setFriendAccount(friendApply.getFriendId());
            myFriend.setNickname(friend.getUsername());
            myFriend.setParticular("0");
            myFriend.setBlocked("0");
            MyFriend hisFriend = new MyFriend();
            hisFriend.setMyAccount(friend.getAccount());
            hisFriend.setFriendAccount(userInfo.getAccount());
            hisFriend.setNickname(userInfo.getUsername());
            hisFriend.setBlocked("0");
            hisFriend.setParticular("0");
            Collections.addAll(myFriendList, myFriend,hisFriend);
        }
        if(CollUtil.isNotEmpty(myFriendList)) {
            myFriendMapper.insertBat(myFriendList);
        }
        return ResultFactory.success("添加成功");
    }

    @Override
    public Result queryApply() {
        LoginUser userInfo = SecurityUtils.getUserInfo();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("friend_id",userInfo.getAccount());
        List list = friendApplyMapper.selectList(queryWrapper);
        return ResultFactory.success(list);
    }
}
