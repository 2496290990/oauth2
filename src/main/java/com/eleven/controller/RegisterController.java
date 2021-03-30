package com.eleven.controller;

import com.eleven.common.Result;
import com.eleven.entity.LoginUser;
import com.eleven.entity.VerifyLog;
import com.eleven.service.RegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhaojinhui
 * @date 2021/3/17 13:49
 * @apiNote
 */
@RestController
@RequestMapping("/register")
@Api(tags = "注册相关")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @GetMapping("/send")
    @ApiOperation("发送邮箱验证码")
    public Result sendEmailCode(String email){
        return registerService.sendEmailCode(email);
    }

    @PostMapping("/verify")
    @ApiOperation("校验验证码")
    public Result verifyEmailCode(@RequestBody VerifyLog verifyLog){
        return registerService.verifyEmailCode(verifyLog);
    }

    @PostMapping("/reg")
    @ApiOperation("注册用户")
    public Result saveNewLoginUser(@RequestBody LoginUser loginUser){
        return registerService.saveNewLoginUser(loginUser);
    }
}
