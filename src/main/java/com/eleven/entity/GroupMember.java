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
 * 群组成员表
 * </p>
 *
 * @author eleven
 * @since 2021-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("im_group_member")
@ApiModel(value="GroupMember对象", description="群组成员表")
public class GroupMember implements Serializable {

private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "群组id")
    private String groupId;

    @ApiModelProperty(value = "0群主1管理员2普通成员")
    private Integer type;

    @ApiModelProperty(value = "群员账号")
    private String userId;

    @ApiModelProperty(value = "群昵称")
    private String nickname;

    @ApiModelProperty(value = "加入时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    private String by1;

    private String by2;

    private String by3;

    private String by4;

    private String by5;

    private String by6;


}
