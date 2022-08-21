package com.qq.service.impl;

import com.qq.DB.DBHelper;
import com.qq.dao.UserDao;
import com.qq.dao.impl.UserDaoImpl;
import com.qq.pojo.User;
import com.qq.service.UserService;
import com.qq.util.MD5Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @program: qq_server
 * @description:
 * @author: Mr.Wang
 * @create: 2021-09-10 12:56
 **/
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    //登录
    @Override
    public boolean login(User user) {
        Connection connection = DBHelper.getConnection();
        try {
            connection.setAutoCommit(false);
            User userBean = userDao.findUserByUsername(user, connection);
            if (userBean != null) {
                String passwordOld = userBean.getPassword();
                String passwordNew = MD5Util.encode(user.getPassword());
                if (passwordOld.equals(passwordNew)) {
                    //修改用户状态
                    userDao.updateStatu(user,1,connection);
                    connection.commit();
                    return true;
                }
            }
            connection.commit();
        } catch (Exception throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            DBHelper.closeAll(connection, null, null);
        }
        return false;
    }

    //查询所有好友
    @Override
    public List<User> findAllFriends(User user) {
        Connection connection = DBHelper.getConnection();
        try {
            connection.setAutoCommit(false);
            List<User> allFriends = userDao.findAllFriends(user, connection);
            connection.commit();
            if (allFriends != null) {
                return allFriends;
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            DBHelper.closeAll(connection, null, null);
        }
        return null;
    }

    //根据账号查询用户
    @Override
    public User findUserByUsername(User user) {
        Connection connection = DBHelper.getConnection();
        try {
            connection.setAutoCommit(false);
            User userBean = userDao.findUserByUsername(user, connection);
            connection.commit();
            if (userBean != null) {
                return userBean;
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            DBHelper.closeAll(connection, null, null);
        }
        return null;
    }
    //注册
    @Override
    public boolean register(User user) {
        Connection connection = DBHelper.getConnection();
        try {
            connection.setAutoCommit(false);
            User userBean = userDao.findUserByUsername(user, connection);
            if (userBean == null) {
                String passwordEncode = MD5Util.encode(user.getPassword());
                user.setPassword(passwordEncode);
                boolean b = userDao.addUser(user, connection);
                connection.commit();
                if (b) {
                    return true;
                }
            }
            connection.commit();
        } catch (Exception throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            DBHelper.closeAll(connection, null, null);
        }
        return false;
    }
    //判断用户是否存在
    @Override
    public boolean isExistUser(User user) {
        Connection connection = DBHelper.getConnection();
        try {
            connection.setAutoCommit(false);
            User userByUsername = userDao.findUserByUsername(user, connection);
            connection.commit();
            if (userByUsername != null) {
                return true;
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            DBHelper.closeAll(connection, null, null);
        }
        return false;
    }
    //判断问题是否回答正确
    @Override
    public boolean isAnswerSuccess(User user,String lucky) {
        Connection connection = DBHelper.getConnection();
        try {
            connection.setAutoCommit(false);
            User userByUsername = userDao.findUserByUsername(user, connection);
            connection.commit();
            String luckNum=userByUsername.getLuckyNum();
            if (userByUsername != null) {
                if (luckNum.equals(lucky)){
                    return true;
                }
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            DBHelper.closeAll(connection, null, null);
        }
        return false;
    }
    //修改密码
    @Override
    public boolean updatePassword(User user) {
        Connection connection = DBHelper.getConnection();
        try {
            connection.setAutoCommit(false);
            boolean b = userDao.updatePassword(user,connection);
            connection.commit();
            if (b){
                return true;
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            DBHelper.closeAll(connection, null, null);
        }
        return false;
    }
    //修改头像
    @Override
    public boolean updatePhoto(User user) {
        Connection connection = DBHelper.getConnection();
        try {
            connection.setAutoCommit(false);
            boolean b = userDao.updatePhoto(user,connection);
            connection.commit();
            if (b){
                return true;
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            DBHelper.closeAll(connection, null, null);
        }
        return false;
    }
    //修改用户信息
    @Override
    public boolean updateUser(User user) {
        Connection connection = DBHelper.getConnection();
        try {
            connection.setAutoCommit(false);
            boolean b = userDao.updateUser(user,connection);
            connection.commit();
            if (b){
                return true;
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            DBHelper.closeAll(connection, null, null);
        }
        return false;
    }
    //修改用户状态
    @Override
    public boolean updateUserStatus(User user, int status) {
        Connection connection = DBHelper.getConnection();
        try {
            connection.setAutoCommit(false);
            boolean b = userDao.updateStatu(user, status, connection);
            connection.commit();
            if (b){
                return true;
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            DBHelper.closeAll(connection, null, null);
        }
        return false;
    }
    //查询所有在线用户
    @Override
    public List<User> findAllOnlineFriends(User user) {
        Connection connection = DBHelper.getConnection();
        try {
            connection.setAutoCommit(false);
            List<User> allFriends = userDao.findAllOnlineFriends(user, connection);
            connection.commit();
            if (allFriends != null) {
                return allFriends;
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            DBHelper.closeAll(connection, null, null);
        }
        return null;
    }
}
