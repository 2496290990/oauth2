package com.eleven.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.eleven.common.PageParam;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zhaojinhui
 * @date 2021/4/1 16:30
 * @apiNote
 */
@Data
public class CommonEntity extends PageParam {
    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT,value = "create_time")
    private LocalDateTime createTime;

    /** 创建人 */
    @TableField(fill = FieldFill.INSERT,value = "create_by")
    private String createBy;

    /** 更新时间  */
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /** 更新人  */
    @TableField(value = "update_by",fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    /** 删除标记 0已删除 1正常  */
    @TableField(value = "del_flag",fill = FieldFill.INSERT)
    private String delFlag;

}
