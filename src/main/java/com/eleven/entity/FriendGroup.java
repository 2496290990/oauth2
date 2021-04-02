package com.eleven.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.eleven.common.PageParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import java.time.LocalDateTime;
import java.util.Date;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 好友分组设置
 * </p>
 *
 * @author zjh123
 * @since 2021-03-17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("im_friend_group")
public class FriendGroup extends CommonEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键雪花id
     */
    private String id;
    /**
     * 用户id
     */
    @TableField("user_id")
    private String userId;
    /**
     * 分组名称
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 分组人数
     */
    @TableField("group_total")
    private Integer groupTotal;

    @TableField(exist = false)
    private List<MyFriend> myFriendList;
}
