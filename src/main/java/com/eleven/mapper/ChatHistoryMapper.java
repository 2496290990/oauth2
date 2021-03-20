package com.eleven.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eleven.entity.ChatHistory;

import java.util.List;

/**
 * <p>
 * 聊天记录表 Mapper 接口
 * </p>
 *
 * @author eleven
 * @since 2021-02-22
 */
public interface ChatHistoryMapper extends BaseMapper<ChatHistory> {

    /**
     * 查询聊天记录
     * @param chatHistory 聊天记录实体
     * @return
     */
    List<ChatHistory> selectChatHistory(ChatHistory chatHistory);

    Integer delChatHistory(ChatHistory chatHistory);
}
