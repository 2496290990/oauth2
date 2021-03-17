package com.eleven.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eleven.entity.LoginUser;

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
}
