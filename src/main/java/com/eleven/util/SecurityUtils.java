package com.eleven.util;

import com.eleven.entity.LoginUser;
import com.google.gson.Gson;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author zhaojinhui
 * @date 2021/3/15 13:08
 * @apiNote
 */
public class SecurityUtils {

    private static final String ANON = "anonymousUser";
    /**
     * 获取用户信息
     * @return LoginUser 返回用户的信息
     */
    public static LoginUser getUserInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || ANON.equals(authentication.getPrincipal())){
            return null;
        }
        return (LoginUser)authentication.getPrincipal();
    }
}
