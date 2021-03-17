package com.eleven.util;

import cn.hutool.extra.mail.MailUtil;
import com.eleven.common.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

/**
 * @author zhaojinhui
 * @date 2021/2/20 10:33
 * @apiNote
 */
@Component
public class EmailUtil {

    /** 授权码 */
    private static final String CODE = "mqyxtwbsffzqeaae";

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("2496290990@qq.com")
    private String fromAddress;


    public String sendEmail(Email email) throws MessagingException, IOException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(fromAddress);
        helper.setTo(email.getSendTo());
        helper.setSubject(email.getSubject());

        mailSender.setHost("smtp.qq.com");
        mailSender.setPort(465);
        mailSender.setUsername("2496290990@qq.com");
        mailSender.setPassword("mqyxtwbsffzqeaae");
        //加载模板数据到邮件模板中
        Context context = new Context();
        context.setVariable("company", "校友邦");
        context.setVariable("companyEn", "Alumni");
        String code = getCode();
        context.setVariable("code", code);
        String process = templateEngine.process("mail.html", context);
        helper.setText(process, true);
        mailSender.send(mimeMessage);
        return code;
    }

    public String sendEmail(Email email,boolean flag)  {

        //加载模板数据到邮件模板中
        Context context = new Context();
        context.setVariable("company", "校友邦");
        context.setVariable("companyEn", "Alumni");
        String code = getCode();
        context.setVariable("code", code);
        String process = templateEngine.process("mail.html", context);
        MailUtil.send(email.getSendTo(), email.getSubject(), process, null, true, null);
        return code;
    }

    private String getCode(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int random = (int) (Math.random() * 10);
            sb.append(random);
        }
        return sb.toString();
    }


}
