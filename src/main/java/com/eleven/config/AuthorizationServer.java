package com.eleven.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @author zhaojinhui
 * @date 2021/3/13 14:22
 * @apiNote 授权服务配置类
 * @EnableAuthorizationServer 表示这是一个授权服务器配置类
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    @Qualifier("redisTokenStore")
    private TokenStore tokenStore;

    @Value("${oauth2.clientId}")
    private String clientId;
    @Value("${oauth2.secret}")
    private String secret;

    @Bean
    public DefaultTokenServices tokenServices(){
        DefaultTokenServices services = new DefaultTokenServices();
        services.setTokenStore(tokenStore);
        //开启支持 refreshToken
        services.setSupportRefreshToken(true);
        //复用refreshToken
        services.setReuseRefreshToken(true);
        //token有效期 30分钟
        services.setAccessTokenValiditySeconds(30 * 60);
        // refreshToken有效期7天
        services.setRefreshTokenValiditySeconds(7 * 24 * 60 * 60);
        return services;
    }

    /**
     * 密码模式
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                //自定义登录逻辑
                .userDetailsService(userDetailsService)
                // 授权管理器
                .authenticationManager(authenticationManager)
                //token存储位置
                .tokenStore(tokenStore)

                .tokenServices(tokenServices());
        ;

    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 使用内存认证模式
        clients.inMemory()
                // clientId
                .withClient(clientId)
                // client密码
                .secret(passwordEncoder.encode(secret))
                // 认证成功之后的重定向地址，获取授权码
                .redirectUris("http://www.baidu.com")
                // 授权范围
                .scopes("all")
                // 授权类型
                .authorizedGrantTypes("authorization_code","password","refresh_token");
    }
}
