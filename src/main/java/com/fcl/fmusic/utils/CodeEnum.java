package com.fcl.fmusic.utils;

/**
 * 状态码枚举类
 */
public enum CodeEnum {
    SUCCESS("200","SUCCESS"),
    ERROR("400","ERROR");
    public String code;
    public String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    CodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
