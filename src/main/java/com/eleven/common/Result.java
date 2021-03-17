package com.eleven.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zhaojinhui
 * @date 2021/3/13 15:33
 * @apiNote
 */
@Data
@AllArgsConstructor
public class Result {

    private Integer code;

    private String message;

    private Object data;

}
