package com.eleven.controller;


import com.eleven.common.Result;
import com.eleven.entity.ReviewOss;
import com.eleven.service.ReviewOssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhaojinhui
 * @date 2021/3/14 16:49
 * @apiNote
 */
@RestController
@RequestMapping("/reviewOss")
public class ReviewOssController {

    @Autowired
    private ReviewOssService ossService;

    @PostMapping("/insert")
    public Result insertResult(@RequestBody ReviewOss reviewOss){
        return ossService.insertReview(reviewOss);

    }

    @GetMapping("/query")
    public Result queryReviewOss(ReviewOss reviewOss){
        //return ResultFactory.success(ossService.getById(reviewOss.getId()));
        return ossService.queryById(reviewOss);
    }

    /**
     * 添加了@RequestBody的是接受的json参数的
     * 不添加的是直接接收参数的，通过url传值
     * @param reviewOss
     * @return
     */
    @PutMapping("/update")
    public Result updateReviewOss(@RequestBody ReviewOss reviewOss){
        return ossService.update(reviewOss);
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteReviewOss(@PathVariable("id") String id){
        return ossService.delete(id);
    }

}
