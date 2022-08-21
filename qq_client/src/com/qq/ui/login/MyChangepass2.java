package com.qq.ui.login;

import com.qq.message.Message;
import com.qq.util.ConnectUtil;

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
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import static com.qq.message.ChatStatus.CHECKFORQUESTION;

public class MyChangepass2 extends JFrame{

	Message message=null;

	public MyChangepass2(Message message) {
		this.message=message;
		init();
	}
	//---north面板
	private JPanel jp_north =new JPanel();
//	//---center面板
	private JPanel jp_center =new JPanel();
//	//---south面板
	private JPanel jp_south =new JPanel();
	private GridBagLayout gbl =new GridBagLayout();
	private GridBagConstraints gbc =new GridBagConstraints();
	
	private ImageIcon icon_system =new ImageIcon("resources\\image\\loginIcon\\图标.png");
	private JLabel jbl_hint1 =new JLabel("安全问题");
	private JLabel jbl_hint2 =new JLabel("请输入您的幸运数字");
	private JTextField jtf_acnumber = new JTextField();
	private JButton jb_start=new JButton("确定");
	
	private void init() {
		this.setSize(280, 300);
		this.setLocationRelativeTo(null);
		this.setTitle("找回密码");
		this.setIconImage(icon_system.getImage());
		this.setResizable(false);
		
		jp_north.setPreferredSize(new Dimension(280,100));
		jp_north.setBackground(Color.white);
		jp_north.setLayout(gbl);
		gbc.insets =new Insets(5, 5, 5, 5);
		gbc.gridx =1;
		gbc.gridy =1;		
		gbl.setConstraints(jbl_hint1, gbc);
		jp_north.add(jbl_hint1);	
		gbc.gridx =1;
		gbc.gridy =2;
		gbc.gridwidth=1;
		gbc.fill=GridBagConstraints.REMAINDER;
		gbl.setConstraints(jbl_hint2, gbc);
		jp_north.add(jbl_hint2);
		jp_north.setBackground(Color.white);
		this.add(jp_north,BorderLayout.NORTH);
		jp_center.setBackground(Color.white);
		jp_center.setPreferredSize(new Dimension(280,100));
		jtf_acnumber.setPreferredSize(new Dimension(180,30));
		jp_center.add(jtf_acnumber);
		this.add(jp_center,BorderLayout.CENTER);
		
		jp_south.setBackground(Color.white);
		jp_south.setPreferredSize(new Dimension(280,100));
		jb_start.setPreferredSize(new Dimension(80,30));
		jb_start.setOpaque(true);//透明化
		jb_start.setFocusPainted(false);//选中后不设置边框
		jb_start.setBackground(Color.white);//背景
		jb_start.setForeground(Color.gray);//前景（文字）
		jp_south.add(jb_start);
		this.add(jp_south,BorderLayout.SOUTH);
//		jb_start.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('\n',0), "start");
//		jb_start.getActionMap().put("start",new AbstractAction() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				System.out.println(jtf_acnumber.getText());
//				new MyChangepass3();
//				MyChangepass2.this.dispose();
//			}
//		});
		jb_start.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(jtf_acnumber.getText());
				message.setLuckyNUm(jtf_acnumber.getText());
				message.setChatStatus(CHECKFORQUESTION);
				ConnectUtil.LuckyNumConnectServer(message,MyChangepass2.this);
			}
		});
		this.setVisible(true);
	}
}
