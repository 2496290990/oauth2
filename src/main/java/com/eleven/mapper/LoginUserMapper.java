package com.eleven.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eleven.entity.LoginUser;
import com.eleven.entity.MyFriend;

import java.util.List;

/**
 * @author zhaojinhui
 * @date 2021/3/17 15:54
 * @apiNote
 */
public interface LoginUserMapper extends BaseMapper<LoginUser> {

    /**
     * 获取所有的账号信息
     * @return
     */
    List<String> queryAllAccount();

    /**
     *
     * @param username
     * @return
     */
    LoginUser selectUser(String username);

    /**
     * 根据好友的账号查询好友信息
     * @return
     * @param account
     */
    LoginUser queryUserByAccount(String account);

    /**
     * 根据登录账户修改用户信息
     * @param loginUser
     */
    void updateByAccount(LoginUser loginUser);

    /**
     * 用户添加好友，按照查询信息搜索系统内用户
     * @param name 用户传入的查询条件
     * @return
     */
    List<LoginUser> queryUserForSearch(String name);
}
