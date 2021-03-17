package com.eleven.controller;


import com.eleven.common.Result;
import com.eleven.entity.ChatHistory;
import com.eleven.service.ChatHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 聊天记录表 前端控制器
 * </p>
 *
 * @author eleven
 * @since 2021-02-22
 */
@RestController
@RequestMapping("/chatHistory")
@Api(tags = "聊天历史记录")
public class ChatHistoryController {

    @Autowired
    private ChatHistoryService chatHistoryService;

    @PostMapping("/private")
    @ApiOperation(value = "查询私聊聊天记录" ,tags = "查询当前登录人的私聊聊天记录")
    public Result queryPrivateChatHistory(@RequestBody ChatHistory chatHistory){
        return chatHistoryService.queryPrivateChatHistory(chatHistory);
    }

    @PostMapping("/sendChat")
    @ApiOperation(value = "发送聊天记录" )
    public Result sendChat(@RequestBody ChatHistory chatHistory){
        return chatHistoryService.sendChat(chatHistory);
    }
}

