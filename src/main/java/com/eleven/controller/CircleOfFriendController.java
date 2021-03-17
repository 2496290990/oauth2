package com.eleven.controller;


import com.eleven.common.Result;
import com.eleven.entity.CircleOfFriend;
import com.eleven.service.CircleOfFriendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public Result insertNew(CircleOfFriend circleOfFriend, List<MultipartFile> files) throws IOException {
        return friendService.insertNew(circleOfFriend,files);
    }

}

