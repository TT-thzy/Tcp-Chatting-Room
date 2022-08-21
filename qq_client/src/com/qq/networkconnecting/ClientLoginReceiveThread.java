package com.qq.networkconnecting;


import com.qq.collection.ManagerChatFrameCollection;
import com.qq.collection.ManagerListFrameCollection;
import com.qq.message.ChatStatus;
import com.qq.message.Message;
import com.qq.pojo.FontStyle;
import com.qq.pojo.User;
import com.qq.ui.chat.ChatFrame;
import com.qq.ui.chat.DouDong;
import com.qq.ui.login.LoginFrame;
import com.qq.ui.login.MyQQFrameLoading;
import com.qq.ui.main.Addfail;
import com.qq.ui.main.Addsuccessfully;
import com.qq.ui.main.ListFrame;
import com.qq.ui.main.SearchFrame;
import com.qq.util.FontSupport;
import com.qq.util.IOUtil;
import com.qq.util.PhotoUtil;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @program: test_qq
 * @description: 客户端登录接收线程
 * @author: Mr.Wang
 * @create: 2021-08-26 22:14
 **/
public class ClientLoginReceiveThread extends Thread {

    Socket socket = null;
    LoginFrame loginFrame = null;


    public ClientLoginReceiveThread(Socket socket, LoginFrame loginFrame) {
        this.socket = socket;
        this.loginFrame = loginFrame;
    }

    @Override
    public void run() {
        System.out.println("客户端接收线程启动");
        while (true) {
            try {
                Message message = (Message) IOUtil.readMessage(socket);
                System.out.println(message.getChatStatus());

                if (message.getChatStatus()==ChatStatus.QUIT){
                    socket.close();
                    this.interrupt();
                    break;
                }

                switch (message.getChatStatus()) {
                    case LOGIN:
                        login(message);
                        break;
                    case SINGLECHAT:
                        singleChat(message);
                        break;
                    case SINGLEDD:
                        singleDD(message);
                        break;
                    case SINGLEFILE:
                        new Thread() {
                            @Override
                            public void run() {
                                singleFile(message);
                            }
                        }.start();
                        break;
                    case FLUSHINFO:
                        new Thread() {
                            @Override
                            public void run() {
                                flushInfo(message);
                            }
                        }.start();
                        break;
                    case FLUSHLIST:
                        new Thread() {
                            @Override
                            public void run() {
                                flushList(message);
                            }
                        }.start();
                        break;
                    case ADDFRIEND:
                        addFriend(message, socket);
                        break;
                    case ACCEPTADDFRIEND:
                        acceptAddFriend(message);
                        break;
                    case FINDFRIEND:
                        findFriend(message);
                        break;
                    case DELETEFRIEND:
                        deleteFriend(message);
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void deleteFriend(Message message) {
        if (!message.isExistUser()) {
            JOptionPane.showMessageDialog(null, "该用户不存在");
        } else {
            if (message.isFriendShip()) {
                if (message.isDeleteFriend()) {
                    JFrame jf = new Addsuccessfully();
                    final Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        public void run() {
                            if (jf != null) {
                                jf.dispose();
                                timer.cancel();//计时器停止
                            }
                        }
                    }, 1000);
                    System.out.println("d3"+message.getUser().getUsername());
                    ListFrame listFrame = ManagerListFrameCollection.get(message.getUser().getUsername());
                    System.out.println("d3"+message.getUserList());
                    if (message.getUserList()!=null){
                        listFrame.jlist_single.setListData(message.getUserList().toArray(new User[message.getUserList().size()]));
                    }else {
//                        System.out.println(1);
                        listFrame.jlist_single.remove(0);
                        listFrame.jlist_single.repaint();
                    }
                } else {
                    JFrame jf = new Addfail();
                    final Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        public void run() {
                            if (jf != null) {
                                jf.dispose();
                                timer.cancel();//计时器停止
                            }
                        }
                    }, 1000);
                }
            } else {
                JOptionPane.showMessageDialog(null, "该用户不是您的好友");
            }
        }
    }

    private void flushList(Message message) {
        User user = message.getUser();
        ListFrame listFrame = ManagerListFrameCollection.get(user.getUsername());
        listFrame.jlist_single.setListData(message.getUserList().toArray(new User[message.getUserList().size()]));
    }

    private void acceptAddFriend(Message message) {
        System.out.println("client  acceptAddFriend");
        if (message.isAcceptAddRequest()) {
            JFrame jf = new Addsuccessfully();
            final Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    if (jf != null) {
                        jf.dispose();
                        timer.cancel();//计时器停止
                    }
                }
            }, 1000);
            System.out.println(message.getUser().getUsername());
            ListFrame listFrame = ManagerListFrameCollection.get(message.getUser().getUsername());
            listFrame.jlist_single.setListData(message.getUserList().toArray(new User[message.getUserList().size()]));
        } else {
            JFrame jf = new Addfail();
            final Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    if (jf != null) {
                        jf.dispose();
                        timer.cancel();//计时器停止
                    }
                }
            }, 1000);
        }
    }

    private void addFriend(Message message, Socket socket) {
        if (!message.isExistUser()) {
            JOptionPane.showMessageDialog(null, "该用户不存在");
        } else {
            if (message.isFriendShip()) {
                JOptionPane.showMessageDialog(null, "该用户已经是您的好友");
            } else {
                int confirmDialog = JOptionPane.showConfirmDialog(null, "是否接收" + message.getSender().getNickname() + "的好友请求");
                if (confirmDialog == JOptionPane.YES_OPTION) {
                    message.setChatStatus(ChatStatus.ACCEPTADDFRIEND);
                    message.setAcceptAddRequest(true);
                    IOUtil.writeMessage(socket, message);
                }
            }
        }
    }

    private void findFriend(Message message) {
        ListFrame listFrame = ManagerListFrameCollection.get(message.getUser().getUsername());
        Point p = listFrame.p;
        SearchFrame searchFrame = new SearchFrame(message.getFindUserList());
        searchFrame.setLocation(p.x, p.y + 165);
    }

    private void flushInfo(Message message) {
        if (message.isChangeSuccess()) {
            JOptionPane.showMessageDialog(null, "修改成功");
            ListFrame listFrame = ManagerListFrameCollection.get(message.getUser().getUsername());
            listFrame.user = message.getUser();
            listFrame.userList = message.getUserList();
            listFrame.jlb_img.setIcon(new ImageIcon(PhotoUtil.byteToPhoto(message.getUser().getPhoto()).getScaledInstance(70, 70, Image.SCALE_DEFAULT)));
            listFrame.jlb_id.setText(message.getUser().getUsername());
            listFrame.jlb_name.setText(message.getUser().getNickname());
            listFrame.jlb_ss.setText(message.getUser().getSignature());
        } else {
            JOptionPane.showMessageDialog(null, "修改失败");
        }
    }

    private void singleFile(Message message) {
        int result = JOptionPane.showConfirmDialog(null, "是否接收来自" + message.getSender().getNickname() + "的文件");
        if (result == JOptionPane.YES_OPTION) {
            //接收文件的时候可以指定具体的目录
            JFileChooser jfc = new JFileChooser();
            //只能选择文件夹
            jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            //打开文件选择窗体
            int state = jfc.showDialog(new JLabel(), "保存文件");

            if (state == JFileChooser.CANCEL_OPTION) {
                System.out.println("用户取消下载");
                //取消
                return;
            }
            //获取文件
            File directory = jfc.getSelectedFile();
            String filePath = directory.getAbsolutePath();
            try {
                System.out.println(filePath + "==" + message.getFile().getName());
                File saveFile = new File(filePath, message.getFile().getName());
                FileOutputStream fos = new FileOutputStream(saveFile);
                fos.write(message.getFile().getData());
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "下载完成");
        }
    }

    private void singleDD(Message message) {
        ChatFrame chatFrame = ManagerChatFrameCollection.get(message.getReceive().getUsername() + "" + message.getSender().getUsername());
        FontSupport.fontDecode(chatFrame.acceptPane, message.getContent(), message.getSender().getNickname(), message.getReceive().getNickname());
        new DouDong(chatFrame).start();
    }

    private void singleChat(Message message) {
        List<FontStyle> content = message.getContent();
        ChatFrame chatFrame = ManagerChatFrameCollection.get(message.getReceive().getUsername() + "" + message.getSender().getUsername());
        FontSupport.fontDecode(chatFrame.acceptPane, content, message.getSender().getNickname(), message.getReceive().getNickname());
//        System.out.println(content.toString());
    }

    private void login(Message message) {
        System.out.println(message.isLoginSuccess());
        if (message.isLoginSuccess()) {
            JOptionPane.showMessageDialog(loginFrame, "登陆成功!");
            new MyQQFrameLoading(message.getUser(), message.getUserList(), socket);
            loginFrame.dispose();
            System.out.println(message.getUser());
            System.out.println(message.getUserList());
        } else {
            JOptionPane.showMessageDialog(loginFrame, "登陆信息有误!");
        }
    }

}
