package com.qq.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.qq.DB.DBHelper;
import com.qq.dao.FriendDao;
import com.qq.dao.impl.FriendDaoimpl;
import com.qq.pojo.User;
import com.qq.service.FriendService;

public class FriendServiceImpl implements FriendService{
	private FriendDao frienddao=new FriendDaoimpl();
	//添加
	@Override
	public boolean addFriend(User usersend, User useraccept) {
		Connection con=DBHelper.getConnection();
		try {
			con.setAutoCommit(false);
			boolean isfriend=frienddao.isbeFriend(usersend, useraccept, con);
			if (!isfriend) {
				boolean addfrien=frienddao.addFriend(usersend, useraccept, con);
				con.commit();
				return true;
			}
			System.out.println("添加失败");
			con.commit();
		}catch (Exception throwables) {
			throwables.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            DBHelper.closeAll(con, null, null);
        }
		
		return false;
	}
	//删除好友
	@Override
	public boolean deleteFriend(User user1, User user2) {
		Connection con=DBHelper.getConnection();
		boolean isfriend;
		boolean delete;
		try {
			con.setAutoCommit(false);
			isfriend = frienddao.isbeFriend(user1, user2, con);
			if (isfriend) {
				delete=frienddao.deleteFriend(user1, user2, con);
				if (delete==true) {
					con.commit();
					return true;
				}
				con.commit();
			}
		} catch (Exception throwables) {
			throwables.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            DBHelper.closeAll(con, null, null);
        }
		
		return false;
	}
	
	//根据账号模糊查询好友
	@Override
	public List<User> selectFriend(User user, String str) {
		Connection con=DBHelper.getConnection();
		List<User> list;
		try {
			con.setAutoCommit(false);
			list=frienddao.findAllFriendsByUsername(user, str, con);
			con.commit();
			if (list!=null) {
				return list;
			}
		}  catch (Exception throwables) {
			throwables.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            DBHelper.closeAll(con, null, null);
        }
		System.out.println("kong");
		return null;
	}

	@Override
	public boolean isFriend(User user, String uid) {
		Connection con=DBHelper.getConnection();
		try {
			con.setAutoCommit(false);
			User friend=frienddao.selecteuser(uid,con);
			if (friend!=null){
				boolean isfriends=frienddao.isbeFriend(user,friend,con);
				con.commit();
				if (isfriends){
					return true;
				}
			}
			con.commit();
		}
		catch (Exception throwables) {
			throwables.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} finally {
			DBHelper.closeAll(con, null, null);
		}
		return false;
	}

}
