package com.eleven.service.impl;

import com.eleven.entity.LoginUser;
import com.eleven.mapper.LoginUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author zhaojinhui
 * @date 2021/3/13 14:05
 * @apiNote
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private LoginUserMapper loginUserMapper;

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginUser user = loginUserMapper.selectUser(username);
        if(user != null){
            user.setAuthorities( AuthorityUtils.commaSeparatedStringToAuthorityList("user"));
        }
        return user;
        /*return new LoginUser("admin",
                passwordEncoder.encode("admin"),
                AuthorityUtils.commaSeparatedStringToAuthorityList("user"));*/
    }
}
