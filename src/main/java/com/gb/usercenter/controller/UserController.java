package com.gb.usercenter.controller;

import cn.hutool.core.util.StrUtil;
import com.gb.usercenter.common.ResponseData;
import com.gb.usercenter.constant.UserConstant;
import com.gb.usercenter.entity.User;
import com.gb.usercenter.entity.dto.UserDTO;
import com.gb.usercenter.entity.request.UserLoginRequest;
import com.gb.usercenter.entity.request.UserRegisterRequest;
import com.gb.usercenter.mapper.UserMapper;
import com.gb.usercenter.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户接口
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    
    @Resource
    private UserService userService;
    @Resource
    private UserMapper userMapper;

    /**
     * 用户注册
     **/
    @PostMapping("/register")
    public ResponseData register(@RequestBody UserRegisterRequest request){
        if (request == null) {
            return null;
        }
        String userName = request.getUserName();
        String phone = request.getPhone();
        String password = request.getPassword();
        String checkPassword = request.getCheckPassword();
        if (StrUtil.hasEmpty(userName,phone,password,checkPassword)) {
            return ResponseData.fail("必填项不能为空");
        }

        return ResponseData.success(userService.register(userName,phone,password,checkPassword));
    }

    /**
     * 用户登录
     **/
    @PostMapping("/login")
    public ResponseData login(@RequestBody UserLoginRequest request, HttpServletRequest httpServletRequest){
        if (request == null) {
            return ResponseData.fail("请求为空");
        }
        String phone = request.getPhone();
        String password = request.getPassword();

        if (StrUtil.hasEmpty(phone,password)) {
            return ResponseData.fail("必填项不能为空");
        }

        return ResponseData.success(userService.login(phone,password,httpServletRequest));
    }

    /**
     * 根据用户名查询用户列表
     * 模糊查询
     **/
    @GetMapping("/search")
    public ResponseData searchUsers(String username, HttpServletRequest request){
        if (!isAdmin(request)) {
            return ResponseData.fail("没有权限");
        }
        return ResponseData.success(userService.searchUsers(username));
    }

    /**
     * 删除用户
     **/
    @PostMapping("/delete")
    public ResponseData deleteUserById(Long id, HttpServletRequest request){
        if (!isAdmin(request)) {
            return ResponseData.fail("没有权限");
        }
        if (id <= 0) {
            return ResponseData.fail("要删除的用户不存在");
        }
        return ResponseData.success(userService.deleteUserById(id));
    }

    /**
     * 获取当前用户
     **/
    @GetMapping("/current")
    public ResponseData getCurrentUser(HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute(UserConstant.LOGIN_STATE);
        if (currentUser == null) {
            return ResponseData.fail("没有用户");
        }
        User user = userMapper.selectByPrimaryKey(currentUser);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
        return ResponseData.success(userDTO);
    }

    /**
     * 用户注销
     **/
    @PostMapping("/logout")
    public ResponseData logout(HttpServletRequest request){
        if (request == null) {
            return ResponseData.fail("请求为空");
        }
        return ResponseData.success(userService.logout(request));
    }


    private boolean isAdmin(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(UserConstant.LOGIN_STATE);
        return user != null && user.getRole() == UserConstant.ADMIN_ROLE;
    }
}

