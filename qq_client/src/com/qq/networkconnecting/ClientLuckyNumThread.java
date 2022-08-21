package com.qq.networkconnecting;

import com.qq.message.Message;
import com.qq.ui.login.MyChangepass;
import com.qq.ui.login.MyChangepass2;
import com.qq.ui.login.MyChangepass3;
import com.qq.util.IOUtil;

import javax.swing.*;
import java.net.Socket;

/**
 * @program: qq_client
 * @description:
 * @author: Mr.Wang
 * @create: 2021-09-12 11:29
 **/
public class ClientLuckyNumThread extends Thread{

    Socket socket=null;
    Message messages=null;
    MyChangepass2 changepass2=null;

    public ClientLuckyNumThread(Socket socket,Message message,MyChangepass2 changepass2){
        this.socket=socket;
        this.messages=message;
        this.changepass2=changepass2;
    }

    @Override
    public void run() {
        System.out.println("判断幸运数组问题是否回答成功接收线程启动");
        Message message = (Message) IOUtil.readMessage(socket);
        if (message.isAnswerSuccess()){
            new MyChangepass3(messages);
            changepass2.dispose();
        }else {
            JOptionPane.showMessageDialog(null,"幸运数字错误!,请重试");
        }
    }
}
