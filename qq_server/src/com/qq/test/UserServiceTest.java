package com.qq.test;

import com.qq.pojo.User;
import com.qq.service.UserService;
import com.qq.service.impl.UserServiceImpl;
import com.qq.util.DateUtil;
import com.qq.util.MD5Util;
import com.qq.util.PhotoUtil;
import com.qq.util.UUIDUtil;
import org.junit.Test;

import java.io.File;
import java.sql.Date;

import static org.junit.Assert.*;

public class UserServiceTest {

    UserService userService =new UserServiceImpl();

    @Test
    public void login() {
        User user = new User();
        user.setUsername("photoTest");
        user.setPassword("123456");
        boolean login = userService.login(user);
        assertTrue(login);
    }

    @Test
    public void register(){
        User user = new User();
        user.setUid(UUIDUtil.getUUID());
        user.setUsername("wt");
        user.setPassword(MD5Util.encode("wt"));
        user.setNickname("wt");
        user.setSignature("wt");
        user.setGender("男");
        java.util.Date d = DateUtil.stringToDate("2000年03月19日", "yyyy年MM月dd日");
        user.setStatu(1);
        user.setPhoto(PhotoUtil.transform(new File("resources\\image\\uicon\\ww.png")));
        user.setCreateTime(new Date(new java.util.Date().getTime()));
        user.setEmail("1458632218@qq.com");
        user.setPhoneNum("11213214");
        user.setLuckyNum("5");
        user.setAddress("");
        user.setTest1(2);
        boolean register = userService.register(user);
        assertTrue(register);
    }
}