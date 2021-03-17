package com.eleven.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 验证码记录表
 * </p>
 *
 * @author eleven
 * @since 2021-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("im_verify_log")
@ApiModel(value="VerifyLog对象", description="验证码记录表")
public class VerifyLog implements Serializable {

private static final long serialVersionUID=1L;

    @ApiModelProperty(value = " 主键id")
    private String id;

    @ApiModelProperty(value = "注册的邮箱或者是手机号")
    private String registerAccount;

    @ApiModelProperty(value = "验证码")
    private String verifyCode;

    @ApiModelProperty(value = "0注册 1修改资料")
    private Integer type;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "过期时间")
    private LocalDateTime expireTime;

    private String by1;

    private String by2;

    private String by3;

    private String by4;

    private String by5;


}
