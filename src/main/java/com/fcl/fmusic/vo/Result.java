package com.fcl.fmusic.vo;

import lombok.Data;

import java.util.List;

@Data
public class Result<T> {
    private Integer code;       //状态返回码
    private String message;         //状态返回消息
    private T data;        //返回数据模型
    private Long count;         //分页查询的总记录数

    private Result(){}

    private Result(Integer code, String msg, T dataModel, Long count){
        this.code = code;
        this.message = msg;
        this.data = dataModel;
        this.count = count;
    }

    public static Result<Object> success(Object data, Long count){
        return new Result(0,"success",data,count);
    }

    //验证成功
    public static Result<Object> success(){
        return new Result<>(200,"success",null,null);
    }

    public static Result<Object> success(String msg) {
        return new Result<>(200, msg, null, null);
    }

    //验证失败
    public static Result<Object> fail(){
        return new Result<>(400,"fail",null,null);
    }

    //验证失败 自定义失败信息
    public static Result<Object> fail(String msg){
        return new Result<>(400,msg,null,null);
    }
}
