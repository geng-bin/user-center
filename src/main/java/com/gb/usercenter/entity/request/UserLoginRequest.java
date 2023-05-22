package com.gb.usercenter.entity.request;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String phone;
    private String password;
}
