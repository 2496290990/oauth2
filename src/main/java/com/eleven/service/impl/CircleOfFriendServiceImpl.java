package com.eleven.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eleven.common.Result;
import com.eleven.common.ResultFactory;
import com.eleven.entity.*;
import com.eleven.mapper.CircleOfFriendMapper;
import com.eleven.mapper.CircleOssMapper;
import com.eleven.mapper.MyFriendMapper;
import com.eleven.service.CircleOfFriendService;
import com.eleven.util.ImgUploadUtil;
import com.eleven.util.SecurityUtils;
import com.eleven.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 动态记录表 服务实现类
 * </p>
 *
 * @author eleven
 * @since 2021-02-22
 */
@Service
public class CircleOfFriendServiceImpl extends ServiceImpl<CircleOfFriendMapper, CircleOfFriend> implements CircleOfFriendService {

    @Autowired
    private CircleOfFriendMapper friendMapper;

    @Autowired
    private CircleOssMapper ossMapper;

    @Autowired
    private MyFriendMapper myFriendMapper;

    @Autowired
    private CircleReviewServiceImpl circleReviewService;

    @Override
    public Result queryLast20Circles() {
        LoginUser userInfo = SecurityUtils.getUserInfo();
        List<MyFriend> myFriendList = myFriendMapper.queryMyFriend(userInfo.getAccount());
        List<String> accountList  = new ArrayList<>();
        accountList.addAll(myFriendList.stream()
                .map(m -> m.getFriendAccount())
                .collect(Collectors.toList())
        );
        accountList.add(userInfo.getAccount());
        List<CircleOfFriend> circleOfFriends =  friendMapper.queryLast20Circles(accountList);
        return null;
    }

    @Override
    public Result insertNew(CircleOfFriend circleOfFriend, List<MultipartFile> files) throws IOException {
        SnowFlake snowFlake = SnowFlake.getSnowFlake();
        circleOfFriend.setId(snowFlake.nextId());
        circleOfFriend.setUtterer(SecurityUtils.getUserInfo().getAccount());
        friendMapper.insert(circleOfFriend);
        List<CircleOss> ossList = new ArrayList<>();
        CircleOss oss = new CircleOss();
        if(files != null) {
            for (int i = 0;i < files.size();i++) {
                MultipartFile file = files.get(i);
                ImgUploadOss imgUploadOss = ImgUploadUtil.imgUpload(file);
                oss = new CircleOss();
                oss.setId(snowFlake.nextId());
                oss.setCircleId(circleOfFriend.getId());
                oss.setUrl(imgUploadOss.getUrl());
                oss.setSort(i);
                ossList.add(oss);
            }
            ossMapper.insertBat(ossList);
        }

        return ResultFactory.success(ossList);
    }

    @Override
    public Result queryOwnCircle(CircleOfFriend friend) {
        LoginUser userInfo = SecurityUtils.getUserInfo();
        friend.setUtterer(userInfo.getAccount());
        List<CircleOfFriend> circleOfFriends = friendMapper.queryOwnCircle(friend);
        return ResultFactory.success(getComputerList(circleOfFriends));
    }

    /**
     * 将评论返回成树状结构并且将图片放置在评论中
     * @param list
     * @return
     */
    private List<CircleOfFriend> getComputerList(List<CircleOfFriend> list){
        List<CircleOfFriend> result = new ArrayList<>();
        for (CircleOfFriend friend : list) {
            result.add(getComputedFriend(friend));
        }
        return result;
    }

    private CircleOfFriend getComputedFriend(CircleOfFriend friend){
        //拿到所有的评论集合
        List<CircleReview> reviewList = friend.getCircleReviewList();
        //拿到所有的评论文件集合
        List<ReviewOss> reviewOssList = friend.getReviewOssList();
        //按照评论的id分组
        Map<String, List<ReviewOss>> ossMap = reviewOssList.stream()
                .collect(Collectors.groupingBy(ReviewOss::getReviewId));
        //将文件放到评论里边
        if(CollUtil.isNotEmpty(reviewList)) {
            reviewList = reviewList.stream()
                    .map(m -> {
                        m.setReviewOssList(ossMap.get(m.getId()));
                        return m;
                    }).collect(Collectors.toList());
            List<CircleReview> topList = new ArrayList<>();
            for (Iterator<CircleReview> iterator = reviewList.iterator(); iterator.hasNext(); ) {
                CircleReview review = iterator.next();
                if (review.getParentId() == null) {
                    topList.add(review);
                    iterator.remove();
                }
            }

            for (CircleReview review : topList) {
                setChild(review, reviewList);
            }
            friend.setReviewOssList(null);
            friend.setCircleReviewList(topList);
        }
        return friend;
    }

    //设置子集
    private void setChild(CircleReview review,List<CircleReview> list){
        List<CircleReview> secondList = new ArrayList<>();
        for(Iterator<CircleReview> iterator = list.iterator();iterator.hasNext();){
            CircleReview next = iterator.next();
            if(next.getParentId().equals(review.getId())){
                secondList.add(next);
                iterator.remove();
            }
        }
        review.setChild(secondList);
        if(CollUtil.isNotEmpty(list)){
            for (CircleReview circleReview : secondList) {
                setChild(circleReview, list);
            }
        }
    }
}
