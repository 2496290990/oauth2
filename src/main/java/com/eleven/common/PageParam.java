package com.eleven.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zhaojinhui
 * @date 2021/3/20 11:48
 * @apiNote
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageParam<T> {
    /** 当前页 */
    private Integer currentPage;

    /** 页展示数量 */
    private Integer pageSize;

    /** 查询出来的总数 */
    private Integer total;

    /** 需要展示的数据 */
    private List<T> data;

}
