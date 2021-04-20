package com.eleven.controller;

import com.eleven.common.Result;
import com.eleven.entity.FriendGroup;
import com.eleven.service.FriendGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhaojinhui
 * @date 2021/3/17 19:31
 * @apiNote
 */
@RestController
@RequestMapping("/friendGroup")
@Api(tags = "好友群组")
public class FriendGroupController {

    @Autowired
    private FriendGroupService friendGroupService;

    @PostMapping("/create")
    @ApiOperation(value = "创建好友分组")
    public Result createFriendGroup(@RequestBody FriendGroup friendGroup){
        return friendGroupService.createFriendGroup(friendGroup);
    }

    @PostMapping("/createBat")
    @ApiOperation(value ="批量创建好友分组")
    public Result createFriendGroupBat(@RequestBody List<FriendGroup>friendGroupList){
        return friendGroupService.createFriendGroupBat(friendGroupList);
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询自己的好友分组")
    public Result queryMyGroupList(FriendGroup friendGroup){
        return friendGroupService.queryMyGroupList(friendGroup);
    }

    @PutMapping("/update")
    @ApiOperation(value = "更新好友分组信息")
    public Result updateFriendGroup(@RequestBody FriendGroup friendGroup){
        return friendGroupService.updateFriendGroup(friendGroup);
    }

    @PutMapping("/updateList")
    @ApiOperation(value = "批量更新群组信息")
    public Result updateFriendBat(@RequestBody List<FriendGroup> friendGroupList){
        return friendGroupService.updateFriendGroupList(friendGroupList);
    }

    @PutMapping("/del")
    @ApiOperation(value = "删除分组信息")
    public Result delFriendGroup(FriendGroup friendGroup){
        return friendGroupService.delFriendGroup(friendGroup);
    }
}
