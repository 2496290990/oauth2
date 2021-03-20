package com.eleven.service.impl;

import cn.hutool.core.util.StrUtil;
import com.eleven.common.*;
import com.eleven.entity.LoginUser;
import com.eleven.entity.VerifyLog;
import com.eleven.mapper.LoginUserMapper;
import com.eleven.mapper.VerifyLogMapper;
import com.eleven.service.RegisterService;
import com.eleven.util.EasemobUtil;
import com.eleven.util.EmailUtil;
import com.eleven.util.SnowFlake;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaojinhui
 * @date 2021/3/17 13:50
 * @apiNote
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RegisterServiceImpl implements RegisterService {

    private final EmailUtil emailUtil;

    private final RedisTemplate redisTemplate;

    private final SnowFlake snowFlake;

    private final VerifyLogMapper verifyLogMapper;

    private final LoginUserMapper loginUserMapper;

    private final BCryptPasswordEncoder passwordEncoder;

    private final EasemobUtil easemobUtil;

    @Override
    public Result sendEmailCode(String emailAddress) {
        Email email = new Email();
        email.setSendTo(emailAddress);
        email.setSubject("校友邦注册邮件");
        String code = "";
        VerifyLog verifyLog = new VerifyLog();

        try{
            //获取验证码
            code = emailUtil.sendEmail(email, true);
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
            return ResultFactory.failed("发送注册邮件失败，请联系系统管理员！");
        }
    }

    @Override
    public Result verifyEmailCode(VerifyLog verifyLog) {
        //从redis中获取验证码
        String code = String.valueOf(redisTemplate.opsForValue().get(verifyLog.getRegisterAccount()));
        if(StrUtil.isBlank(code)){
            return ResultFactory.failed("验证码已过期");
        }
        if(StrUtil.equals(code, verifyLog.getVerifyCode())){
            //验证通过之后删除键值
            redisTemplate.delete(verifyLog.getRegisterAccount());
            return ResultFactory.success("验证通过");
        }
        return ResultFactory.failed("验证码错误");
    }


    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public Result saveNewLoginUser(LoginUser loginUser) {
        List<String> accountList = loginUserMapper.queryAllAccount();
        String account = "";
        do{
            account = generatorAccount();
        }while (accountList.contains(account));
        loginUser.setId(snowFlake.nextId());
        loginUser.setAccount(account);
        String salt = generatorPasswordSalt();
        loginUser.setSalt(salt);
        String password = loginUser.getPassword();
        //password = password + salt;
        loginUser.setPassword(passwordEncoder.encode(password));
        int effectRow = loginUserMapper.insert(loginUser);
        //==================注册环信用户============
        EasemobRegResult easemobRegResult = easemobUtil.openRegister(loginUser);
        List<Entities> entities = easemobRegResult.getEntities();
        // TODO: 2021/3/17 插入信息
        return effectRow != 0 ?
                ResultFactory.success("注册成功") :
                ResultFactory.failed("注册失败，请联系管理员");

    }

    private static String generatorPasswordSalt(){
        String salt = "";
        while(salt.length() <= 6) {
            //生成[m,n)区间的随机数 生成 0 - z 之间的字符
            char randomChar = (char) (Math.random() * (123 - 30) + 30);
            salt += randomChar;
            salt = salt.replaceAll("\\D\\W", "");
        }
        return salt;
    }

    public static String generatorAccount(){
        String account = "";
        while(account.length() < 9){
            int i = (int) (Math.random() * 10);
            account += i;
        }
        return account;
    }
}
