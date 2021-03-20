package com.eleven.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
 * 朋友圈文件表
 * </p>
 *
 * @author eleven
 * @since 2021-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("im_circle_oss")
@ApiModel(value="CircleOss对象", description="朋友圈文件表")
public class CircleOss extends PageParam implements Serializable {

private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键id")
    private String id;

    @ApiModelProperty(value = "动态id")
    private String circleId;

    @ApiModelProperty(value = "排序字段")
    private Integer sort;

    @ApiModelProperty(value = "文件url")
    private String url;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "0删除1正常")
    @TableLogic
    private String delFlag;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    private String by1;

    private String by2;

    private String by3;

    private String by4;

    private String by5;


}
