package com.qq.test;

import com.qq.message.ChatStatus;
import com.qq.message.Message;
import com.qq.pojo.User;
import org.junit.Test;

import java.io.File;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @program: qq_server
 * @description:
 * @author: Mr.Wang
 * @create: 2021-09-07 10:31
 **/
public class test {

    @Test
    public void testEnum() throws Exception{
        Socket socket = new Socket("localhost", 9111);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        Message message = new Message();
        message.setChatStatus(ChatStatus.LOGIN);
        objectOutputStream.writeObject(message);
        socket.close();
        objectOutputStream.close();
    }

    @Test
    public void testList() throws Exception{
        Socket socket = new Socket("localhost", 9111);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        Message message = new Message();
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        message.setUserList(users);
        objectOutputStream.writeObject(message);
        socket.close();
        objectOutputStream.close();
    }

//    @Test
//    public void testFile() throws Exception{
//        Socket socket = new Socket("localhost", 9111);
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
//        Message message = new Message();
//        File file = new File("C:\\Users\\王涛.com\\Desktop\\ui\\用户信息\\windowusers\\icon\\登录.png");
//        message.setFile(file);
//        objectOutputStream.writeObject(message);
//        socket.close();
//        objectOutputStream.close();
//    }
}
