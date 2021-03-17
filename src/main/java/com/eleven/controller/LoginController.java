package com.eleven.controller;

import com.eleven.common.Result;
import com.eleven.entity.LoginUser;
import com.eleven.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaojinhui
 * @date 2021/3/13 16:49
 * @apiNote
 */
@RestController
@RequestMapping("/oauth")
public class LoginController {

    @Autowired
    private LoginService loginService;


    @PostMapping("/login")
    public Result passwordTypeLogin(@RequestBody LoginUser user){
        return loginService.login(user);
    }
}
