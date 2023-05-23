package com.gb.usercenter.service.impl

import com.gb.usercenter.entity.User
import com.gb.usercenter.exception.CustomException
import com.gb.usercenter.mapper.UserMapper
import com.gb.usercenter.service.UserService
import spock.lang.Specification

import javax.servlet.http.HttpServletRequest

class UserServiceImplTest extends Specification {

    def "Register - success"() {
        given: "init param"
        def userMapper = Mock(UserMapper)
        def userService = Mock(UserService)
        def userName = "张三"
        def phone = "18847160594"
        def password = "123456"
        def checkPassword = "123456"

        when: "call register method"
        def id = userService.register(userName, phone, password, checkPassword)

        then: "register success"
        userMapper.selectCount(_ as User) >> 0
        userMapper.insertSelective(_ as User) >> {
            User user ->
                user.id = 1
                user
        }
    }


    def "Login 异常情况 "() {
        given:
        def userService = new UserServiceImpl()
        def request = Mock(HttpServletRequest)
        when: "调用 login 方法"
        userService.login(phone, password, request)

        then: "捕获异常"
        def exception = thrown(expectedException)
        exception.message == expectedMessage

        where: "校验参数"
        phone         | password || expectedException || expectedMessage
        ""            | ""       || CustomException   || "请输入手机号和密码"
        ""            | "test"   || CustomException   || "请输入手机号和密码"
        "18847160591" | ""       || CustomException   || "请输入手机号和密码"
        "18847160591" | "test"   || CustomException   || "密码不能少于6位"
        "11543536354" | "test"   || CustomException   || "手机号格式错误"
        "13543-36354" | "test"   || CustomException   || "手机号格式错误"
    }

    def "SearchUsers"() {
    }

    def "DeleteUserById"() {
    }

    def "Logout"() {
    }
}
