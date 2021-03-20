package com.eleven.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eleven.entity.CircleOfFriend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 动态记录表 Mapper 接口
 * </p>
 *
 * @author eleven
 * @since 2021-02-22
 */
public interface CircleOfFriendMapper extends BaseMapper<CircleOfFriend> {

    /**
     * 查询最后20条朋友圈
     * @return
     */
    List<CircleOfFriend> queryLast20Circles(@Param("list") List<String> ids);

    /**
     * 分页查询自己的朋友圈信息
     * @param account 账号信息
     * @return
     */
    List<CircleOfFriend> queryOwnCircle(CircleOfFriend friend);
}
