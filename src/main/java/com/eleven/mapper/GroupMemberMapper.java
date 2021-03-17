package com.eleven.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eleven.entity.GroupMember;

import java.util.List;

/**
 * <p>
 * 群组成员表 Mapper 接口
 * </p>
 *
 * @author eleven
 * @since 2021-02-22
 */
public interface GroupMemberMapper extends BaseMapper<GroupMember> {

    /**
     * 根据群组id 查询群组成员列表
     * @param id 要查询的id
     * @return
     */
    List<GroupMember> queryMembersByGroupId(String id);
}
