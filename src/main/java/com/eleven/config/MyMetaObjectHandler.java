package com.eleven.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.eleven.entity.LoginUser;
import com.eleven.util.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author zhaojinhui
 * @date 2021/3/17 14:44
 * @apiNote
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        LoginUser userInfo = SecurityUtils.getUserInfo();
        String createBy = userInfo == null ? "System" : userInfo.getAccount();
        this.setFieldValByName("createBy",createBy , metaObject);
        this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("delFlag", "1", metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        LoginUser userInfo = SecurityUtils.getUserInfo();
        String updateBy = userInfo == null ? "System" : userInfo.getAccount();
        this.setFieldValByName("updateBy", updateBy, metaObject);
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }
}
