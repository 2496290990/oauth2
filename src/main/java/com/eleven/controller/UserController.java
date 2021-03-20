package com.eleven.controller;

import com.eleven.common.Result;
import com.eleven.common.ResultFactory;
import com.eleven.entity.LoginUser;
import com.eleven.service.UserService;
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
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping
    public Result updateUser(@RequestBody LoginUser loginUser){
        return userService.updateUser(loginUser);
    }

    @PutMapping("/updatePwd")
    public Result updateUserPwd(@RequestBody LoginUser loginUser){
        return userService.updateUserPwd(loginUser);
    }

    @GetMapping("/getQrCode")
    public Result getQrCodeByAccount(String account, HttpServletRequest request, HttpServletResponse response){
        return userService.getQrCodeByAccount(account,request,response);
    }
}
