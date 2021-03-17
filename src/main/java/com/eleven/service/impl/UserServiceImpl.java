package com.eleven.service.impl;

import com.eleven.common.Result;
import com.eleven.common.ResultFactory;
import com.eleven.entity.LoginUser;
import com.eleven.service.UserService;
import com.eleven.util.SecurityUtils;
import org.springframework.stereotype.Service;

/**
 * @author zhaojinhui
 * @date 2021/3/15 13:11
 * @apiNote
 */
@Service
public class UserServiceImpl implements UserService {


    @Override
    public Result getUser() {
        LoginUser userInfo = SecurityUtils.getUserInfo();
        return ResultFactory.success(userInfo);
    }
}
