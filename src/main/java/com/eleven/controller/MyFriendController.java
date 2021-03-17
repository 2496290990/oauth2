package com.eleven.controller;


import com.eleven.common.Result;
import com.eleven.service.MyFriendService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 好友关系表 前端控制器
 * </p>
 *
 * @author eleven
 * @since 2021-02-22
 */
@RestController
@RequestMapping("/myFriend")
@Api(tags = "好友列表")
public class MyFriendController {

    @Autowired
    private MyFriendService myFriendService;

    @GetMapping("/query")
    public Result queryMyFriend(){
        return myFriendService.queryMyFriend();
    }
}

