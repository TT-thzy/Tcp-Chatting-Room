package com.qq.dao;




import java.sql.Connection;
import java.util.List;

import com.qq.pojo.User;

/**
 * @program: qq_server
 * @description:
 * @author: Mr.Wang
 * @create: 2021-09-10 11:17
 **/
public interface FriendDao {

    //添加好友
    boolean addFriend(User userSend, User userAccept,Connection con) throws Exception;
    //判断是否已经为好友
    boolean isbeFriend(User userSend, User userAccept,Connection con) throws Exception;
    //删除好友
    boolean deleteFriend(User user,User user2,Connection con) throws Exception;
    //根据账号查询对象
    User selecteuser(String username, Connection con) throws Exception;
    //按账号模糊查找好友
    List<User> findAllFriendsByUsername(User user,String id,Connection con) throws Exception;
}
