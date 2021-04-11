package com.eleven.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.annotation.TableField;
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
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
            if(CollUtil.isNotEmpty(ossList)) {
                ossMapper.insertBat(ossList);
            }
        }

        return ResultFactory.success(ossList);
    }

    @Override
    public Result queryOwnCircle(CircleOfFriend friend) {
        LoginUser userInfo = SecurityUtils.getUserInfo();
        List<String> accounts = new ArrayList<>();
        accounts.add(userInfo.getAccount());
        List<CircleOfFriend> circleOfFriends = friendMapper.getCircleList(accounts);
        return ResultFactory.success(circleOfFriends);
    }

    @Override
    public Result getCircleList() {
        //获取当前登录人账号以及好有账号
        LoginUser userInfo = SecurityUtils.getUserInfo();
        String account = userInfo.getAccount();
        List<String> accountList = new ArrayList<>();
        List<MyFriend> myFriendList = myFriendMapper.getMyFriendList(account);
        List<String> friendAccountList = myFriendList.stream().map(m -> m.getFriendAccount()).collect(Collectors.toList());
        accountList.add(account);
        accountList.addAll(friendAccountList);

        List<CircleOfFriend> circleList = friendMapper.getCircleList(accountList);
        return ResultFactory.success(circleList);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Result publishCircle(CircleOfFriend circleOfFriend) {
        LoginUser userInfo = SecurityUtils.getUserInfo();
        SnowFlake snowFlake = SnowFlake.getSnowFlake();
        circleOfFriend.setUtterer(userInfo.getAccount());
        circleOfFriend.setId(snowFlake.nextId());
        int insert = friendMapper.insert(circleOfFriend);
        List<String> urlList = circleOfFriend.getUrlList();
        if(CollUtil.isNotEmpty(urlList)){
            List<CircleOss> ossList = new ArrayList<>();
            CircleOss oss = null;
            int i = 0;
            for (String url : urlList) {
                oss = new CircleOss();
                oss.setId(snowFlake.nextId());
                oss.setCircleId(circleOfFriend.getId());
                oss.setSort(i++);
                oss.setUrl(url);
                ossList.add(oss);
            }
            if(CollUtil.isNotEmpty(ossList)){
                ossMapper.insertBat(ossList);
            }
        }
        return insert > 0 ?
                ResultFactory.success("发表成功") :
                ResultFactory.failed("发表失败，请联系管理员");
    }



}
