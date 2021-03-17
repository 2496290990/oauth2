package com.eleven.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eleven.common.Result;
import com.eleven.entity.VerifyLog;

/**
 * <p>
 * 验证码记录表 服务类
 * </p>
 *
 * @author eleven
 * @since 2021-02-22
 */
public interface VerifyLogService extends IService<VerifyLog> {

    /**
     * 发送邮箱验证码
     * @param verifyLog
     */
    void saveEmailCode(VerifyLog verifyLog);

    /**
     * 验证最后一个验证码是否正确
     * @param verifyLog 要验证的验证码主体
     * @return
     */
    Result verifyLastCode(VerifyLog verifyLog);
}
