package com.eleven.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import com.eleven.common.Result;
import com.eleven.common.ResultFactory;
import com.eleven.entity.LoginUser;
import com.eleven.entity.Oauth2Result;
import com.eleven.service.LoginService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaojinhui
 * @date 2021/3/13 16:50
 * @apiNote
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Autowired
    private Gson gson;

    @Value("${server.port}")
    private String port;

    @Value("${oauth2.clientId}")
    private String clientId;

    @Value("${oauth2.secret}")
    private String secret;

    @Autowired
    private RedisTemplate redisTemplate;

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
        log.info("登录结果 -{}",body);
        return ResultFactory.success(gson.fromJson(body, Oauth2Result.class));
    }

    @Override
    public Result logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(StrUtil.isNotBlank(token)){
            token = token.replaceAll("Bearer ", "");
        }
        String authKey = "auth-token:auth:" + token;
        String accessKey = "auth-token:access:" + token;
        redisTemplate.delete(authKey);
        redisTemplate.delete(accessKey);
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(null);
        return ResultFactory.success("用户退出成功");
    }
}
