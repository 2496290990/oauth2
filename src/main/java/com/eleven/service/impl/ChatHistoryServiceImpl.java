package com.eleven.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eleven.common.Result;
import com.eleven.common.ResultFactory;
import com.eleven.entity.ChatHistory;
import com.eleven.mapper.ChatHistoryMapper;
import com.eleven.service.ChatHistoryService;
import com.eleven.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 聊天记录表 服务实现类
 * </p>
 *
 * @author eleven
 * @since 2021-02-22
 */
@Service
public class ChatHistoryServiceImpl extends ServiceImpl<ChatHistoryMapper, ChatHistory> implements ChatHistoryService {

    @Autowired
    private ChatHistoryMapper chatHistoryMapper;

    @Autowired
    private SnowFlake snowFlake;

    @Override
    public Result queryPrivateChatHistory(ChatHistory chatHistory) {
        List<ChatHistory> chatHistoryList = chatHistoryMapper.selectChatHistory(chatHistory);
        return ResultFactory.success(chatHistoryList);
    }

    @Override
    public Result sendChat(ChatHistory chatHistory) {
        chatHistory.setId(snowFlake.nextId());
        chatHistoryMapper.insert(chatHistory);
        return ResultFactory.success(null);
    }

    @Override
    public Result saveChatHistory(ChatHistory chatHistory) {
        chatHistory.setId(snowFlake.nextId());
        chatHistoryMapper.insert(chatHistory);
        return ResultFactory.success(chatHistory);
    }

    @Override
    public Result delChatHistory(ChatHistory chatHistory) {
        return ResultFactory.success(chatHistoryMapper.delChatHistory(chatHistory));
    }
}
