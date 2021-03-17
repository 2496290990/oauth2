package com.eleven.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
 * 好友关系表
 * </p>
 *
 * @author eleven
 * @since 2021-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("im_my_friend")
@ApiModel(value="MyFriend对象", description="好友关系表")
public class MyFriend implements Serializable {

private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键id")
    private String id;

    @ApiModelProperty(value = "我的账号")
    private String myAccount;

    @ApiModelProperty(value = "好友账号")
    private String friendAccount;

    @ApiModelProperty(value = "0 正常好友 1特别关系")
    private String particular;

    @ApiModelProperty(value = "0正常 1 黑名单 2被拉黑")
    private String blocked;

    @ApiModelProperty(value = "0删除 1正常")
    @TableLogic
    private String delFlag;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "备注")
    private String nickname;

    @ApiModelProperty(value = "标签")
    private String tag;

    @ApiModelProperty(value = "简介")
    private String intro;

    @ApiModelProperty(value = "好友分组")
    private String friendGroup;

    @ApiModelProperty(value = "备用字段")
    private String by1;

    private String by2;

    private String by3;


}
