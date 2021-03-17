package com.eleven.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eleven.entity.CircleOss;

import java.util.List;

/**
 * <p>
 * 朋友圈文件表 Mapper 接口
 * </p>
 *
 * @author eleven
 * @since 2021-02-22
 */
public interface CircleOssMapper extends BaseMapper<CircleOss> {

    /**
     * 批量插入
     * @param ossList
     */
    void insertBat(List<CircleOss> ossList);
}
