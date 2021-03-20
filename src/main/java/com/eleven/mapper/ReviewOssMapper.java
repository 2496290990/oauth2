package com.eleven.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eleven.entity.ReviewOss;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhaojinhui
 * @date 2021/3/14 16:57
 * @apiNote
 */
public interface ReviewOssMapper extends BaseMapper<ReviewOss> {

    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    ReviewOss selectById(@Param("id") String id);

    /**
     * 批量添加文件
     * @param ossList
     */
    void insertBat(List<ReviewOss> ossList);
}
