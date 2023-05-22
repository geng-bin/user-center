package com.gb.usercenter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.regex.Pattern;

@SpringBootTest
class UserCenterApplicationTests {

    @Test
    void contextLoads() {
        String regex = "^1[3-9]\\d{9}$";
        Pattern p = Pattern.compile(regex);
        System.out.println(p.matcher("18847160591").matches());
        String usernameRegex = "^[a-zA-Z0-9_]+$";
        String username = "dsafg&sda";
        System.out.println(username.matches(usernameRegex));
    }

}
