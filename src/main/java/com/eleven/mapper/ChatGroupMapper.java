package com.eleven.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eleven.entity.ChatGroup;

import java.util.List;

/**
 * <p>
 * 聊天群组表 Mapper 接口
 * </p>
 *
 * @author eleven
 * @since 2021-02-22
 */
public interface ChatGroupMapper extends BaseMapper<ChatGroup> {

    /**
     * 根据查询条件查询群组信息
     * @param chatGroup
     * @return
     */
    List<ChatGroup> queryGroupByLike(ChatGroup chatGroup);

    int delByHxGroupId(ChatGroup chatGroup);
}
