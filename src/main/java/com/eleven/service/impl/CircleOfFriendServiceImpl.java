package com.eleven.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eleven.common.Result;
import com.eleven.common.ResultFactory;
import com.eleven.entity.CircleOfFriend;
import com.eleven.entity.CircleOss;
import com.eleven.entity.ImgUploadOss;
import com.eleven.entity.LoginUser;
import com.eleven.mapper.CircleOfFriendMapper;
import com.eleven.mapper.CircleOssMapper;
import com.eleven.service.CircleOfFriendService;
import com.eleven.util.ImgUploadUtil;
import com.eleven.util.SecurityUtils;
import com.eleven.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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



    @Override
    public Result queryLast20Circles() {
        LoginUser userInfo = SecurityUtils.getUserInfo();
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
        return null;
    }
}
