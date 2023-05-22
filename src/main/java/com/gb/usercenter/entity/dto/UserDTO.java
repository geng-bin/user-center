package com.gb.usercenter.entity.dto;

import lombok.Data;
import tk.mybatis.mapper.annotation.LogicDelete;

import java.util.Date;

@Data
public class UserDTO {
    private Long id;

    private String userName;

    private String avatarUrl;

    private Integer gender;

    private String phone;

    private String email;

    private Integer userStatus;

    private Integer role;
}
