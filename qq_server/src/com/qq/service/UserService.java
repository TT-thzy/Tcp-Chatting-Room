package com.qq.service;

import com.qq.pojo.User;

import java.util.List;

/**
 * @program: mybatisTest
 * @description:
 * @author: Mr.Wang
 * @create: 2021-03-27 17:26
 **/
public interface UserService {

    //登录
    public boolean login(User user);
    //查询所有好友
    public List<User> findAllFriends(User user);
    //据用户名查询用户详细信息
    public User findUserByUsername(User user);
    //注册
    public boolean register(User user);
    //判断用户是否存在
    boolean isExistUser(User user);
    //判断问题是否回答正确
    boolean isAnswerSuccess(User user,String lucky);
    //修改密码
    boolean updatePassword(User user);
    //修改头像
    boolean updatePhoto(User user);
    //修改用户信息
    boolean updateUser(User user);
    //修改用户状态
    boolean updateUserStatus(User user,int status);
    //查询所有在线用户
    public List<User> findAllOnlineFriends(User user);
}
