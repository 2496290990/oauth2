package com.eleven.controller;


import com.eleven.common.Result;
import com.eleven.entity.MyFriend;
import com.eleven.service.MyFriendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @ApiOperation("查询我的好友")
    public Result queryMyFriend(){
        return myFriendService.queryMyFriend();
    }

    @PostMapping("/addFriend")
    @ApiOperation(value = "添加好友")
    public Result addFriend(@RequestBody MyFriend myFriend){
        return myFriendService.addFriend(myFriend);
    }

    @PutMapping
    @ApiOperation(value = "更新好友关系")
    public Result updateMyFriend(@RequestBody MyFriend myFriend){
        return myFriendService.updateMyFriend(myFriend);
    }

    @GetMapping("queryByAccount")
    @ApiOperation(value = "根据好友账号查询好友信息")
    public Result queryByAccount(String account){
        return myFriendService.queryByAccount(account);
    }
}

