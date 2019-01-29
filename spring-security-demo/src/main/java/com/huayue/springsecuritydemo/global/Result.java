package com.huayue.springsecuritydemo.global;

import lombok.Data;

/**
 * @Author: koujinhong
 * @Description:
 * @Date: Create in 17:07 2018/11/13
 */
@Data
public class Result {
    private int code = 0;
    private String message = "成功";
    private Object data;

    public Result() {
    }

    public Result(int code) {
        this.code = code;
    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(int code, String message, Object data) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public Result(Object data) {
        this.data = data;
    }

    public static Result success() {
        return new Result();
    }

    public static Result success(Object data) {
        return new Result(data);
    }

    public static Result failure() {
        return new Result(-1, "失败", null);
    }

    public static Result failure(String message) {
        return new Result(-1, message);
    }

    public static Result failure(int code, String message) {
        return new Result(code, message);
    }

    public static Result of(int code, String message) {
        return new Result(code, message);
    }

    public static Result of(int code, String message, Object data) {
        return new Result(code, message, data);
    }
}

