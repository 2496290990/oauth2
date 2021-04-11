package com.eleven.controller;


import com.eleven.common.Result;
import com.eleven.entity.CircleOfFriend;
import com.eleven.service.CircleOfFriendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 动态记录表 前端控制器
 * </p>
 *
 * @author eleven
 * @since 2021-02-22
 */
@RestController
@RequestMapping("/circleOfFriend")
@Api(tags = "朋友圈/动态")
public class CircleOfFriendController {

    @Autowired
    private CircleOfFriendService friendService;

    @GetMapping("/query")
    @ApiOperation(value = "查询最新的20条朋友圈")
    public Result queryLast20Circles(){
        return friendService.queryLast20Circles();
    }

    @GetMapping("/queryOwn")
    @ApiOperation(value = "查询自己的朋友圈")
    public Result queryOwnCircle(CircleOfFriend friend){
        return friendService.queryOwnCircle(friend);
    }

    @PostMapping
    @ApiOperation(value = "发朋友圈")
    public Result insertNew(List<MultipartFile> files,CircleOfFriend circleOfFriend) throws IOException {
        return friendService.insertNew(circleOfFriend,files);
    }

    @PostMapping("/publish")
    @ApiOperation("先上传文件后发表的方式")
    public Result publishCircle(@RequestBody CircleOfFriend circleOfFriend){
        return friendService.publishCircle(circleOfFriend);
    }

    /**
     * 获取朋友圈列表
     * @return
     */
    @GetMapping("/list")
    public Result getCircleList(){
        return  friendService.getCircleList();
    }

    @PutMapping("/update")
    public Result updateById(@RequestBody CircleOfFriend circleOfFriend){
        return friendService.updateCircleById(circleOfFriend);
    }
}

