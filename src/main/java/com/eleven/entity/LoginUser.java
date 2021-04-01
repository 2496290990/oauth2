package com.eleven.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.eleven.common.PageParam;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * @author zhaojinhui
 * @date 2021/3/13 14:06
 * @apiNote
 */
@Data
@TableName("im_user")
public class LoginUser extends PageParam implements UserDetails {

    private String username;

    private String password;

    private List<GrantedAuthority> authorities;

    private String id;
    /** 账号 */
    private String account;
    /** 密码随机盐*/
    private String salt;
    /** 0女1男2保密 */
    private Integer sex;
    /** 手机号 */
    private String phone;
    /**邮箱*/
    private String email;
    /**创建时间*/
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**删除标记0已删除1正常*/
    @TableField(fill = FieldFill.INSERT)
    private String delFlag;
    /**更新时间*/
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    private String updateBy;
    /** 头像地址 */
    private String accountUrl;
    /** 简介 */
    private String intro;
    /** 生日信息 */
    private LocalDate birth;

    /**0下线 1上线 */
    private Integer loginState;

    /** 最后登录时间 */
    private LocalDateTime lastLoginTime;

    /** 环信的密码 */
    private String hxPwd;

    @TableField(exist = false)
    private String rePwd;

    public LoginUser(){}

    public LoginUser(String username, String password, List<GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
