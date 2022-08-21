package com.qq.test;

import com.qq.DB.DBHelper;
import com.qq.pojo.User;
import com.qq.util.DateUtil;
import com.qq.util.MD5Util;
import com.qq.util.PhotoUtil;
import com.qq.util.UUIDUtil;
import org.junit.Test;

import java.io.File;
import java.sql.Date;

/**
 * @program: qq_server
 * @description:
 * @author: Mr.Wang
 * @create: 2021-09-10 09:47
 **/
public class DBTest {
    @Test
    public void testInsert() throws Exception{
        User user = new User();
        user.setUid(UUIDUtil.getUUID());
        user.setUsername("root");
        user.setPassword(MD5Util.encode("root"));
        user.setNickname("root");
        user.setSignature("root");
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
        DBHelper.update(DBHelper.getConnection(),sql,
                user.getUid(),user.getUsername(), user.getPassword(),user.getNickname(),user.getSignature(),user.getGender(),user.getBirthday(),user.getStatu(),user.getPhoto(),user.getCreateTime(),user.getEmail(),user.getPhoneNum(),user.getLuckyNum(),user.getAddress(),user.getTest1());
    }
}
