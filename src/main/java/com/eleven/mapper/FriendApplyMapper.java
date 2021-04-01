package com.eleven.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eleven.entity.FriendApply;

/**
 * <p>
 * 好友申请记录表 Mapper 接口
 * </p>
 *
 * @author eleven
 * @since 2021-02-22
 */
public interface FriendApplyMapper extends BaseMapper<FriendApply> {

    /**
     * 操作好友申请
     * @param friendApply
     */
    void operationApply(FriendApply friendApply);
}
