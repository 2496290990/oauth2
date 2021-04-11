package com.eleven.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eleven.common.Result;
import com.eleven.common.ResultFactory;
import com.eleven.entity.CircleReview;
import com.eleven.entity.ImgUploadOss;
import com.eleven.entity.LoginUser;
import com.eleven.entity.ReviewOss;
import com.eleven.mapper.CircleReviewMapper;
import com.eleven.mapper.ReviewOssMapper;
import com.eleven.service.CircleReviewService;
import com.eleven.service.CommonService;
import com.eleven.util.SecurityUtils;
import com.eleven.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaojinhui
 * @date 2021/3/14 16:30
 * @apiNote
 */
@Service
public class CircleReviewServiceImpl extends ServiceImpl<CircleReviewMapper, CircleReview> implements CircleReviewService {

    @Autowired
    private CircleReviewMapper reviewMapper;

    @Autowired
    private CommonService commonService;

    @Autowired
    private ReviewOssMapper ossMapper;

    private SnowFlake snowFlake = SnowFlake.getSnowFlake();

    /**
     * @param
     * @return
     * @apiNote
     * @date
     * @author zjh
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Result insertReview(CircleReview circleReview) {
        if(StrUtil.isBlank(circleReview.getReviewContent())){
            return ResultFactory.failed("回复内容不能为空");
        }
        LoginUser userInfo = SecurityUtils.getUserInfo();
        circleReview.setReviewId(userInfo.getAccount());
        circleReview.setId(snowFlake.nextId());
        circleReview.setDelFlag("1");
        int insert = reviewMapper.insert(circleReview);
        return ResultFactory.success("发表评论成功");
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
