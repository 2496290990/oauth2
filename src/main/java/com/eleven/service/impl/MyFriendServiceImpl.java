package com.eleven.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eleven.common.Result;
import com.eleven.common.ResultFactory;
import com.eleven.entity.LoginUser;
import com.eleven.entity.MyFriend;
import com.eleven.mapper.MyFriendMapper;
import com.eleven.service.MyFriendService;
import com.eleven.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public Result queryMyFriend() {
        LoginUser userInfo = SecurityUtils.getUserInfo();
        List<MyFriend> myFriendList =  myFriendMapper.queryMyFriendByUserId(userInfo.getAccount());
        return ResultFactory.success(myFriendList);
    }
}
