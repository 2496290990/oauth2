package com.eleven.controller;

import com.eleven.common.Result;
import com.eleven.common.ResultFactory;
import com.eleven.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/getCurrentUser")
    public Result getCurrentUserAuthentication(Authentication authentication){
        log.info("authorization - {}" ,authentication.getPrincipal());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResultFactory.success(authentication.getPrincipal());
    }

    @GetMapping("/getUser")
    public Result getUser(){
        return userService.getUser();
    }
}
