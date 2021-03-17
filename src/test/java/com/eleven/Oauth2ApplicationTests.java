package com.eleven;

import cn.hutool.http.ContentType;
import cn.hutool.http.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class Oauth2ApplicationTests {

    @Test
    void contextLoads() {
        Map<String,Object> map = new HashMap<>();
        map.put("grant_type", "password");
        map.put("username", "372322840");
        map.put("scope", "all");
        map.put("password", "admin");
        String body = HttpRequest.post("localhost:8099/oauth/token")
                .basicAuth("admin", "elevenZ")
                .form(map)
                .execute()
                .body();
        System.out.println(body);

    }

    @Test
    public void huanXinReg(){
        String clientId = "YXA6zl0MsW5YTQ6JFVFGUTFgeA";
        String clientSecret = "YXA6fN-mBR5MvYCtlqVwLOfyjwgDP5Q";
        String appKey = "1102210107065543#alumni";
        String orgName = "1102210107065543";
        String appName = "alumni";
        String restApi = "https://a1.easemob.com";
        String url = new StringBuilder()
                        .append(restApi)
                        .append("/")
                        .append(orgName)
                        .append("/")
                        .append(appName)
                        .append("/token")
                .toString();
        System.out.println(url);
        Map<String, Object> map = new HashMap();
        map.put("grant_type", "client_credentials");
        map.put("client_id", clientId);
        map.put("client_secret", clientSecret);
        String body = HttpRequest.post(url)
                .contentType(ContentType.JSON.toString())
                .body(new Gson().toJson(map))
                .execute()
                .body();
        System.out.println(body);
    }

    @Test
    public void openReg(){
        Gson gson = new Gson();
        Map<String,Object> map = new HashMap<>();
        map.put("username", "790771525");
        map.put("password", "123456");
        map.put("nickname", "小A");
        String json = gson.toJson(map);
        String body = HttpRequest.post("https://a1.easemob.com/1102210107065543/alumni/users")
                .contentType(ContentType.JSON.toString())
                .body(json)
                .execute()
                .body();
        System.out.println(body);
    }

}
