package com.eleven.controller;

import com.eleven.common.Result;
import com.eleven.service.CommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author zhaojinhui
 * @date 2021/3/17 15:15
 * @apiNote
 */
@RestController
@RequestMapping("/common")
@Api("公共组件")
public class CommonController {

    @Autowired
    private CommonService commonService;

    /**
     * 通用上传文件接口
     * @param file 图片文件
     * @return
     */
    @PostMapping("/uploadImg")
    @ApiOperation("公共上传文件接口")
    public Result uploadImg(MultipartFile file) throws IOException {
        return commonService.uploadImg(file);
    }
}
