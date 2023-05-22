package com.gb.usercenter.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.LogicDelete;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * (User)表实体类
 *
 * @author makejava
 * @since 2023-05-11 14:30:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {
    /**
     * 主键
     **/
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;
    /**
     * 用户名
     **/
    private String userName;
    /**
     * 密码
     **/
    private String userPassword;
    /**
     * 用户头像地址
     **/
    private String avatarUrl;
    /**
     * 性别
     **/
    private Integer gender;
    /**
     * 电话
     **/
    private String phone;
    /**
     * 邮箱
     **/
    private String email;
    /**
     * 用户状态
     **/
    private Integer userStatus;
    /**
     * 创建时间
     **/
    private Date createTime;
    /**
     * 更新时间
     **/
    private Date updateTime;
    /**
     * 是否删除
     **/
    @LogicDelete
    private Integer deleted;
    /**
     * 角色 0 普通用户 1 管理员
     **/
    private Integer role;
}

