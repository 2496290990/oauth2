package com.eleven.controller;

import com.eleven.common.Result;
import com.eleven.entity.CircleReview;
import com.eleven.service.CircleReviewService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author zhaojinhui
 * @date 2021/3/14 16:29
 * @apiNote
 */
@RestController
@RequestMapping("/review")
@Api(tags = "朋友圈评论控制器")
public class CircleReviewController {

    @Autowired
    private CircleReviewService circleReviewService;

    /**
     * 新增评论
     * @param circleReview
     * @return
     */
    @PostMapping("/insert")
    public Result insertCircleReview(CircleReview circleReview, List<MultipartFile>files){
        return circleReviewService.insertReview(circleReview,files);
    }

    @GetMapping("/query")
    public Result queryCircleReview(CircleReview review){
        return circleReviewService.queryCircleReview(review);
    }

    @GetMapping("/{id}")
    public Result queryCircleReview(@PathVariable("id") String id ){
        return circleReviewService.queryCircleReview(id);
    }

    @PutMapping("/update")
    public  Result updateCircleReview(@RequestBody CircleReview circleReview){
        return circleReviewService.updateCircleReviewById(circleReview);
    }

    @DeleteMapping("/delete")
    public  Result deleteCircleReview(@RequestBody CircleReview circleReview){
        return circleReviewService.deleteCircleReview(circleReview);
    }
    @DeleteMapping("/{id}")
    public  Result deleteCircReview(@PathVariable("id") String id ){
        return  circleReviewService.deleteCircleReview(id);
    }
    @DeleteMapping("/del")
    public Result  delCircleReview(CircleReview review){
        return  circleReviewService.deleteCircleReview(review);
    }



}
