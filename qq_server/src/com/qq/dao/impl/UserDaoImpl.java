package com.qq.dao.impl;

import com.qq.DB.DBHelper;
import com.qq.dao.UserDao;
import com.qq.pojo.User;

import java.sql.Connection;
import java.util.List;

/**
 * @program: qq_server
 * @description:
 * @author: Mr.Wang
 * @create: 2021-09-10 12:37
 **/
public class UserDaoImpl implements UserDao {
    //根据账号查询某个用户
    @Override
    public User findUserByUsername(User user, Connection connection) throws Exception {
        String username = user.getUsername();
        String sql="SELECT * FROM qq_user WHERE username=?";
        User userBean = DBHelper.selectForBean(connection, User.class, sql, username);
        return userBean;
    }
    //根据uid查询某个用户
    @Override
    public User findUserByUid(User user, Connection connection) throws Exception {
        String uid=user.getUid();
        String sql="SELECT * FROM qq_user WHERE uid=?";
        User userBean = DBHelper.selectForBean(connection, User.class, sql, uid);
        return userBean;
    }
    //插入一条用户信息
    @Override
    public boolean addUser(User user, Connection connection) throws Exception {
        String sql="INSERT INTO qq_user(uid,username,password,nickname,signature,gender,birthday,statu,photo,createTime,email,phoneNum,luckyNum,address,test1) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        boolean update = DBHelper.update(connection, sql,
                user.getUid(), user.getUsername(), user.getPassword(), user.getNickname(), user.getSignature(), user.getGender(), user.getBirthday(), user.getStatu(), user.getPhoto(), user.getCreateTime(), user.getEmail(), user.getPhoneNum(), user.getLuckyNum(), user.getAddress(), user.getTest1());
        return update;
    }
    //修改一条用户数据
    @Override
    public boolean updateUser(User user, Connection connection) throws Exception {
        String sql="update qq_user set nickname=?,gender=?,birthday=?,signature=? where username=?";
        boolean update = DBHelper.update(connection, sql, user.getNickname(), user.getGender(), user.getBirthday(), user.getSignature(), user.getUsername());
        return update;
    }
    //修改用户状态
    @Override
    public boolean updateStatu(User user, int status, Connection connection) throws Exception {
        String sql="update qq_user set statu=? where username=?";
        boolean update = DBHelper.update(connection, sql, status, user.getUsername());
        return update;
    }
    //修改用户密码
    @Override
    public boolean updatePassword(User user, Connection connection) throws Exception {
        String sql="update qq_user set password=? where username=?";
        boolean update = DBHelper.update(connection, sql, user.getPassword(), user.getUsername());
        return update;
    }
    //修改用户头像
    @Override
    public boolean updatePhoto(User user, Connection connection) throws Exception {
        String sql="update qq_user set photo=? where username=?";
        boolean update = DBHelper.update(connection, sql, user.getPhoto(), user.getUsername());
        return update;
    }
    //查询某用户的所有好友
    @Override
    public List<User> findAllFriends(User user, Connection connection) throws Exception {
        String sql="SELECT qq_user.* FROM qq_user RIGHT JOIN (SELECT userid AS `friend` FROM qq_friendship WHERE friendId=? UNION SELECT friendId AS `friend` FROM qq_friendship WHERE userid=?) AS `friendship`ON friendship.friend=qq_user.`uid`";
        List<User> userList = DBHelper.selectForList(connection, User.class, sql, user.getUid(), user.getUid());
        return userList;
    }
    //查询某用户的所有在线的好友
    @Override
    public List<User> findAllOnlineFriends(User user, Connection connection) throws Exception {
        String sql="SELECT qq_user.* FROM qq_user RIGHT JOIN (SELECT userid AS `friend` FROM qq_friendship WHERE friendId=? UNION SELECT friendId AS `friend` FROM qq_friendship WHERE userid=?) AS `friendship`ON friendship.friend=qq_user.`uid` WHERE statu=1";
        List<User> userList = DBHelper.selectForList(connection, User.class, sql, user.getUid(), user.getUid());
        return userList;
    }
}
