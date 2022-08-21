package com.qq.networkconnecting;

import com.qq.config.ServerConfig;
import com.qq.ui.ServerFrame;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 * @program: test_qq
 * @description:服务端入口
 * @author: Mr.Wang
 * @create: 2021-08-26 21:27
 **/
public class ChatServer {

    public ChatServer(){
        try {
            ServerSocket serverSocket = new ServerSocket(ServerConfig.PORT);
            ServerFrame serverFrame = new ServerFrame();
            serverFrame.serverInfoPanel.txtLog.setText("服务器启动成功....");
            while (true){
                //多客户端连接
                Socket socket = serverSocket.accept();
                new ServerThread(socket,serverFrame).start();
                System.out.println("服务器接收到客户端连接：" + socket);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ChatServer();
    }
}
