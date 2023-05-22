package com.gb.usercenter.service;

import com.gb.usercenter.entity.User;
import com.gb.usercenter.entity.dto.UserDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2023-05-11 11:10:04
 */
public interface UserService {



    /**
     * 用户注册接口
     * 参数: 手机号 密码 二次确认密码
     **/
    Long register(String userName, String phone, String password, String checkPassword);

    /**
     * 用户登录
     **/
    UserDTO login(String phone, String password, HttpServletRequest request);

    /**
     * 根据用户名查询用户列表
     * 模糊查询
     **/
    List<UserDTO> searchUsers(String username);

    /**
     * 删除用户
     **/
    Boolean deleteUserById(Long id);

    /**
     * 用户注销
     **/
    Integer logout(HttpServletRequest request);
}

