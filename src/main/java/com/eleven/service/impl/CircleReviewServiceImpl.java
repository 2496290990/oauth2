package com.eleven.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eleven.common.Result;
import com.eleven.common.ResultFactory;
import com.eleven.entity.CircleReview;
import com.eleven.mapper.CircleReviewMapper;
import com.eleven.service.CircleReviewService;
import com.eleven.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhaojinhui
 * @date 2021/3/14 16:30
 * @apiNote
 */
@Service
public class CircleReviewServiceImpl extends ServiceImpl<CircleReviewMapper, CircleReview> implements CircleReviewService {

    @Autowired
    private CircleReviewMapper reviewMapper;

    private SnowFlake snowFlake = SnowFlake.getSnowFlake();

    /**
     * @param
     * @return
     * @apiNote
     * @date
     * @author zjh
     */
    @Override
    public Result insertReview(CircleReview circleReview) {
        circleReview.setId(snowFlake.nextId());
        circleReview.setDelFlag("1");
        int insert = reviewMapper.insert(circleReview);
        return ResultFactory.success(insert);
    }

    @Override
    public Result queryCircleReview(CircleReview review) {
        CircleReview circleReview = reviewMapper.selectById(review.getId());
        return ResultFactory.success(circleReview);
    }

    @Override
    public Result queryCircleReview(String id) {
        return ResultFactory.success(reviewMapper.selectById(id));
    }

    @Override
    public Result updateCircleReviewById(CircleReview review) {
        return ResultFactory.success(reviewMapper.updateById(review));
    }

    @Override
    public Result deleteCircleReview(CircleReview circleReview) {
        circleReview.setDelFlag("0");
        return ResultFactory.success(reviewMapper.updateById(circleReview));
    }

    @Override
    public Result deleteCircleReview(String id) {
        CircleReview circleReview = new CircleReview();
        circleReview.setId(id);
        circleReview.setDelFlag("0");
        return ResultFactory.success(reviewMapper.updateById(circleReview));
    }

}
