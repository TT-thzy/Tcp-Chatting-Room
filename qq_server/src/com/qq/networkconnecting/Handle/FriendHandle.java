package com.qq.networkconnecting.Handle;

import com.qq.message.ChatStatus;
import com.qq.message.Message;
import com.qq.pojo.User;
import com.qq.service.FriendService;
import com.qq.service.UserService;
import com.qq.service.impl.FriendServiceImpl;
import com.qq.service.impl.UserServiceImpl;
import com.qq.util.IOUtil;

import java.net.Socket;
import java.util.HashMap;
import java.util.List;

/**
 * @program: qq_server
 * @description: 好友模块处理器
 * @author: Mr.Wang
 * @create: 2021-09-10 11:10
 **/
public class FriendHandle {

    private FriendService friendService = new FriendServiceImpl();
    private UserService userService = new UserServiceImpl();
    //添加好友处理

    public void addFriendHandle(Socket socket, HashMap<String, Socket> map, Message message) {
        User sender = message.getSender();
        String friendId = message.getFriendId();
        Socket socketReceive = map.get(friendId);
        User user = new User();
        user.setUsername(friendId);
        User userByUsername = userService.findUserByUsername(user);
        //判断是否存在该用户
        if (userByUsername != null) {
            message.setExistUser(true);
            message.setReceive(userByUsername);
            boolean isfriend = friendService.isFriend(sender, friendId);
            //判断是否为好友
            if (isfriend) {
                message.setFriendShip(true);
                IOUtil.writeMessage(socket, message);
            } else {
                //判断接收方是否在线
                if (socketReceive != null && !socketReceive.isClosed()) {
                    message.setFriendShip(false);
                    IOUtil.writeMessage(socketReceive, message);
                } else {
                    System.out.println("对方不在线");
                }
            }
        } else {
            message.setExistUser(false);
            IOUtil.writeMessage(socket, message);
        }
    }
    //同意添加好友处理

    public void AcceptAddFriendHandle(Socket socket, HashMap<String, Socket> map, Message message) {
//        System.out.println(message.getSender().getUsername());  //registerTest
//        System.out.println(message.getReceive().getUsername()); //root
        Message messageSender=new Message();
        User sender = message.getSender();
        User receive = message.getReceive();
        message.setUser(receive);
        Socket socketSender = map.get(sender.getUsername());
        if (socketSender != null && !socketSender.isClosed()) {
            boolean addfriend = friendService.addFriend(sender, message.getReceive());
            message.setChatStatus(ChatStatus.ACCEPTADDFRIEND);
            if (addfriend) {
                message.setAcceptAddRequest(true);
                messageSender.setAcceptAddRequest(true);
                messageSender.setChatStatus(message.getChatStatus());
                messageSender.setUser(sender);
//                System.out.println(message.getUser().getUsername());
//                System.out.println(messageSender.getUser().getUsername());
                List<User> allFriends = userService.findAllFriends(message.getUser());
                message.setUserList(allFriends);
                List<User> allFriends1 = userService.findAllFriends(messageSender.getUser());
                messageSender.setUserList(allFriends1);
            } else {
                message.setAcceptAddRequest(false);
                messageSender.setAcceptAddRequest(false);
            }
            IOUtil.writeMessage(socket,message);
            IOUtil.writeMessage(socketSender, messageSender);
        } else {
            System.out.println("对方不在线");
        }
    }
    //根据账号模糊查询好友处理

    public void findFriendsHandle(Socket socket, Message message) {
        List<User> userList = friendService.selectFriend(message.getUser(), message.getId());
        System.out.println(userList);
        message.setFindUserList(userList);
        IOUtil.writeMessage(socket, message);
    }
    //删除好友处理

    public void deleteFriendHandle(Socket socket, HashMap<String,Socket> map,Message message) {
        Message messageReceive = new Message();
        User sender = message.getSender();
//        System.out.println("d1"+ sender.getUsername());
        String friendId = message.getFriendId();
        User user = new User();
        user.setUsername(friendId);
        User userByUsername = userService.findUserByUsername(user);
        //判断是否存在该用户
        if (userByUsername != null) {
            message.setExistUser(true);
            messageReceive.setExistUser(true);
            message.setReceive(userByUsername);
            boolean isfriend = friendService.isFriend(sender, friendId);
            //判断是否为好友
            if (isfriend) {
                message.setFriendShip(true);
                messageReceive.setFriendShip(true);
                boolean deletefriend = friendService.deleteFriend(sender, userByUsername);
                if (deletefriend) {
                    Socket socketReceive = map.get(userByUsername.getUsername());
                    message.setDeleteFriend(true);
                    messageReceive.setDeleteFriend(true);
                    messageReceive.setUser(userByUsername);
                    messageReceive.setChatStatus(message.getChatStatus());
                    List<User> allFriends = userService.findAllFriends(sender);
//                    System.out.println("d2"+ sender.getUsername());
//                    System.out.println(allFriends);
                    message.setUser(sender);
                    message.setUserList(allFriends);
                    List<User> allFriends1 = userService.findAllFriends(userByUsername);
                    messageReceive.setUserList(allFriends1);
                    IOUtil.writeMessage(socket, message);
                    IOUtil.writeMessage(socketReceive, messageReceive);
                } else {
                    message.setDeleteFriend(false);
                    IOUtil.writeMessage(socket, message);
                }
            } else {
                message.setFriendShip(false);
                IOUtil.writeMessage(socket, message);
            }
        } else {
            message.setExistUser(false);
            IOUtil.writeMessage(socket, message);
        }
    }
}
