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
 * 聊天记录表
 * </p>
 *
 * @author eleven
 * @since 2021-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("im_chat_history")
@ApiModel(value="ChatHistory对象", description="聊天记录表")
public class ChatHistory implements Serializable {

private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "发送者id")
    private String senderId;

    @ApiModelProperty(value = "接受人id")
    private String recipient;

    @ApiModelProperty(value = "群组id")
    private String groupId;

    @ApiModelProperty(value = "消息类型")
    private String messageType;

    @ApiModelProperty(value = "消息内容")
    private String text;

    @ApiModelProperty(value = "发送时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "0删除 1展示")
    /** 发送人是否删除聊天记录 */
    private String sendDel;

    @ApiModelProperty(value = "0删除1展示")
    /** 发送人是否删除聊天记录 */
    private String receiveDel;

    @ApiModelProperty(value = "文件地址")
    private String url;

    private String by1;

    private String by2;

    private String by3;

    private String by4;

    private String by5;

    private String by6;


}
