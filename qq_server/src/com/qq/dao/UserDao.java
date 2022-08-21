package com.qq.dao;

import com.qq.pojo.User;

import java.sql.Connection;
import java.util.List;

/**
 * @program: mybatisTest
 * @description:
 * @author: Mr.Wang
 * @create: 2021-03-27 17:26
 **/
public interface UserDao {

    //根据账号查询某个用户
    User findUserByUsername(User user, Connection connection) throws Exception;
    //根据uid查询某个用户
    User findUserByUid(User user, Connection connection) throws Exception;
    //插入一条用户信息
    boolean addUser(User user, Connection connection) throws Exception;
    //修改一条用户数据
    boolean updateUser(User user, Connection connection) throws Exception;
    //修改用户状态
    boolean updateStatu(User user,int status, Connection connection) throws Exception;
    //修改用户密码
    boolean updatePassword(User user, Connection connection) throws Exception;
    //修改用户头像
    boolean updatePhoto(User user, Connection connection) throws Exception;
    //查询某用户的所有好友
    List<User> findAllFriends(User user, Connection connection) throws Exception;
    //查询某用户的所有在线的好友
    List<User> findAllOnlineFriends(User user, Connection connection) throws Exception;
}
