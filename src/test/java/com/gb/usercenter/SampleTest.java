package com.gb.usercenter;

import com.gb.usercenter.entity.User;
import com.gb.usercenter.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tk.mybatis.mapper.util.Assert;

import java.util.List;

@SpringBootTest
public class SampleTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        User user = new User();
        user.setId(7L);
        user.setUserName("fgaasd");
        System.out.println(userMapper.selectByPrimaryKey(user));
    }

}