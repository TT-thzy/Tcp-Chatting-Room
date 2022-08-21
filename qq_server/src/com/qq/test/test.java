package com.qq.test;

import com.qq.message.ChatStatus;
import com.qq.message.Message;
import com.qq.pojo.User;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Collections;

/**
 * @program: qq_server
 * @description:
 * @author: Mr.Wang
 * @create: 2021-09-07 10:31
 **/
public class test {

    @Test
    public void testEnum() throws Exception{
        ServerSocket serverSocket = new ServerSocket(9111);
        Socket socket = serverSocket.accept();
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Message o = (Message) objectInputStream.readObject();
        System.out.println(o.getChatStatus()== ChatStatus.LOGIN);
        socket.close();
        objectInputStream.close();
    }

    @Test
    public void testList() throws Exception{
        ServerSocket serverSocket = new ServerSocket(9111);
        Socket socket = serverSocket.accept();
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Message o = (Message) objectInputStream.readObject();
        System.out.println(o.getUserList());
        socket.close();
        objectInputStream.close();
    }

//    @Test
//    public void fileTest() throws Exception{
//        ServerSocket serverSocket = new ServerSocket(9111);
//        Socket socket = serverSocket.accept();
//        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
//        Message o = (Message) objectInputStream.readObject();
//        System.out.println(o.getFile().getAbsoluteFile());
//        socket.close();
//        objectInputStream.close();
//    }

    @Test
    public void testNull(){
        User user = new User();
        TestCase.assertNull(user.getUsername());
    }
}
