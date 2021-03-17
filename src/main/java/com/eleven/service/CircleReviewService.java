package com.eleven.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eleven.common.Result;
import com.eleven.entity.CircleReview;

/**
 * @author zhaojinhui
 * @date 2021/3/14 16:29
 * @apiNote
 */

public interface CircleReviewService extends IService<CircleReview> {

    /**
     * @param circleReview 评论内容
     * @return  Result
     * @apiNote 创建一条信的评论
     * @date   2021年3月14日17:37:49
     * @author zjh
     */
    Result insertReview(CircleReview circleReview);

    /**
     * 根据id查询评论
     * @param review
     * @return
     */
    Result queryCircleReview(CircleReview review);

    /**
     * 根据id查询评论
     * @param id
     * @return
     */
    Result queryCircleReview(String id);

    Result updateCircleReviewById(CircleReview review);

    Result deleteCircleReview(CircleReview circleReview);

    Result deleteCircleReview(String id);

}
