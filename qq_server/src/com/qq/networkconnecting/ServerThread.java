package com.qq.networkconnecting;

import com.qq.message.ChatStatus;
import com.qq.message.Message;
import com.qq.networkconnecting.Handle.ChatHandle;
import com.qq.networkconnecting.Handle.FriendHandle;
import com.qq.networkconnecting.Handle.UserHandle;
import com.qq.pojo.User;
import com.qq.ui.ServerFrame;
import com.qq.util.IOUtil;
import com.sun.javafx.robot.impl.FXRobotHelper;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

/**
 * @program: qq_server
 * @description:
 * @author: Mr.Wang
 * @create: 2021-09-07 16:29
 **/
public class ServerThread extends Thread {

    //拿到的socket连接
    Socket socket = null;
    //服务端界面
    ServerFrame serverFrame = null;
    //保存所有连接的账号与socket
    public static HashMap<String, Socket> map = new HashMap<>();
    //用户模块处理器
    UserHandle userHandle=new UserHandle();
    //聊天模块处理器
    ChatHandle chatHandle=new ChatHandle();
    //好友模块处理器
    FriendHandle friendHandle=new FriendHandle();

    public ServerThread(Socket socket, ServerFrame serverFrame) {
        this.socket = socket;
        this.serverFrame = serverFrame;
    }

    @Override
    public void run() {
        //模拟一直处理数据（长连接）
        while (true) {
            System.out.println("服务器线程启动！");
            Message message = (Message) IOUtil.readMessage(socket);
            System.out.println("服务端拿到了客户端信息" + message);
            System.out.println(message.getChatStatus());

            if (message.getChatStatus()==ChatStatus.QUIT){
                map.remove(message.getUser().getUsername());
                //刷新服务端
                Set<String> set = map.keySet();
                serverFrame.onlineUserPanel.lstUser.setListData(set.toArray());
                serverFrame.serverInfoPanel.txtNumber.setText(String.valueOf(map.size()));

                IOUtil.writeMessage(socket,message);
                userHandle.changeStatusHandle(socket,message);
                User user = message.getUser();
                userHandle.flushList(map,user);
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                this.interrupt();
                break;
            }

            try {
                //消息分类
                switch (message.getChatStatus()){
                    case LOGIN:
                        //登录请求
                        userHandle.loginHandle(socket,map,serverFrame,message);
//                        IOUtil.writeMessage(socket,message);
                        break;
                    case REGISTER:
                        //注册请求
                        userHandle.registerHandle(socket,message);
                        break;
                    case SINGLECHAT:
                        //单聊请求
                        chatHandle.singleChatHandle(socket,message,map,serverFrame);
                        break;
                    case SINGLEDD:
                        //单聊抖动
                        chatHandle.singleDDHandle(socket,map,message,serverFrame);
                        break;
                    case SINGLEFILE:
                        //单聊文件
                        chatHandle.singleFileHandle(socket,map,message,serverFrame);
                        break;
                    case isExistUser:
                        //是否存在该用户
                        userHandle.isExistUser(socket,message);
                        break;
                    case CHANGEHEADERPHOTO:
                        //是否存在该用户
                        userHandle.changePhotoHandle(socket,map,message);
                        break;
                    case ADDFRIEND:
                        friendHandle.addFriendHandle(socket,map,message);
                        //添加好友
                        break;
                    case ACCEPTADDFRIEND:
                        friendHandle.AcceptAddFriendHandle(socket,map,message);
                        //同意添加好友
                        break;
                    case CHECKFORQUESTION:
                        //检查问题
                        userHandle.checkForQuestionHandle(socket,message);
                        break;
                    case CHANGEPASSWORD:
                        //修改密码
                        userHandle.changePasswordHandle(socket,message);
                        break;
                    case CHANGEDATA:
                        userHandle.changeDataHandle(socket,map,message);
                        //修改信息
                        break;
                    case CHANGESTATUS:
                        //修改状态
                        break;
                    case FINDFRIEND:
                        //根据账号模糊查询好友
                        friendHandle.findFriendsHandle(socket,message);
                        break;
                    case DELETEFRIEND:
                        //删除好友
                        friendHandle.deleteFriendHandle(socket,map,message);
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
