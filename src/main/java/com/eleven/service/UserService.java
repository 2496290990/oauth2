package com.eleven.service;

import com.eleven.common.Result;
import com.eleven.entity.LoginUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhaojinhui
 * @date 2021/3/15 13:10
 * @apiNote
 */

public interface UserService  {
    /**
     * 获取用户信息
     * @return
     */
    Result getUser();

    /**
     * 更新用户信
     * @param loginUser
     * @return
     */
    Result updateUser(LoginUser loginUser);

    /**
     * 更新用户密码
     * @param loginUser
     * @return
     */
    Result updateUserPwd(LoginUser loginUser);

    /**
     * 根据用户账号生成二维码
     * @param account 用户账号
     * @param request
     * @param response
     * @return
     */
    Result getQrCodeByAccount(String account, HttpServletRequest request, HttpServletResponse response);

    /**
     * 获取当前登录用户信息
     * @return
     */
    Result getCurrentUser(LoginUser loginUser);

    /**
     * 发送修改密码的验证邮件
     * @param email
     * @return
     */
    Result sendEmailCode(String email);

    /**
     * 查询用户信息
     * @param name
     * @return
     */
    Result queryUser(String name);
}
