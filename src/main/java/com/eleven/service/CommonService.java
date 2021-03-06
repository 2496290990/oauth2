package com.eleven.service;

import com.eleven.common.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author zhaojinhui
 * @date 2021/3/17 15:16
 * @apiNote
 */

public interface CommonService {
    /**
     * 上传文件
     * @param file 图片文件
     * @return
     */
    Result uploadImg(MultipartFile file) throws IOException;
}
