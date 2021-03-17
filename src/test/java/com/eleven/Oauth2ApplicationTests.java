package com.eleven;

import cn.hutool.http.HttpRequest;
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

}
