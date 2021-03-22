package com.eleven.controller;

import com.eleven.common.Result;
import com.eleven.entity.LoginUser;
import com.eleven.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhaojinhui
 * @date 2021/3/13 16:49
 * @apiNote
 */
@RestController
@RequestMapping("/oauth")
@Api("登录相关")
public class LoginController {

    @Autowired
    private LoginService loginService;


    @PostMapping("/login")
    @ApiOperation(value = "用户登录",notes = "username可以是邮箱，手机号或者系统分配的账号")
    public Result passwordTypeLogin(@RequestBody LoginUser user){
        return loginService.login(user);
    }

    @GetMapping("/logout")
    @ApiOperation(value = "用户退出")
    public Result userLogout(HttpServletRequest request){return loginService.logout(request);}
}
