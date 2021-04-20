package com.eleven.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eleven.entity.FriendGroup;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 根据登录人的账户信息查询好友列表
     * @param account
     * @return
     */
    List<FriendGroup> getGroupByAccount(String account);

    /**
     * 查询我的好友这个群组
     * @param account
     * @return
     */
    FriendGroup queryMyGroup(String account);

    /**
     * 更新我的好友分组
     */
    void updateMyGroup();

    /**
     * 更新数据
     * @param myGroup
     */
    void updateMyGroupNum(FriendGroup myGroup);

    /**
     * 同时更新两条好友群组
     * @param offset 影响行数
     * @param groupId 群组id
     */
    void updateGroupTotal(@Param("groupId")String groupId,@Param("offset")Integer offset);

    /**
     * 批量多字段更新
     * @param friendGroupList
     */
    void updateBat(List<FriendGroup> friendGroupList);

    /**
     * 批量删除
     * @param delList
     * @param account
     */
    void delBat(@Param("list") List<FriendGroup> delList, @Param("account") String account);
}
