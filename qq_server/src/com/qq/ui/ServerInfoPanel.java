package com.qq.ui;

import com.qq.config.ServerConfig;
import com.qq.message.ChatStatus;
import com.qq.message.Message;
import com.qq.networkconnecting.ServerThread;
import com.qq.util.DateUtil;
import com.qq.util.IOUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;
import java.util.Iterator;

public class ServerInfoPanel {

    //在线人数
    public JTextField txtNumber = null;

    public JTextPane txtLog = null;

    /**
     * 第一个选项卡的信息
     *
     * @return
     */
    public JLabel getServerInfoPanel() {

        //整个第一个服务选项卡面板，包括日志区域
        JPanel pnlServer = new JPanel();
        pnlServer.setOpaque(false);
        pnlServer.setLayout(null);
        pnlServer.setBounds(0, 0, ServerFrame.FRAME_WIDTH, ServerFrame.FRAME_HEIGHT);

        //日志标签
        JLabel lblLog = new JLabel("[服务器日志]");
        lblLog.setForeground(Color.BLACK);
        lblLog.setFont(new Font("宋体", 0, 16));
        lblLog.setBounds(130, 5, 100, 30);
        pnlServer.add(lblLog);

        //日志区域
        txtLog = new JTextPane();
        txtLog.setOpaque(false);
        txtLog.setFont(new Font("宋体", 0, 12));

        JScrollPane scoPaneOne = new JScrollPane(txtLog);// 设置滚动条
        scoPaneOne.setBounds(130, 35, 340, 360);
        scoPaneOne.setOpaque(false);
        scoPaneOne.getViewport().setOpaque(false);

        pnlServer.add(scoPaneOne);

        pnlServer.add(stopBtn());

        pnlServer.add(saveLogBtn());

        pnlServer.add(getServerParam());

        //加载窗体的背景图片
        ImageIcon imageIcon = new ImageIcon("D:\\IdeaProjects\\test_qq\\src\\main\\resources\\image\\beijing.jpg");
        //创建一个标签并将图片添加进去
        JLabel lblBackground = new JLabel(imageIcon);
        //设置图片的位置和大小
        lblBackground.setBounds(0, 200, 300, 300);
        //添加到当前窗体中
        lblBackground.add(pnlServer);

        return lblBackground;
    }

    /**
     * 服务器参数信息界面
     *
     * @return
     */
    public JPanel getServerParam() {
        //服务器参数信息面板，不包括日志区域
        JPanel serverParamPanel = new JPanel();
        serverParamPanel.setOpaque(false);
        serverParamPanel.setBounds(5, 5, 100, 400);
        serverParamPanel.setFont(new Font("宋体", 0, 14));
        serverParamPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(""),
                BorderFactory.createEmptyBorder(1, 1, 1, 1)));

        JLabel lblNumber = new JLabel("当前在线人数:");
        lblNumber.setFont(new Font("宋体", 0, 14));
        serverParamPanel.add(lblNumber);

        txtNumber = new JTextField("0", 10);
        txtNumber.setFont(new Font("宋体", 0, 14));
        txtNumber.setEditable(false);
        serverParamPanel.add(txtNumber);

        JLabel lblServerName = new JLabel("服务器名称:");
        lblServerName.setFont(new Font("宋体", 0, 14));
        serverParamPanel.add(lblServerName);

        JTextField txtServerName = new JTextField(ServerConfig.HOST, 10);
        txtServerName.setFont(new Font("宋体", 0, 14));
        txtServerName.setEditable(false);
        serverParamPanel.add(txtServerName);

        JLabel lblIP = new JLabel("服务器IP:");
        lblIP.setFont(new Font("宋体", 0, 14));
        serverParamPanel.add(lblIP);

        JTextField txtIP = new JTextField(ServerConfig.IP, 10);
        txtIP.setFont(new Font("宋体", 0, 14));
        txtIP.setEditable(false);
        serverParamPanel.add(txtIP);

        JLabel lblPort = new JLabel("服务器端口:");
        lblPort.setFont(new Font("宋体", 0, 14));
        serverParamPanel.add(lblPort);

        JTextField txtPort = new JTextField(String.valueOf(ServerConfig.PORT), 10);
        txtPort.setFont(new Font("宋体", 0, 14));
        txtPort.setEditable(false);
        serverParamPanel.add(txtPort);

        return serverParamPanel;
    }

    /**
     * 关闭服务器按钮
     *
     * @return
     */
    public JButton stopBtn() {
        JButton stopBtn = new JButton("关闭服务器");
        stopBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                if (!txtNumber.getText().equals("0")) {
//                    int i = JOptionPane.showConfirmDialog(null, "该服务器内存在在线用户,关闭会导致不可预期的错误!是否关闭");
//                    if (i == JOptionPane.YES_OPTION) {
//                        Message message = new Message();
//                        message.setChatStatus(ChatStatus.QUIT);
//                        Iterator<String> iterator = ServerThread.map.keySet().iterator();
//                        while (iterator.hasNext()){
//                            String username = iterator.next();
//                            Socket socket = ServerThread.map.get(username);
//                            IOUtil.writeMessage(socket,message);
//                        }
//                        System.exit(0);
//                    }
//                }else {
//                    System.exit(0);
//                }
            }
        });
        stopBtn.setBackground(Color.WHITE);
        stopBtn.setFont(new Font("宋体", 0, 14));
        stopBtn.setBounds(200, 400, 110, 30);
        return stopBtn;
    }

    /**
     * 保存日志按钮
     *
     * @return
     */
    public JButton saveLogBtn() {
        JButton saveLogBtn = new JButton("保存日志");
        saveLogBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = txtLog.getText();
//				System.out.println(text);
                try {
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("resources\\log\\" + DateUtil.dateToString(new Date()).replaceAll("-", "").replaceAll(":", "") + ".txt")));
                    writer.write(text);
                    writer.flush();
                    writer.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
        saveLogBtn.setBackground(Color.WHITE);
        saveLogBtn.setFont(new Font("宋体", 0, 14));
        saveLogBtn.setBounds(320, 400, 110, 30);
        return saveLogBtn;
    }
}
