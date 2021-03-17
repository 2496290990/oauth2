package com.eleven.common;

/**
 * @author zhaojinhui
 * @date 2021/3/13 15:35
 * @apiNote
 */
public class ResultFactory {

    public static Result success(Object data){
        return new Result(200,"success",data);
    }

    public static Result failed(Object data){
        return new Result(500, "fail", data);
    }

    public static Result buildResult(Integer code,String message,Object data){
        return new Result(code, message, data);
    }
}
