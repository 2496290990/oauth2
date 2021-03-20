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
     * 更新
     * @param myFriend
     * @return
     */
    Result updateMyFriend(MyFriend myFriend);
}
