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
public class ChatGroup extends CommonEntity implements Serializable {

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

    @ApiModelProperty(value = "群标签")
    private String groupTag;

    @ApiModelProperty(value = "群主id")
    private String ownerId;

    @ApiModelProperty(value = "群等级")
    private Integer level;

    /** 环信群组id */
    private String hxGroupId;

    /** 群组类型 0私有1共有 */
    private Integer groupType;

    /** 审核状态 0审核 1公开 */
    private String applyState;

    private String by4;

    private String by5;

    private String by6;

    /** 查询条件 */
    @TableField(exist = false)
    private String queryLike;


}
