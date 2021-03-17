package com.eleven.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eleven.common.Result;
import com.eleven.common.ResultFactory;
import com.eleven.entity.ChatHistory;
import com.eleven.mapper.ChatHistoryMapper;
import com.eleven.service.ChatHistoryService;
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

    @Override
    public Result queryPrivateChatHistory(ChatHistory chatHistory) {
        List<ChatHistory> chatHistoryList = chatHistoryMapper.selectChatHistory(chatHistory);
        return ResultFactory.success(chatHistoryList);
    }

    @Override
    public Result sendChat(ChatHistory chatHistory) {
        chatHistoryMapper.insert(chatHistory);
        // TODO: 2021/2/23 给好友发送消息
        return ResultFactory.success(null);
    }
}
