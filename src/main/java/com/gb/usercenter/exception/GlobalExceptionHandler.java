package com.gb.usercenter.exception;

import com.gb.usercenter.common.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseData customExceptionHandler(CustomException e) {
        log.error("customException: " + e.getMsg(), e);
        return ResponseData.fail(e.getMsg());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseData runtimeExceptionHandler(RuntimeException e) {
        log.error("customException: " + e.getMessage(), e);
        return ResponseData.fail(e.getMessage());
    }

}
