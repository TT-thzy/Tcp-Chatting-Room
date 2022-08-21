package com.qq.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.qq.DB.DBHelper;
import com.qq.dao.FriendDao;
import com.qq.dao.SelectuidDao;
import com.qq.dto.Friendship;
import com.qq.pojo.User;
import com.qq.util.UUIDUtil;

public class FriendDaoimpl implements FriendDao {
    //添加
    @Override
    public boolean addFriend(User userSend, User userAccept, Connection con)
            throws Exception {
        UUIDUtil uuid = new UUIDUtil();
        String uui = uuid.getUUID();
        String userid = userSend.getUid();
        SelectuidDao sd = new SelectuidDaoImpl();
        String friendid = sd.selectuid(userAccept, con);
        System.out.println(friendid);
        String sql = "INSERT INTO qq_friendship(id,userId,friendId) VALUES(?, ?, ?)";
        boolean add = DBHelper.update(con, sql, uui, userid, friendid);
        return add;
    }

    @Override
    public boolean isbeFriend(User userSend, User userAccept, Connection con)
            throws Exception {
        String userid = userSend.getUid();
        String username = userAccept.getUsername();
        String sql1 = "SELECT uid from qq_user WHERE username=?";
        String friendid = (String) DBHelper.getValue(con, sql1, username);
        String sql = "SELECT * FROM qq_friendship WHERE userId=? and friendId=?";
        Friendship isfriend = DBHelper.selectForBean(con, Friendship.class, sql, friendid, userid);
        Friendship isfriend1 = DBHelper.selectForBean(con, Friendship.class, sql, userid, friendid);


        if (isfriend != null || isfriend1 != null) {
            return true;
        } else {
            return false;
        }

    }

    //删除好友
    @Override
    public boolean deleteFriend(User user, User user2, Connection con)
            throws Exception {
        String userid = user.getUid();
        String username = user2.getUsername();
        String sql1 = "SELECT uid from qq_user WHERE username=?";
        String user2id = DBHelper.getValue(con, sql1, username);
        String sql = "DELETE FROM qq_friendship WHERE userid=? and friendid=?";//删除好友
        boolean delete = DBHelper.update(con, sql, userid, user2id);
        boolean delete1 = DBHelper.update(con, sql, user2id, userid);
        if (delete1 || delete) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public User selecteuser(String username, Connection con) throws Exception {
        String sql = "SELECT * from qq_user WHERE username=?";
        User user = DBHelper.selectForBean(con, User.class, sql, username);
        if (user != null) {
            return user;
        }
        return null;
    }

    //根据账号模糊查询好友
    @Override
    public List<User> findAllFriendsByUsername(User user, String id,
                                               Connection con) throws Exception {
        String userid = user.getUid();
        String s = id = "%" + id + "%";
//        String sql = "SELECT nickname ,photo from qq_user as u INNER JOIN (SELECT * from qq_friendship WHERE userId=? ) as f on u.uid=f.friendId  where username LIKE ? UNION SELECT nickname ,photo from qq_user as u INNER JOIN (SELECT * from qq_friendship WHERE friendid=?) as f on u.uid=f.userid  where username LIKE ?";
        String sql = "SELECT qq_user.* FROM qq_user RIGHT JOIN (SELECT userid AS `friend` FROM qq_friendship WHERE friendId=? UNION  SELECT friendId AS `friend` FROM qq_friendship WHERE userid=?) AS `friendship`ON friendship.friend=qq_user.`uid` WHERE username LIKE ?";
        List<User> list = DBHelper.selectForList(con, User.class, sql, userid, userid, s);
        return list;
    }


}
