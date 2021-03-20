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
import org.springframework.data.geo.Circle;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 动态记录表
 * </p>
 *
 * @author eleven
 * @since 2021-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("im_circle_of_friend")
@ApiModel(value="CircleOfFriend对象", description="动态记录表")
public class CircleOfFriend extends PageParam implements Serializable {

private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键id")
    private String id;

    @ApiModelProperty(value = "发表人id")
    private String utterer;

    @ApiModelProperty(value = "发表的文字内容")
    private String text;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "0已删除 1正常")
    @TableLogic
    private String delFlag;

    private String by1;

    private String by2;

    private String by3;

    private String by4;

    private String by5;

    /** 朋友圈文件集合 */
    @TableField(exist = false)
    private List<CircleOss> circleOssList;

    /** 朋友圈平路 */
    @TableField(exist = false)
    private List<CircleReview> circleReviewList;

    @TableField(exist = false)
    private List<ReviewOss> reviewOssList;

}
