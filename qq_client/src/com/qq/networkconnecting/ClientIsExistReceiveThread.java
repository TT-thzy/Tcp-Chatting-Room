package com.qq.networkconnecting;

import com.qq.message.Message;
import com.qq.ui.login.LoginFrame;
import com.qq.ui.login.MyChangepass;
import com.qq.ui.login.MyChangepass2;
import com.qq.ui.register.Register;
import com.qq.util.IOUtil;

import javax.swing.*;
import java.net.Socket;

/**
 * @program: qq_client
 * @description:
 * @author: Mr.Wang
 * @create: 2021-09-12 10:30
 **/
public class ClientIsExistReceiveThread extends Thread{

    Socket socket=null;
    MyChangepass changePass=null;

    public ClientIsExistReceiveThread(Socket socket,MyChangepass changePass){
        this.socket=socket;
        this.changePass=changePass;
    }

    @Override
    public void run() {
        System.out.println("判断是否存在该用户接收线程启动");
        Message message = (Message) IOUtil.readMessage(socket);
        if (message.isExistUser()){
            new MyChangepass2(message);
            changePass.dispose();
        }else {
            JOptionPane.showMessageDialog(null,"账号不存在!");

        }
    }
}
