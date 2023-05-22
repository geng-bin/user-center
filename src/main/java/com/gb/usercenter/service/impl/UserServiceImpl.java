package com.gb.usercenter.service.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.gb.usercenter.constant.UserConstant;
import com.gb.usercenter.entity.User;
import com.gb.usercenter.entity.dto.UserDTO;
import com.gb.usercenter.exception.CustomException;
import com.gb.usercenter.mapper.UserMapper;
import com.gb.usercenter.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import tk.mybatis.mapper.weekend.Weekend;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2023-05-11 11:10:04
 */
@Service("userService")
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    public static final String SALT = "java";



    @Override
    public Long register(String userName, String phone, String password, String checkPassword) {
        //校验
        if (StrUtil.isEmpty(phone) || StrUtil.isEmpty(password) || StrUtil.isEmpty(checkPassword) || StrUtil.isEmpty(userName)) {
            throw new CustomException("必填项不能为空");
        }
        if (password.length() < 6) {
            throw new CustomException("密码不能少于6位");
        }
        if (!password.equals(checkPassword)) {
            throw new CustomException("两次密码不一致");
        }
        String phoneRegex = "^1[3-9]\\d{9}";
        if (!phone.matches(phoneRegex)) {
            throw new CustomException("手机号格式错误");
        }
        String userNameRegex = "^[a-zA-Z0-9_]+$";
        if (!userName.matches(userNameRegex)) {
            throw new CustomException("用户名由数字、字母、下划线组成");
        }

        User queryPone = new User();
        queryPone.setPhone(phone);

        if (userMapper.selectCount(queryPone) > 0) {
            throw new CustomException("手机号已注册");
        }
        User queryUsername = new User();
        queryUsername.setUserName(userName);
        if (userMapper.selectCount(queryUsername) > 0) {
            throw new CustomException("用户名已被注册");
        }

        User user = new User();
        user.setUserName(userName);
        user.setPhone(phone);
        //密码加密
        user.setUserPassword(SecureUtil.md5(SALT + password));
        userMapper.insertSelective(user);
        return user.getId();
    }

    @Override
    public UserDTO login(String phone, String password, HttpServletRequest request) {
        //校验
        if (StrUtil.isEmpty(phone) || StrUtil.isEmpty(password)) {
            throw new CustomException("请输入手机号和密码");
        }
        String phoneRegex = "^1[3-9]\\d{9}";
        if (!phone.matches(phoneRegex)) {
            throw new CustomException("手机号格式错误");
        }
        if (password.length() < 6) {
            throw new CustomException("密码不能少于6位");
        }
        User loginUser = new User();
        loginUser.setPhone(phone);
        loginUser.setUserPassword(SecureUtil.md5(SALT + password));
        User user = userMapper.selectOne(loginUser);
        if (user == null) {
            throw new CustomException("手机号或密码错误");
        }
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user,userDTO);

        return userDTO;
    }

    @Override
    public List<UserDTO> searchUsers(String username) {
        Weekend<User> condition = new Weekend<>(User.class);
        condition.weekendCriteria().andLike(User::getUserName,username);
        List<User> users = userMapper.selectByExample(condition);
        List<UserDTO> userDTOS = new ArrayList<>();
        UserDTO userDTO = new UserDTO();
        for (User user : users) {
            BeanUtils.copyProperties(user,userDTO);
            userDTOS.add(userDTO);
        }

        return userDTOS;
    }

    @Override
    public Boolean deleteUserById(Long id) {
        User user = new User();
        user.setId(id);
        int res = userMapper.deleteByPrimaryKey(user);
        return res > 0;
    }


    @Override
    public Integer logout(HttpServletRequest request) {
        request.getSession().removeAttribute(UserConstant.LOGIN_STATE);
        return 1;
    }
}

