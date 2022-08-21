package com.qq.util;

import com.qq.message.Message;
import com.qq.networkconnecting.*;
import com.qq.ui.login.*;
import com.qq.ui.register.Edit;

import java.net.InetAddress;
import java.net.Socket;

/**
 * @program: qq_client
 * @description:
 * @author: Mr.Wang
 * @create: 2021-09-10 10:33
 **/
public class ConnectUtil {

    public static void LoginConnectServer(Message message, LoginFrame loginFrame){
        try {
            System.out.println("客户端连接之前");
            Socket socket = new Socket(InetAddress.getByName("localhost"), 9090); //连接服务端
            IOUtil.writeMessage(socket,message);
            new ClientLoginReceiveThread(socket,loginFrame).start();
        }catch (Exception e1){
            e1.printStackTrace();
        }
    }

    public static void RegisterConnectServer(Message message, Edit edit){
        try {
            System.out.println("客户端连接之前");
            Socket socket = new Socket(InetAddress.getByName("localhost"), 9090); //连接服务端
            IOUtil.writeMessage(socket,message);
            new ClientRegisterReceiveThread(socket,edit).start();
        }catch (Exception e1){
            e1.printStackTrace();
        }
    }

    public static void isExistConnectServer(Message message, MyChangepass changePass){
        try {
            System.out.println("客户端连接之前");
            Socket socket = new Socket(InetAddress.getByName("localhost"), 9090); //连接服务端
            IOUtil.writeMessage(socket,message);
            new ClientIsExistReceiveThread(socket,changePass).start();
        }catch (Exception e1){
            e1.printStackTrace();
        }
    }

    public static void ForgotConnectServer(Message message, MyChangepass3 myChangepass3){
        try {
            System.out.println("客户端连接之前");
            Socket socket = new Socket(InetAddress.getByName("localhost"), 9090); //连接服务端
            IOUtil.writeMessage(socket,message);
            new ClientForgotReceiveThread(socket,myChangepass3).start();
        }catch (Exception e1){
            e1.printStackTrace();
        }
    }

    public static void LuckyNumConnectServer(Message message, MyChangepass2 myChangepass2) {
        try {
            System.out.println("客户端连接之前");
            Socket socket = new Socket(InetAddress.getByName("localhost"), 9090); //连接服务端
            IOUtil.writeMessage(socket,message);
            new ClientLuckyNumThread(socket,message,myChangepass2).start();
        }catch (Exception e1){
            e1.printStackTrace();
        }
    }
}
