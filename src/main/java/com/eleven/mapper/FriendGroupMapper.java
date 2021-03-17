package com.eleven.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eleven.entity.FriendGroup;

import java.util.List;

/**
 * <p>
 * 好友分组设置 Mapper 接口
 * </p>
 *
 * @author zjh123
 * @since 2021-03-17
 */
public interface FriendGroupMapper extends BaseMapper<FriendGroup> {

    /**
     * 批量插入
     * @param insertBat
     */
    void insertBat(List<FriendGroup> insertBat);

    /**
     * 根据账号查询好友分组信息
     * @param account
     * @return
     */
    List<FriendGroup> getMyGroup(String account);

    /**
     * 查询信息
     * @param friendGroup
     * @return
     */
    List<FriendGroup> getGroupByLike(FriendGroup friendGroup);

}
