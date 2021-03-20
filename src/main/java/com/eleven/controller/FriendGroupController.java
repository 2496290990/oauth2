package com.eleven.controller;

import com.eleven.common.Result;
import com.eleven.entity.FriendGroup;
import com.eleven.service.FriendGroupService;
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
public class FriendGroupController {

    @Autowired
    private FriendGroupService friendGroupService;

    @PostMapping("/create")
    public Result createFriendGroup(@RequestBody FriendGroup friendGroup){
        return friendGroupService.createFriendGroup(friendGroup);
    }

    @PostMapping("/createBat")
    public Result createFriendGroupBat(@RequestBody List<FriendGroup>friendGroupList){
        return friendGroupService.createFriendGroupBat(friendGroupList);
    }

    @GetMapping("/list")
    public Result queryMyGroupList(FriendGroup friendGroup){
        return friendGroupService.queryMyGroupList(friendGroup);
    }

    @PutMapping("/update")
    public Result updateFriendGroup(@RequestBody FriendGroup friendGroup){
        return friendGroupService.updateFriendGroup(friendGroup);
    }
}
