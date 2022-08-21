package com.qq.networkconnecting;

import com.qq.message.Message;
import com.qq.ui.login.MyChangepass3;
import com.qq.util.IOUtil;

import javax.swing.*;
import java.net.Socket;

/**
 * @program: qq_client
 * @description: 客户端忘记密码接收线程
 * @author: Mr.Wang
 * @create: 2021-09-10 10:42
 **/
public class ClientForgotReceiveThread extends Thread{

    Socket socket=null;
    MyChangepass3 myChangepass3=null;

    public ClientForgotReceiveThread(Socket socket,MyChangepass3 myChangepass3){
        this.socket=socket;
        this.myChangepass3=myChangepass3;
    }

    @Override
    public void run() {
        System.out.println("注册接收线程启动");
        Message message = (Message) IOUtil.readMessage(socket);
        if (message.isChangeSuccess()){
            JOptionPane.showMessageDialog(myChangepass3,"修改成功");
            myChangepass3.dispose();
        }else {
            JOptionPane.showMessageDialog(myChangepass3,"修改失败");
            myChangepass3.dispose();
        }
    }
}
