package com.qq.dao.impl;

import java.sql.Connection;

import com.qq.DB.DBHelper;
import com.qq.dao.SelectuidDao;
import com.qq.pojo.User;

public class SelectuidDaoImpl implements SelectuidDao{

	@Override
	public String selectuid(User user,Connection con ) throws Exception{
		String username=user.getUsername();
		String sql="SELECT uid  from qq_user WHERE username=?";
		String uid=DBHelper.getValue(con, sql, username);
		return uid;
	}

}
