package com.eleven.controller;


import com.eleven.common.Result;
import com.eleven.entity.VerifyLog;
import com.eleven.service.VerifyLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 验证码记录表 前端控制器
 * </p>
 *
 * @author eleven
 * @since 2021-02-22
 */
@RestController
@RequestMapping("/verifyLog")
@Api(tags = "验证码控制器")
public class VerifyLogController {

    @Autowired
    private VerifyLogService verifyLogService;

    /**
     * 验证验证码是否正确
     * @param verifyLog
     * @return
     */
    @PostMapping("/verifyLastCode")
    @ApiOperation(value = "验证验证码是否正确",notes = "验证验证码是否可用，必须参数registerAccount,type,verifyCode")
    public Result verifyLastCode(@RequestBody VerifyLog verifyLog){
        return  verifyLogService .verifyLastCode(verifyLog);
    }
}

