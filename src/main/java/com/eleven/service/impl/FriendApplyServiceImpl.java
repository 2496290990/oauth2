package com.eleven.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eleven.common.Result;
import com.eleven.common.ResultFactory;
import com.eleven.entity.FriendApply;
import com.eleven.mapper.FriendApplyMapper;
import com.eleven.service.FriendApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 好友申请记录表 服务实现类
 * </p>
 *
 * @author eleven
 * @since 2021-02-22
 */
@Service
public class FriendApplyServiceImpl extends ServiceImpl<FriendApplyMapper, FriendApply> implements FriendApplyService {

    @Autowired
    private FriendApplyMapper friendApplyMapper;

    @Override
    public Result applyToAdd(FriendApply friendApply) {
        friendApplyMapper.insert(friendApply);
        // TODO: 2021/2/23 发送消息有好友申请
        return ResultFactory.success(null);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Result operationApply(FriendApply friendApply) {

        friendApply.setUpdateBy(friendApply.getFriendId());
        friendApplyMapper.updateById(friendApply);
        //如果同意的话
        if(friendApply.getState() == 1){

        }else{

        }
        return ResultFactory.success(null);
    }
}
