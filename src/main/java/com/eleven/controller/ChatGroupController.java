package com.eleven.controller;



import com.eleven.common.Result;
import com.eleven.entity.ChatGroup;
import com.eleven.service.ChatGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 聊天群组表 前端控制器
 * </p>
 *
 * @author eleven
 * @since 2021-02-22
 */
@RestController
@RequestMapping("/chatGroup")
@Api(tags = "群聊控制器")
public class ChatGroupController {

    @Autowired
    private ChatGroupService chatGroupService;

    @PostMapping("/create")
    @ApiOperation(value = "创建群组")
    public Result createNewGroup(@RequestBody ChatGroup chatGroup){
        return chatGroupService.createNewGroup(chatGroup);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询群组信息")
    public Result queryGroupById(@PathVariable("id") String id){
        return chatGroupService.queryGroupById(id);
    }

    @PutMapping("/edit")
    @ApiOperation(value = "修改群组信息")
    public Result editGroupById(@RequestBody ChatGroup chatGroup){
        return chatGroupService.editGroupById(chatGroup);
    }

    @PutMapping("/del")
    @ApiOperation(value = "解散群组")
    public Result delGroup(@RequestBody ChatGroup chatGroup){
        return chatGroupService.delGroup(chatGroup);
    }

    @GetMapping("/query")
    public Result queryGroupByLike(ChatGroup chatGroup){
        return chatGroupService.queryGroupByLike(chatGroup);
    }
    /**
     * 查询我的群组
     * @return
     */
    @GetMapping("/queryMyGroup")
    public Result queryMyGroup(ChatGroup chatGroup){
        return chatGroupService.queryMyGroup(chatGroup);
    }

    @PutMapping("/update")
    public Result updateMyGroup(@RequestBody ChatGroup chatGroup){
        return chatGroupService.updateMyGroup(chatGroup);
    }
}

