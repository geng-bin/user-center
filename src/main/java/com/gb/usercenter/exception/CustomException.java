package com.gb.usercenter.exception;

import lombok.Data;

@Data
public class CustomException extends RuntimeException {

    /**
     * 异常状态码
     */
    private String code;

    /**
     * 异常信息
     */
    private String msg;

    public CustomException(String message) {
        super(message);
        this.msg = message;
    }

    public CustomException(String code, String message) {
        super(message);
        this.code = code;
        this.msg = message;
    }
}