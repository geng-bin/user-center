package com.gb.usercenter.entity.request;

import lombok.Data;

@Data
public class UserRegisterRequest {
    private String userName;
    private String phone;
    private String password;
    private String checkPassword;
}
