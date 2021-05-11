package com.eleven.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eleven.common.Result;
import com.eleven.entity.MyFriend;

/**
 * <p>
 * 好友关系表 服务类
 * </p>
 *
 * @author eleven
 * @since 2021-02-22
 */
public interface MyFriendService extends IService<MyFriend> {

    /**
     * 查询我的好友列表
     * @return
     */
    Result queryMyFriend();

    /**
     * 添加好友
     * @param myFriend
     * @return
     */
    Result addFriend(MyFriend myFriend);

    /**
     * 根据好友账号查询好友信息
     * @param account
     * @return
     */
    Result queryByAccount(String account);

    /**
     * 编辑好友信息
     * @param myFriend
     * @return
     */
    Result editFriend(MyFriend myFriend);

    /**
     * 加入小黑屋
     * @param myFriend
     * @return
     */
    Result joinBlock(MyFriend myFriend);

    /**
     * 移出黑名单
     * @param myFriend
     * @return
     */
    Result removeBlock(MyFriend myFriend);

    /**
     *
     * @param account
     * @return
     */
    Result getBlockStatus(String account);

    /**
     * 根据两个人的账号修改数据
     * @param myFriend
     * @return
     */
    Result updateMyFriendByAccount(MyFriend myFriend);
}
