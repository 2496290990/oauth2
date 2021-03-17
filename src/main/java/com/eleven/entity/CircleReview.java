package com.eleven.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author zhaojinhui
 * @date 2021/3/14 16:22
 * @apiNote
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("im_circle_review")
public class CircleReview {

    private static final Long serialVersionUID = 1L;

    /** 主键id */
    private String id;

    /** 朋友圈id */
    private String circleId;

    /** 评论人id */
    private String reviewId;

    /** 评论内容 */
    private String reviewContent;

    /** 父级id */
    private String parentId;

    /** 创建时间 */
    private LocalDateTime createTime;
    /** 创建人 */
    private String createBy;

    /** 更新时间 */
    private LocalDateTime updateTime;

    /** 更新人 */
    private String updateBy;

    /** 删除标记 */
    private String delFlag;

    /** 备用字段 */
    private String by1;
    private String by2;
    private String by3;
    private String by4;
}
