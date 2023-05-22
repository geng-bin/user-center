package com.gb.usercenter.service.impl

import com.gb.usercenter.entity.User
import com.gb.usercenter.mapper.UserMapper
import com.gb.usercenter.service.UserService
import com.sun.org.apache.bcel.internal.generic.IAND
import spock.lang.Specification

import javax.annotation.Resource

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
            def id = userService.register(userName,phone,password,checkPassword)
        then: "register success"
            userMapper.selectCount(_ as User) >> 0
            userMapper.insertSelective(_ as User) >> {
                User user ->
                    user.id = 1
                    user
            }
    }





    def "Login"() {
    }

    def "SearchUsers"() {
    }

    def "DeleteUserById"() {
    }

    def "Logout"() {
    }
}
