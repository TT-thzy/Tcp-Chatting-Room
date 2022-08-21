package com.qq.ui.login;

import com.qq.message.ChatStatus;
import com.qq.message.Message;
import com.qq.pojo.User;
import com.qq.util.ConnectUtil;
import com.qq.util.MD5Util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class MyChangepass3 extends JFrame {

    Message message = null;

    public MyChangepass3(Message message) {
        this.message = message;
        init();
    }

    //---north面板
    private JPanel jp_north = new JPanel();
    //	//---center面板
    private JPanel jp_center = new JPanel();
    //	//---south面板
    private JPanel jp_south = new JPanel();
    private GridBagLayout gbl = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();

    private ImageIcon icon_system = new ImageIcon("resources\\image\\loginIcon\\图标.png");
    private JLabel jbl_hint1 = new JLabel("找回密码");
    private JLabel jbl_hint2 = new JLabel("请输入新的密码");
    private JPasswordField jpf_password = new JPasswordField();
    private JLabel jbl_hint3 = new JLabel("请再次输入新的密码");
    private JPasswordField jpf_password2 = new JPasswordField();
    private JButton jb_start = new JButton("确定");

    private void init() {
        this.setSize(280, 300);
        this.setLocationRelativeTo(null);
        this.setTitle("找回密码");
        this.setIconImage(icon_system.getImage());
        this.setResizable(false);

        jp_north.setPreferredSize(new Dimension(280, 170));
        jp_north.setBackground(Color.white);
        jp_north.setLayout(gbl);
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbl.setConstraints(jbl_hint1, gbc);
        jp_north.add(jbl_hint1);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.REMAINDER;
        gbl.setConstraints(jbl_hint2, gbc);
        jp_north.add(jbl_hint2);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.REMAINDER;
        jpf_password.setPreferredSize(new Dimension(150, 20));
        gbl.setConstraints(jpf_password, gbc);
        jp_north.add(jpf_password);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.REMAINDER;
        gbl.setConstraints(jbl_hint3, gbc);
        jp_north.add(jbl_hint3);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.REMAINDER;
        jpf_password2.setPreferredSize(new Dimension(150, 20));
        gbl.setConstraints(jpf_password2, gbc);
        jp_north.add(jpf_password2);
        this.add(jp_north, BorderLayout.NORTH);


        jp_south.setBackground(Color.white);
        jp_south.setPreferredSize(new Dimension(280, 100));
        jb_start.setPreferredSize(new Dimension(80, 30));
        jb_start.setOpaque(true);//透明化
        jb_start.setFocusPainted(false);//选中后不设置边框
        jb_start.setBackground(Color.white);//背景
        jb_start.setForeground(Color.gray);//前景（文字）
        jp_south.add(jb_start);
        this.add(jp_south, BorderLayout.SOUTH);
//		jb_start.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('\n',0), "start");
//		jb_start.getActionMap().put("start",new AbstractAction() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				System.out.println(jpf_password.getText());
//				System.out.println("ok");
//				MyChangepass3.this.dispose();
//				new MyChangepass4();
//			}
//		});
//		jb_start.addActionListener(new ActionListener() {	
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				System.out.println(jpf_password.getText());
//				System.out.println("ok");
//				MyChangepass3.this.dispose();
//				new MyChangepass4(message);
//			}
//		});
        jb_start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(jpf_password.getText());
                if (String.valueOf(jpf_password.getPassword()).equals(String.valueOf(jpf_password2.getPassword()))) {
                    System.out.println(jpf_password.getText());
                    System.out.println("ok");
                    User user = message.getUser();
                    System.out.println(user.getUsername() + "==" + user.getPassword());
                    user.setPassword(MD5Util.encode(jpf_password.getText()));
                    message.setUser(user);
                    message.setChatStatus(ChatStatus.CHANGEPASSWORD);
                    System.out.println(message.getUser().getUsername());
                    System.out.println(message.getUser().getPassword());
                    ConnectUtil.ForgotConnectServer(message, MyChangepass3.this);
                    //new Changepasshint5();
                    //MyChangepass3.this.dispose();
                } else {
                    new Changepasshint3();
                }

            }
        });

        this.setVisible(true);
    }
}
