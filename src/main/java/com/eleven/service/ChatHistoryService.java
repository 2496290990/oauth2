package com.eleven.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eleven.common.Result;
import com.eleven.entity.ChatHistory;

/**
 * <p>
 * 聊天记录表 服务类
 * </p>
 *
 * @author eleven
 * @since 2021-02-22
 */
public interface ChatHistoryService extends IService<ChatHistory> {

    /**
     * 查询私聊聊天记录
     * @param chatHistory 聊天记录实体
     * @return
     */
    Result queryPrivateChatHistory(ChatHistory chatHistory);

    /**
     * 发送聊天记录
     * @param chatHistory
     * @return
     */
    Result sendChat(ChatHistory chatHistory);

    /**
     * 保存聊天记录
     * @param chatHistory
     * @return
     */
    Result saveChatHistory(ChatHistory chatHistory);

    /**
     * 删除聊天记录
     * @param chatHistory
     * @return
     */
    Result delChatHistory(ChatHistory chatHistory);
}
