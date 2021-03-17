package com.eleven.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eleven.common.Result;
import com.eleven.entity.ChatGroup;

/**
 * <p>
 * 聊天群组表 服务类
 * </p>
 *
 * @author eleven
 * @since 2021-02-22
 */
public interface ChatGroupService extends IService<ChatGroup> {

    /**
     * 创建新的群组
     * @param chatGroup 群组
     * @return 返回创建结果
     * @author eleven
     */
    Result createNewGroup(ChatGroup chatGroup);

    /**
     * 根据群组id 查询群组信息
     * @param id 要查询的群组id
     * @return
     */
    Result queryGroupById(String id);

    /**
     * 编辑群组信息
     * @param chatGroup
     * @return
     */
    Result editGroupById(ChatGroup chatGroup);

    /**
     * 解散群组
     * @param group 要解散的群组id
     * @return
     */
    Result delGroup(ChatGroup group);
}
