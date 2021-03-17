package com.eleven.util;

import com.eleven.common.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;

/**
 * @author zhaojinhui
 * @date 2021/2/28 17:32
 * @apiNote
 */
public class ResponseUtil {
    public static void out(HttpServletResponse response, Result result){
        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try{
            objectMapper.writeValue(response.getWriter(),result);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
