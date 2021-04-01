package com.eleven.service.impl;

import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import cn.hutool.http.HttpRequest;
import com.eleven.common.Email;
import com.eleven.common.Result;
import com.eleven.common.ResultFactory;
import com.eleven.entity.LoginUser;
import com.eleven.entity.VerifyLog;
import com.eleven.mapper.LoginUserMapper;
import com.eleven.mapper.UserMapper;
import com.eleven.mapper.VerifyLogMapper;
import com.eleven.service.UserService;
import com.eleven.util.EmailUtil;
import com.eleven.util.SecurityUtils;
import com.eleven.util.SnowFlake;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaojinhui
 * @date 2021/3/15 13:11
 * @apiNote
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private LoginUserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SnowFlake snowFlake;

    @Autowired
    private VerifyLogMapper verifyLogMapper;

    @Override
    public Result getUser() {
        LoginUser userInfo = SecurityUtils.getUserInfo();
        return ResultFactory.success(userInfo);
    }

    @Override
    public Result updateUser(LoginUser loginUser) {
        LoginUser userInfo = SecurityUtils.getUserInfo();
        loginUser.setUpdateTime(LocalDateTime.now());
        loginUser.setUpdateBy(userInfo.getAccount());
        userMapper.updateByAccount(loginUser);
        return ResultFactory.success("更新用户信息成功");
    }

    @Override
    public Result updateUserPwd(LoginUser loginUser) {
        LoginUser userInfo = SecurityUtils.getUserInfo();
        String account = userInfo.getAccount();
        LoginUser queryUser = userMapper.selectUser(account);
        if(passwordEncoder.matches(loginUser.getPassword(),queryUser.getPassword())){
            queryUser.setPassword(passwordEncoder.encode(loginUser.getRePwd()));
            userMapper.updateById(queryUser);
            return ResultFactory.success("更新密码成功");
        }
        return ResultFactory.failed("输入密码错误");
    }

    @Override
    public Result getQrCodeByAccount(String account, HttpServletRequest request, HttpServletResponse response) {
        LoginUser loginUser = userMapper.selectUser(account);
        loginUser.setPassword(null);
        loginUser.setSalt(null);
        loginUser.setId(null);
        loginUser.setCreateTime(null);
        loginUser.setUpdateTime(null);
        loginUser.setUpdateTime(null);
        Gson gson = new Gson();

        try {
            QrConfig config = new QrConfig(400, 400);
            InputStream inputStream = HttpRequest.get(loginUser.getAccountUrl())
                    .execute()
                    .bodyStream();
            BufferedImage read = ImageIO.read(inputStream);
            config.setImg(read);
            BufferedImage qrCode = QrCodeUtil.generate(gson.toJson(loginUser), config);
            ServletOutputStream os = null;
            os = response.getOutputStream();
            response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode("qrCode.jpg", "UTF-8"));
            response.setHeader("Connection", "close");
            response.setHeader("Content-Type", "application/octet-stream");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(qrCode, "png", byteArrayOutputStream);
            os.write(byteArrayOutputStream.toByteArray());
            return ResultFactory.success("生成二维码成功");
        } catch (IOException e) {
            e.printStackTrace();
            return ResultFactory.failed("生成二维码失败，请联系系统管理员");
        }

    }

    @Override
    public Result getCurrentUser() {
        LoginUser userInfo = SecurityUtils.getUserInfo();
        return ResultFactory.success(userMapper.selectUser(userInfo.getAccount()));
    }

    @Override
    public Result sendEmailCode(String emailAddress) {
        LoginUser loginUser = userMapper.selectUser(emailAddress);
        if(loginUser != null){
            Email email = new Email();
            email.setSendTo(emailAddress);
            email.setSubject("校友邦验证邮件");
            String code = "";
            VerifyLog verifyLog = new VerifyLog();

            try{
                //获取验证码
                code = emailUtil.sendEmail(email, false);
                //将验证码放置到redis中 三分钟内有效
                redisTemplate.opsForValue().set(emailAddress, code,3, TimeUnit.MINUTES);
                //将数据存放到数据库中
                verifyLog.setId(snowFlake.nextId());
                verifyLog.setCreateTime(LocalDateTime.now());
                verifyLog.setExpireTime(LocalDateTime.now().plusMinutes(3));
                verifyLog.setVerifyCode(code);
                verifyLog.setRegisterAccount(emailAddress);
                verifyLog.setType(0);
                verifyLogMapper.insert(verifyLog);
                return ResultFactory.success(code);
            }catch(Exception e){
                e.printStackTrace();
                return ResultFactory.failed("发送验证邮件失败，请联系系统管理员！");
            }
        }
        return ResultFactory.failed("邮箱暂未注册，请返回注册页面申请账号");
    }

    @Override
    public Result queryUser(String name) {
        //根据用户传入的查询信息查询系统内账户
        List<LoginUser> loginUserList = userMapper.queryUserForSearch(name);
        return ResultFactory.success(loginUserList);
    }

}
