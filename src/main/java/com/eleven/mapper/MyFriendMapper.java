package com.eleven.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eleven.entity.MyFriend;

import java.util.List;

/**
 * <p>
 * 好友关系表 Mapper 接口
 * </p>
 *
 * @author eleven
 * @since 2021-02-22
 */
public interface MyFriendMapper extends BaseMapper<MyFriend> {

    /**
     * 根据当前登录人查询好友列表
     * @param id 要查询的登录人的id
     * @return
     */
    List<MyFriend> queryMyFriendByUserId(String id);
}
