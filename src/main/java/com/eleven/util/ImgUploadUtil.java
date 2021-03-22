package com.eleven.util;


import cn.hutool.http.ContentType;
import cn.hutool.http.HttpRequest;
import com.eleven.entity.ImgUploadOss;
import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhaojinhui
 * @date 2021/2/19 17:26
 * @apiNote
 */
public class ImgUploadUtil {

    private static final String requestUrl = "http://test.imgurl.org/upload/localhost";

    public static ImgUploadOss imgUpload(byte[] data, String fileName){
        String body = HttpRequest.post(requestUrl)
                .contentType(ContentType.MULTIPART.toString())
                .form("file", data,fileName)
                .execute()
                .body();
        Gson gson = new Gson();
        return gson.fromJson(body, ImgUploadOss.class);
    }

    public static ImgUploadOss imgUpload(File file){
        String body = HttpRequest.post(requestUrl)
                .contentType(ContentType.MULTIPART.toString())
                .form("file", file)
                .execute()
                .body();
        Gson gson = new Gson();
        return gson.fromJson(body, ImgUploadOss.class);
    }

    public static ImgUploadOss imgUpload(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        File tempFile = new File("temp.png");
        FileUtils.copyInputStreamToFile(inputStream, tempFile);
        String body = HttpRequest.post(requestUrl)
                .contentType(ContentType.MULTIPART.toString())
                .form("file", tempFile)
                .execute()
                .body();
        Gson gson = new Gson();
        return gson.fromJson(body, ImgUploadOss.class);

    }

}
