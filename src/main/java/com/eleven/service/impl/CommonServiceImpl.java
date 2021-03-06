package com.eleven.service.impl;

import com.eleven.common.Result;
import com.eleven.common.ResultFactory;
import com.eleven.entity.ImgUploadOss;
import com.eleven.service.CommonService;
import com.eleven.util.ImgUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author zhaojinhui
 * @date 2021/3/17 15:16
 * @apiNote
 */
@Service
@Slf4j
public class CommonServiceImpl implements CommonService {


    @Override
    public Result uploadImg(MultipartFile file) throws IOException {
        ImgUploadOss imgUploadOss = ImgUploadUtil.imgUpload(file);
        return ResultFactory.success(imgUploadOss);
    }
}
