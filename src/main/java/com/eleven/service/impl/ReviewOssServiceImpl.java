package com.eleven.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eleven.common.Result;
import com.eleven.common.ResultFactory;
import com.eleven.entity.ReviewOss;
import com.eleven.mapper.ReviewOssMapper;
import com.eleven.service.ReviewOssService;
import com.eleven.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhaojinhui
 * @date 2021/3/14 16:56
 * @apiNote
 */
@Service
public class ReviewOssServiceImpl extends ServiceImpl<ReviewOssMapper, ReviewOss> implements ReviewOssService {

    @Autowired
    private ReviewOssMapper reviewOssMapper;

    SnowFlake snowFlake = SnowFlake.getSnowFlake();

    @Override
    public Result insertReview(ReviewOss reviewOss) {
        reviewOss.setId(snowFlake.nextId());
        reviewOss.setDelFlag("1");
        return ResultFactory.success(reviewOssMapper.insert(reviewOss));
    }

    /**
     * @param reviewOss
     * @return
     * @apiNote
     * @date
     * @author zjh
     */
    @Override
    public Result queryById(ReviewOss reviewOss) {

        return ResultFactory.success(reviewOssMapper.selectById(reviewOss.getId()));
    }

    @Override
    public Result update(ReviewOss reviewOss) {
        return ResultFactory.success(reviewOssMapper.updateById(reviewOss));
    }

    @Override
    public Result delete(String id) {
        ReviewOss reviewOss = new ReviewOss();
        reviewOss.setId(id);
        reviewOss.setDelFlag("0");

        return ResultFactory.success(reviewOssMapper.updateById(reviewOss));
    }
}
