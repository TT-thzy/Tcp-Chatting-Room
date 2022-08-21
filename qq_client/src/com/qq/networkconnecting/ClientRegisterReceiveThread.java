package com.qq.networkconnecting;

import com.qq.message.ChatStatus;
import com.qq.message.Message;
import com.qq.ui.login.LoginFrame;
import com.qq.ui.register.Edit;
import com.qq.ui.register.Register;
import com.qq.util.IOUtil;
import com.sun.org.apache.regexp.internal.RE;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;

/**
 * @program: qq_client
 * @description:客户端注册接收线程
 * @author: Mr.Wang
 * @create: 2021-09-10 10:40
 **/
public class ClientRegisterReceiveThread extends Thread{

    Socket socket=null;

    Edit edit=null;

    public ClientRegisterReceiveThread(Socket socket,Edit edit){
        this.socket=socket;
        this.edit=edit;
    }

    @Override
    public void run() {
        System.out.println("注册接收线程启动");
        Message message = (Message) IOUtil.readMessage(socket);
        if (message.isRegisterSuccess()){
            JOptionPane.showMessageDialog(edit,"注册成功");
            new LoginFrame();
            edit.dispose();
        }else {
            JOptionPane.showMessageDialog(edit,"注册失败，账号已存在!");
            new Register();
            edit.dispose();
        }
    }
}
