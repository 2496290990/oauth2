package com.eleven.controller;


import com.eleven.common.Result;
import com.eleven.entity.FriendApply;
import com.eleven.service.FriendApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 好友申请记录表 前端控制器
 * </p>
 *
 * @author eleven
 * @since 2021-02-22
 */
@RestController
@RequestMapping("/friendApply")
@Api(tags = "好友申请")
public class FriendApplyController {

    @Autowired
    private FriendApplyService friendApplyService;

    @PostMapping("/apply")
    @ApiOperation(value = "发起好友申请")
    public Result applyToAdd(@RequestBody FriendApply friendApply){
        return friendApplyService.applyToAdd(friendApply);
    }

    @PostMapping("/operation")
    @ApiOperation(value = "添加好友或拒绝申请" ,notes = "拒绝请将state 改为0，同意改为1")
    public Result operationApply(@RequestBody FriendApply friendApply){
        return friendApplyService.operationApply(friendApply);
    }


}

