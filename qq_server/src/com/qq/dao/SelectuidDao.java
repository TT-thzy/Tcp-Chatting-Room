package com.qq.dao;

import java.sql.Connection;

import com.qq.pojo.User;

public interface SelectuidDao {
	String selectuid(User user,Connection con)throws Exception;

}
