package com.eleven.controller;

import com.eleven.common.Result;
import com.eleven.common.ResultFactory;
import com.eleven.entity.LoginUser;
import com.eleven.service.UserService;
import com.eleven.util.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhaojinhui
 * @date 2021/3/13 14:36
 * @apiNote
 */
@RestController
@RequestMapping("/user")
@Slf4j
@Api(tags = "用户控制器")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUser")
    @ApiOperation("获取用户信息")
    public Result getCurrentUser(LoginUser loginUser){
        return userService.getCurrentUser(loginUser);
    }

    @PutMapping
    public Result updateUser(@RequestBody LoginUser loginUser){
        return userService.updateUser(loginUser);
    }

    @PutMapping("/updatePwd")
    @ApiOperation("更新密码")
    public Result updateUserPwd(@RequestBody LoginUser loginUser){
        return userService.updateUserPwd(loginUser);
    }

    @GetMapping("/getQrCode")
    @ApiOperation("获取用户二维码")
    public Result getQrCodeByAccount(String account, HttpServletRequest request, HttpServletResponse response){
        return userService.getQrCodeByAccount(account,request,response);
    }

    @GetMapping("/queryUser")
    @ApiOperation("根据条件查询用户信息")
    public Result queryUser(String name){
        return userService.queryUser(name);
    }



}
