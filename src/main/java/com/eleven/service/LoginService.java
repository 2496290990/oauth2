package com.eleven.service;

import com.eleven.common.Result;
import com.eleven.entity.LoginUser;
import org.springframework.stereotype.Service;

/**
 * @author zhaojinhui
 * @date 2021/3/13 16:50
 * @apiNote
 */
public interface LoginService {

    /**
     * 根据用户名密码登录
     * @param user 用户登录信息
     * @return
     */
    Result login(LoginUser user);
}
