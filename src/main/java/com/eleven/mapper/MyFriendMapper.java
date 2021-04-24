package com.eleven.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eleven.entity.FriendGroup;
import com.eleven.entity.MyFriend;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
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

    /**
     * 批量插入好友信息
     * @param myFriendList
     */
    void insertBat(@Param("list") List<MyFriend> myFriendList);

    /**
     * 根据两个人的账号查询好友信息
     * @param myFriend
     * @return
     */
    MyFriend getFriendBy2Account(MyFriend myFriend);

    /**
     * 查询我的好友
     * @param account
     * @return
     */
    List<MyFriend> queryMyFriend(String account);

    /**
     * 将已经删除的分组变更到我的好友下
     * @param id
     * @param id1
     */
    Integer updateGroupToMy(@Param("oldId")String id,@Param("newId") String newId);

    /**
     * 查询好友信息
     * @param account 我的账号
     * @param account1 好友账号
     * @return
     */
    MyFriend getMyFriendByAccount(@Param("myAccount") String myAccount,@Param("friendAccount") String friendAccount);

    /**
     * 根据id更新好友信息
     * @param myFriend
     * @return
     */
    int updateById(MyFriend myFriend);

    /**
     * 根据登录人获取好友账号集合
     * @param account 登录人账号
     * @return
     */
    List<MyFriend> getMyFriendList(String account);

    /**
     * 将删除的好友分组内的好友移送到我的好友分组
     * @param delList
     * @param id
     */
    void updateToMyFriendGroup(@Param("list") List<FriendGroup> delList, @Param("id")String id);
}
