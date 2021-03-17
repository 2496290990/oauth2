package com.eleven.config;

/**
 * @author zhaojinhui
 * @date 2021/2/28 16:50
 * @apiNote
 */

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * @author zhaojinhui
 * @date 2021/2/20 12:27
 * @apiNote
 */
@Configuration
public class CommonConfig extends WebMvcAutoConfiguration {

    @Bean
    public JavaMailSenderImpl getMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setPort(465);
        mailSender.setHost("smtp.qq.com");
        mailSender.setUsername("2496290990@qq.com");
        mailSender.setPassword("mqyxtwbsffzqeaae");
        Properties properties = new Properties();
        properties.put("1", 1);
        return mailSender;
    }
}

