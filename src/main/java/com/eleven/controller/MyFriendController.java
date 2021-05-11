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

    @GetMapping("/queryByAccount")
    @ApiOperation(value = "根据好友账号查询好友信息")
    public Result queryByAccount(String account){
        return myFriendService.queryByAccount(account);
    }

    @PutMapping("/editFriend")
    @ApiOperation(value = "编辑好友信息")
    public Result editFriend(@RequestBody MyFriend myFriend){
        return myFriendService.editFriend(myFriend);
    }

    @PutMapping("/joinBlock")
    @ApiOperation("将好友加入小黑屋")
    public Result joinBlock(@RequestBody MyFriend myFriend){
        return myFriendService.joinBlock(myFriend);
    }

    @PutMapping("/removeBlock")
    @ApiOperation("移出黑名单")
    public Result removeBlock(@RequestBody MyFriend myFriend){
        return myFriendService.removeBlock(myFriend);
    }

    @GetMapping("/block/{account}")
    @ApiOperation("获取好友的黑名单状态")
    public Result getBlockStatus(@PathVariable("account")String account){
        return myFriendService.getBlockStatus(account);
    }

    @PutMapping
    @ApiOperation("修改好友分组信息")
    public Result updateMyFriend(@RequestBody MyFriend myFriend){
        return myFriendService.updateMyFriendByAccount(myFriend);
    }
}

