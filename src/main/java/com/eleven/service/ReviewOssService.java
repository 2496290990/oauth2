package com.eleven.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eleven.common.Result;
import com.eleven.entity.ReviewOss;


/**
 * @author zhaojinhui
 * @date 2021/3/14 16:55
 * @apiNote
 */
public interface ReviewOssService extends IService<ReviewOss> {

    Result insertReview(ReviewOss reviewOss);

    /**
     * @param reviewOss
     * @return
     * @apiNote
     * @date
     * @author zjh
     */
    Result queryById(ReviewOss reviewOss);

    Result update(ReviewOss reviewOss);

    Result delete(String id);
}
