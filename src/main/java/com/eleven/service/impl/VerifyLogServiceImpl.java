package com.eleven.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eleven.common.Result;
import com.eleven.common.ResultFactory;
import com.eleven.entity.VerifyLog;
import com.eleven.mapper.VerifyLogMapper;
import com.eleven.service.VerifyLogService;
import com.eleven.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 验证码记录表 服务实现类
 * </p>
 *
 * @author eleven
 * @since 2021-02-22
 */
@Service
public class VerifyLogServiceImpl extends ServiceImpl<VerifyLogMapper, VerifyLog> implements VerifyLogService {

    @Autowired
    private VerifyLogMapper verifyLogMapper;

    private SnowFlake snowFlake = SnowFlake.getSnowFlake();

    @Override
    public void saveEmailCode(VerifyLog verifyLog) {
        verifyLog.setId(snowFlake.nextId());
        verifyLog.setExpireTime(LocalDateTime.now().plusMinutes(3));
        verifyLog.setType(0);
        verifyLogMapper.insert(verifyLog);
    }

    @Override
    public Result verifyLastCode(VerifyLog verifyLog) {
        return ResultFactory.failed("验证码失效");

    }
}
