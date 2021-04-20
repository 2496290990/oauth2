package com.eleven.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eleven.common.Result;
import com.eleven.entity.FriendGroup;

import java.util.List;

/**
 * @author zhaojinhui
 * @date 2021/3/17 19:32
 * @apiNote
 */
public interface FriendGroupService extends IService<FriendGroup> {
    /**
     * 创建好友分组
     * @param friendGroup
     * @return
     */
    Result createFriendGroup(FriendGroup friendGroup);

    /**
     * 批量创建分组
     * @param friendGroupList
     * @return
     */
    Result createFriendGroupBat(List<FriendGroup> friendGroupList);

    /**
     * 查询我的好友分组列表
     * @param friendGroup
     * @return
     */
    Result queryMyGroupList(FriendGroup friendGroup);

    /**
     * 更新好友群组信息
     * @param friendGroup
     * @return
     */
    Result updateFriendGroup(FriendGroup friendGroup);

    /**
     * 删除好友分组
     * @param friendGroup
     * @return
     */
    Result delFriendGroup(FriendGroup friendGroup);

    /**
     * 批量更新好友群组
     * @param friendGroupList 批量更新的列表
     * @return
     */
    Result updateFriendGroupList(List<FriendGroup> friendGroupList);
}
