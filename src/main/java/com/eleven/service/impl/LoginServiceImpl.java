package com.eleven.service.impl;

import cn.hutool.http.HttpRequest;
import com.eleven.common.Result;
import com.eleven.common.ResultFactory;
import com.eleven.entity.LoginUser;
import com.eleven.entity.Oauth2Result;
import com.eleven.service.LoginService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaojinhui
 * @date 2021/3/13 16:50
 * @apiNote
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private Gson gson;

    @Value("${server.port}")
    private String port;

    @Value("${oauth2.clientId}")
    private String clientId;

    @Value("${oauth2.secret}")
    private String secret;

    @Override
    public Result login(LoginUser user) {
        Map<String,Object> map = new HashMap<>();
        map.put("grant_type", "password");
        map.put("username", user.getUsername());
        map.put("scope", "all");
        map.put("password", user.getPassword());
        String body = HttpRequest.post("localhost:" + port + "/oauth/token")
                .basicAuth(clientId, secret)
                .form(map)
                .execute()
                .body();

        return ResultFactory.success(gson.fromJson(body, Oauth2Result.class));
    }
}
