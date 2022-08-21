package com.qq.networkconnecting.Handle;

import com.qq.message.ChatStatus;
import com.qq.message.Message;
import com.qq.pojo.User;
import com.qq.service.UserService;
import com.qq.service.impl.UserServiceImpl;
import com.qq.ui.ServerFrame;
import com.qq.util.IOUtil;

import java.io.IOException;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @program: qq_server
 * @description:用户模块处理器
 * @author: Mr.Wang
 * @create: 2021-09-10 11:05
 **/
public class UserHandle {

    private UserService userService = new UserServiceImpl();

    //登录处理

    public void loginHandle(Socket socket, HashMap<String, Socket> map, ServerFrame serverFrame, Message message) {
        User user = message.getUser();
        boolean login = userService.login(user);
        if (login) {
            message.setLoginSuccess(true);
            User userBean = userService.findUserByUsername(user);
            List<User> allFriends = userService.findAllFriends(userBean);
            message.setUser(userBean);
            message.setUserList(allFriends);
            IOUtil.writeMessage(socket, message);
            //建立联系
            map.put(user.getUsername(), socket);
            //刷新服务端
            Set<String> set = map.keySet();
            serverFrame.onlineUserPanel.lstUser.setListData(set.toArray());
            serverFrame.serverInfoPanel.txtNumber.setText(String.valueOf(map.size()));
            flushList(map, userBean);
        } else {
            message.setLoginSuccess(false);
            IOUtil.writeMessage(socket, message);
        }

    }

    public void flushList(HashMap<String, Socket> map, User user) {
        List<User> allOnlineFriends = userService.findAllOnlineFriends(user);
        if (allOnlineFriends != null) {
            for (int i = 0; i < allOnlineFriends.size(); i++) {
                User userOnline = allOnlineFriends.get(i);
                Socket socketSender = map.get(userOnline.getUsername());
                List<User> allFriends = userService.findAllFriends(userOnline);
                Message messageSender = new Message();
                messageSender.setUser(userOnline);
                messageSender.setUserList(allFriends);
                messageSender.setChatStatus(ChatStatus.FLUSHLIST);
                if (socketSender != null && !socketSender.isClosed()) {
                    IOUtil.writeMessage(socketSender, messageSender);
                }
            }
        }
    }
    //注册处理

    public void registerHandle(Socket socket, Message message) {
        User user = message.getUser();
        boolean register = userService.register(user);
        if (register) {
            message.setRegisterSuccess(true);
        } else {
            message.setRegisterSuccess(false);
        }
        IOUtil.writeMessage(socket, message);
    }

    //是否存在该用户处理
    public void isExistUser(Socket socket, Message message) {
        User user = message.getUser();
        boolean existUser = userService.isExistUser(user);
        if (existUser) {
            message.setExistUser(true);
        } else {
            message.setExistUser(false);
        }
        IOUtil.writeMessage(socket, message);
    }

    //检查问题处理

    public void checkForQuestionHandle(Socket socket, Message message) {
        String luckyNUm = message.getLuckyNUm();
        System.out.println(luckyNUm);
        boolean answerSuccess = userService.isAnswerSuccess(message.getUser(), luckyNUm);
        if (answerSuccess) {
            message.setAnswerSuccess(true);
        } else {
            message.setAnswerSuccess(false);
        }
        IOUtil.writeMessage(socket, message);
    }
    //修改密码处理

    public void changePasswordHandle(Socket socket, Message message) {
        System.out.println(message.getUser());
        boolean b = userService.updatePassword(message.getUser());
        if (b) {
            message.setChangeSuccess(true);
        } else {
            message.setChangeSuccess(false);
        }
        IOUtil.writeMessage(socket, message);
    }
    //修改信息处理

    public void changeDataHandle(Socket socket, HashMap<String, Socket> map, Message message) {
//        System.out.println(message.getUser());
        User userBean = message.getUser();
        boolean b = userService.updateUser(message.getUser());
        if (b) {
            message.setChangeSuccess(true);
            message.setChatStatus(ChatStatus.FLUSHINFO);
            User user = userService.findUserByUsername(message.getUser());
            message.setUser(user);
            IOUtil.writeMessage(socket, message);

            flushList(map, userBean);
        } else {
            message.setChangeSuccess(false);
            IOUtil.writeMessage(socket, message);
        }
    }
    //修改状态处理

    public void changeStatusHandle(Socket socket, Message message) {
        User user = message.getUser();
        userService.updateUserStatus(user, 0);
    }

    //修改头像
    public void changePhotoHandle(Socket socket, HashMap<String, Socket> map, Message message) {
//        System.out.println(message.getUser());
        User userBean = message.getUser();
        boolean b = userService.updatePhoto(message.getUser());
        if (b) {
            message.setChangeSuccess(true);
            User user = userService.findUserByUsername(message.getUser());
            message.setUser(user);
            message.setChatStatus(ChatStatus.FLUSHINFO);
            IOUtil.writeMessage(socket, message);

            flushList(map, userBean);
        } else {
            message.setChangeSuccess(false);
            IOUtil.writeMessage(socket, message);
        }

    }
}
