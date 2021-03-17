package com.eleven.util;

import cn.hutool.http.ContentType;
import cn.hutool.http.HttpRequest;
import com.eleven.common.EasemobRegResult;
import com.eleven.common.EasemobResult;
import com.eleven.common.Result;
import com.eleven.entity.LoginUser;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaojinhui
 * @date 2021/3/17 21:48
 * @apiNote
 */
public class EasemobUtil {

    @Value("${ease:clientId}")
    String clientId;
    @Value("${ease:clientSecret}")
    String clientSecret;
    @Value("${ease:appKey}")
    String appKey ;
    @Value("${ease:orgName}")
    String orgName;
    @Value("${ease:appName}")
    String appName;
    @Value("${ease:restApi}")
    String restApi;
    @Value("${ease:baseUrl}")
    String baseUrl;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private Gson gson = new Gson();;

    /**
     * 获取环信系统的APP token
     * @return
     */
    public EasemobResult getAccessToken(){
        String easeToken = String.valueOf(redisTemplate.opsForValue().get("easeToken"));
        EasemobResult easemobResult = gson.fromJson(easeToken, EasemobResult.class);
        if(easemobResult != null ){
            return easemobResult;
        }else{
            Map<String, Object> map = new HashMap();
            map.put("grant_type", "client_credentials");
            map.put("client_id", clientId);
            map.put("client_secret", clientSecret);
            String body = HttpRequest.post(baseUrl + "/token")
                    .contentType(ContentType.JSON.toString())
                    .body(new Gson().toJson(map))
                    .execute()
                    .body();
            EasemobResult result = gson.fromJson(body, EasemobResult.class);
            redisTemplate.opsForValue()
                    .setIfAbsent("easeToken", body, result.getExpiresIn(), TimeUnit.SECONDS);
            return result;
        }


    }

    public EasemobRegResult openRegister(LoginUser loginUser){
        Map<String,Object> map = new HashMap<>();
        map.put("username", loginUser.getAccount());
        map.put("password", "123456");
        map.put("nickname", loginUser.getUsername());
        String json = gson.toJson(map);
        String body = HttpRequest.post(baseUrl + "/users")
                .contentType(ContentType.JSON.toString())
                .body(json)
                .execute()
                .body();
        return gson.fromJson(body, EasemobRegResult.class);
    }

    public EasemobRegResult authRegister(LoginUser loginUser){
        Map<String,Object> map = new HashMap<>();
        map.put("username", loginUser.getAccount());
        map.put("password", "123456");
        map.put("nickname", loginUser.getUsername());
        String json = gson.toJson(map);
        String body = HttpRequest.post(baseUrl + "/users")
                .contentType(ContentType.JSON.toString())
                .body(json)
                .execute()
                .body();
        return gson.fromJson(body, EasemobRegResult.class);
    }

    /*public static void main(String[] args) {
        String str = "{\"path\":\"/users\",\"uri\":\"https://a1.easemob.com/1102210107065543/alumni/users\",\"timestamp\":1615990703975,\"organization\":\"1102210107065543\",\"application\":\"ce5d0cb1-6e58-4d0e-8915-514651316078\",\"entities\":[{\"uuid\":\"a23e2fa0-872b-11eb-9b95-2b05e5e42cb3\",\"type\":\"user\",\"created\":1615990704032,\"modified\":1615990704032,\"username\":\"790771525\",\"activated\":true,\"nickname\":\"小A\"}],\"action\":\"post\",\"data\":[],\"duration\":101,\"applicationName\":\"alumni\"}";
        Gson gson = new Gson();
        EasemobRegResult easemobRegResult = gson.fromJson(str, EasemobRegResult.class);
        System.out.println(easemobRegResult);

    }*/
}
