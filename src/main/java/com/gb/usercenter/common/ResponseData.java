package com.gb.usercenter.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData<T> {

    private Integer code;

    private T data;

    private String msg;

    public static final int SUCCESS_CODE = 1;
    public static final String SUCCESS_MSG = "SUCCESS";
    public static final int FAIL_CODE = 2;
    public static final String FAIL_MSG = "FAIL";


    public static <T> ResponseData<T> success(T data){
        return new ResponseData<T>(SUCCESS_CODE, data, SUCCESS_MSG);
    }

    public static ResponseData success(){
        ResponseData responseData = new ResponseData();
        responseData.setCode(SUCCESS_CODE);
        responseData.setMsg(SUCCESS_MSG);
        return responseData;
    }
    public static <T> ResponseData fail() {
        ResponseData responseData = new ResponseData();
        responseData.setCode(FAIL_CODE);
        responseData.setMsg(FAIL_MSG);
        return responseData;
    }

    public static <T> ResponseData fail(String msg) {
        ResponseData responseData = new ResponseData();
        responseData.setCode(FAIL_CODE);
        responseData.setMsg(msg);
        return responseData;
    }
}
