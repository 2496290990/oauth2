package com.eleven.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eleven.common.Result;
import com.eleven.common.ResultFactory;
import com.eleven.entity.GroupMember;
import com.eleven.mapper.GroupMemberMapper;
import com.eleven.service.GroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 群组成员表 服务实现类
 * </p>
 *
 * @author eleven
 * @since 2021-02-22
 */
@Service
public class GroupMemberServiceImpl extends ServiceImpl<GroupMemberMapper, GroupMember> implements GroupMemberService {

    @Autowired
    private GroupMemberMapper groupMemberMapper;

    @Override
    public Result queryGroupMember(String id) {
        List<GroupMember> memberList = groupMemberMapper.queryMembersByGroupId(id);
        return ResultFactory.success(memberList);
    }

    @Override
    public Result delMember(GroupMember groupMember) {
        return ResultFactory.success(groupMemberMapper.deleteById(groupMember.getId()));
    }
}
