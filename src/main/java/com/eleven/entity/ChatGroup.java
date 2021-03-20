package com.eleven.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.eleven.common.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 聊天群组表
 * </p>
 *
 * @author eleven
 * @since 2021-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("im_chat_group")
@ApiModel(value="ChatGroup对象", description="聊天群组表")
public class ChatGroup extends PageParam implements Serializable {

private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键id")
    private String id;

    @ApiModelProperty(value = "创建这id")
    private String createId;

    @ApiModelProperty(value = "群名称")
    private String groupName;

    @ApiModelProperty(value = "群简介")
    private String groupIntro;

    @ApiModelProperty(value = "群头像")
    private String groupUrl;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "群标签")
    private String groupTag;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "群主id")
    private String ownerId;

    @ApiModelProperty(value = "群等级")
    private Integer level;

    private String by1;

    private String by2;

    private String by3;

    private String by4;

    private String by5;

    private String by6;


}
