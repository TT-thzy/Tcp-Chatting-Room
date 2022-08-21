package com.qq.networkconnecting.Handle;

import com.qq.message.Message;
import com.qq.pojo.FontStyle;
import com.qq.ui.ServerFrame;
import com.qq.util.FontSupport;
import com.qq.util.IOUtil;

import java.net.Socket;
import java.util.HashMap;
import java.util.List;

/**
 * @program: qq_server
 * @description: 聊天模块处理器
 * @author: Mr.Wang
 * @create: 2021-09-10 11:12
 **/
public class ChatHandle {

    //单聊请求处理

    public void singleChatHandle(Socket socket, Message message, HashMap<String, Socket> map, ServerFrame serverFrame) {
        Socket receiver = map.get(message.getReceive().getUsername());
//        IOUtil.writeMessage(socket,message);
        if (receiver != null && !receiver.isClosed()){
            IOUtil.writeMessage(receiver, message);
            List<FontStyle> content = message.getContent();
            FontSupport.fontDecode(serverFrame.serverInfoPanel.txtLog, content, message.getSender().getNickname(), message.getReceive().getNickname());
        }else {
            System.out.println("消息接收人：" + message.getReceive().getNickname() + "不在线");
        }

    }
    //单聊抖动处理

    public void singleDDHandle(Socket socket, HashMap<String, Socket> map, Message message, ServerFrame serverFrame) {
        Socket receiver = map.get(message.getReceive().getUsername());
        if (receiver != null && !receiver.isClosed()){
            IOUtil.writeMessage(receiver, message);
            List<FontStyle> content = message.getContent();
            FontSupport.fontDecode(serverFrame.serverInfoPanel.txtLog, content, message.getSender().getNickname(), message.getReceive().getNickname());
        }else {
            System.out.println("消息接收人：" + message.getReceive().getNickname() + "不在线");
        }
    }
    //单聊文件处理

    public void singleFileHandle(Socket socket, HashMap<String, Socket> map, Message message, ServerFrame serverFrame) {
        String reciver = message.getReceive().getUsername();
        //取出接收人的Socket
        Socket reciverSocket = map.get(reciver);
        //如果当前人在线的话
        if (reciverSocket != null && !reciverSocket.isClosed()) {
            IOUtil.writeMessage(reciverSocket, message);
        } else {
            System.out.println("文件接收人：" + message.getReceive().getNickname() + "不在线");
        }
    }
}
