package com.qq.service;

import java.util.List;

import com.qq.pojo.User;

/**
 * @program: mybatisTest
 * @description:
 * @author: Mr.Wang
 * @create: 2021-03-27 17:26
 **/
public interface FriendService {
	public boolean addFriend(User usersend,User useraccept);
	
	public boolean deleteFriend(User user1,User user2);
	
	public List<User> selectFriend(User user ,String str);

	public  boolean isFriend(User user ,String uid);
	
	
	
	
	
	}
