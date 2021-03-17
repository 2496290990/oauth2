package com.eleven.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eleven.common.Result;
import com.eleven.common.ResultFactory;
import com.eleven.entity.ChatGroup;
import com.eleven.entity.LoginUser;
import com.eleven.mapper.ChatGroupMapper;
import com.eleven.service.ChatGroupService;
import com.eleven.util.SecurityUtils;
import com.eleven.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 聊天群组表 服务实现类
 * </p>
 *
 * @author eleven
 * @since 2021-02-22
 */
@Service
public class ChatGroupServiceImpl extends ServiceImpl<ChatGroupMapper, ChatGroup> implements ChatGroupService {

    @Autowired
    private ChatGroupMapper chatGroupMapper;

    private SnowFlake snowFlake = SnowFlake.getSnowFlake();

    @Override
    public Result createNewGroup(ChatGroup chatGroup) {
        LoginUser userInfo = SecurityUtils.getUserInfo();
        chatGroup.setId(snowFlake.nextId());
        chatGroup.setCreateId(userInfo.getId());
        int insert = chatGroupMapper.insert(chatGroup);
        if(insert > 0){
            return ResultFactory.success(chatGroup.getId());
        }
        return ResultFactory.failed("创建群组失败");
    }

    @Override
    public Result queryGroupById(String id) {
        ChatGroup chatGroup = chatGroupMapper.selectById(id);
        return ResultFactory.success(chatGroup);
    }

    @Override
    public Result editGroupById(ChatGroup chatGroup) {
        return ResultFactory.success(chatGroupMapper.updateById(chatGroup));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Result delGroup(ChatGroup chatGroup) {

        return null;
    }
}
