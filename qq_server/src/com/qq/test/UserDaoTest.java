package com.qq.test;

import com.qq.DB.DBHelper;
import com.qq.dao.UserDao;
import com.qq.dao.impl.UserDaoImpl;
import com.qq.pojo.User;
import com.qq.util.DateUtil;
import com.qq.util.MD5Util;
import com.qq.util.PhotoUtil;
import com.qq.util.UUIDUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;

public class UserDaoTest {

    Connection connection;

    UserDao userDao= new UserDaoImpl();

    @Before
    public void before(){
        connection=DBHelper.getConnection();
    }

    @After
    public void after() throws Exception{
       connection.close();
    }

    @Test
    public void findUserByUsername() throws Exception{
        User user = new User();
        user.setUsername("photoTest");
        User userBean = userDao.findUserByUsername(user, connection);
        System.out.println(userBean);
    }

    @Test
    public void findUserByUid() throws Exception{
        User user = new User();
        user.setUid("03049a496a824608a18eb2b91b02638c");
        User userBean = userDao.findUserByUid(user, connection);
        System.out.println(userBean);
    }

    @Test
    public void addUser() throws Exception{
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
        String sql="INSERT INTO qq_user(uid,username,password,nickname,signature,gender,birthday,statu,photo,createTime,email,phoneNum,luckyNum,address,test1) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        boolean b = userDao.addUser(user, connection);
        assertTrue(b);
    }

    @Test
    public void updateUser() {
    }

    @Test
    public void updateStatu() {
    }

    @Test
    public void testUpdateStatu() {
    }

    @Test
    public void findAllFriends() throws Exception{
        User user = new User();
        user.setUid("03049a496a824608a18eb2b91b02638c");
        List<User> allFriends = userDao.findAllFriends(user, connection);
        System.out.println(allFriends);
    }

    @Test
    public void findAllOnlineFriends() throws Exception{
        User user = new User();
        user.setUid("03049a496a824608a18eb2b91b02638c");
        List<User> allFriends = userDao.findAllFriends(user, connection);
        System.out.println(allFriends);
    }

}