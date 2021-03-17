package com.eleven.controller;

import com.eleven.common.Result;
import com.eleven.entity.LoginUser;
import com.eleven.entity.VerifyLog;
import com.eleven.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhaojinhui
 * @date 2021/3/17 13:49
 * @apiNote
 */
@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @GetMapping("/send")
    public Result sendEmailCode(String email){
        return registerService.sendEmailCode(email);
    }

    @PostMapping("/verify")
    public Result verifyEmailCode(@RequestBody VerifyLog verifyLog){
        return registerService.verifyEmailCode(verifyLog);
    }

    @PostMapping("/reg")
    public Result saveNewLoginUser(@RequestBody LoginUser loginUser){
        return registerService.saveNewLoginUser(loginUser);
    }
}
