package com.eleven.service;

import com.eleven.common.Result;
import com.eleven.entity.LoginUser;
import com.eleven.entity.VerifyLog;

/**
 * @author zhaojinhui
 * @date 2021/3/17 13:50
 * @apiNote
 */
public interface RegisterService {

    /**
     * 给邮箱发送验证码
     * @param email 邮箱
     * @return
     */
    Result sendEmailCode(String email);

    /**
     * 验证验证码
     * @param verifyLog 邮箱和验证码
     * @return
     */
    Result verifyEmailCode(VerifyLog verifyLog);

    /**
     * 保存新用户
     * @param loginUser 注册的用户信息
     * @return
     */
    Result saveNewLoginUser(LoginUser loginUser);
}
