package com.qq.ui.datachange;

import com.qq.message.ChatStatus;
import com.qq.message.Message;
import com.qq.util.DateUtil;
import com.qq.util.IOUtil;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.Socket;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class ChangedataFrame extends JFrame{
	 public ChangedataFrame(Usersdata usersdata) {
		 data=usersdata;
		init();
	}
		//---north面板
		private JPanel jp_north =new JPanel();
//		//---center面板
		private JPanel jp_center =new JPanel();
//		//---south面板
		private JPanel jp_south =new JPanel();
		private GridBagLayout gbl =new GridBagLayout();
		private GridBagConstraints gbc =new GridBagConstraints();
		public Usersdata data;
		private ImageIcon icon_system =new ImageIcon("resources\\image\\loginIcon\\图标.png");
		private JLabel jbl_name=new JLabel("昵称");
		private JTextField jtf_name = new JTextField();
		private JLabel jbl_sex =new JLabel("性别");
		private JComboBox<String> jcb_sex =new JComboBox<String>();
		private JTextField jtf_sex = new JTextField();
		private JLabel jbl_birth =new JLabel("生日");
		public JTextField jtf_birth = new JTextField();
		private JComboBox<Integer> jcb_year =new JComboBox<Integer>();
		private JLabel jbl_signature =new JLabel("签名");
		private JTextField jtf_signature = new JTextField();
		private JButton jb_start=new JButton("保存");
		private JButton jb_cancel=new JButton("取消");
		private BirthFrame jf_birth;

		private void init() {
			this.setSize(400, 300);
			this.setLocationRelativeTo(null);
			this.setTitle("编辑资料");
			this.setIconImage(icon_system.getImage());
			this.setResizable(false);
			jp_north.setPreferredSize(new Dimension(400,150));
			jp_north.setBackground(Color.white);
			jp_north.setLayout(gbl);
			gbc.insets =new Insets(5, 5, 5, 5);
			gbc.gridx =1;
			gbc.gridy =1;		
			gbl.setConstraints(jbl_name, gbc);
			jp_north.add(jbl_name);	
			gbc.gridx =2;
			gbc.gridy =1;
			jtf_name.setPreferredSize(new Dimension(80,20));
			jtf_name.setText(data.usersname);
			gbl.setConstraints(jtf_name, gbc);
			jp_north.add(jtf_name);	
			gbc.gridx =3;
			gbc.gridy =1;
			gbc.gridwidth=1;
			gbc.fill=GridBagConstraints.REMAINDER;
			gbl.setConstraints(jbl_sex, gbc);
			jp_north.add(jbl_sex);
			jp_north.setBackground(Color.white);
			gbc.gridx =4;
			gbc.gridy =1;
			gbc.gridwidth=1;
			gbc.fill=GridBagConstraints.REMAINDER;
			jcb_sex.setPreferredSize(new Dimension(80,20));
			jcb_sex.setBackground(Color.white);
			jcb_sex.addItem("男");
			jcb_sex.addItem("女");
			gbl.setConstraints(jcb_sex, gbc);
			jp_north.add(jcb_sex);
			gbc.gridx =1;
			gbc.gridy =2;
			gbc.gridwidth=1;
			gbc.fill=GridBagConstraints.REMAINDER;
			gbl.setConstraints(jbl_birth, gbc);
			jp_north.add(jbl_birth);
			gbc.gridx =2;
			gbc.gridy =2;
			gbc.gridwidth=2;
			gbc.fill=GridBagConstraints.REMAINDER;

			jtf_birth.setText("2000年01月01日");
			jtf_birth.setPreferredSize(new Dimension(120,20));
			gbl.setConstraints(jtf_birth, gbc);
			jp_north.add(jtf_birth);
			gbc.gridx =1;
			gbc.gridy =3;
			gbc.gridwidth=1;
			gbc.fill=GridBagConstraints.REMAINDER;
			gbl.setConstraints(jbl_signature, gbc);
			jp_north.add(jbl_signature);
			gbc.gridx =2;
			gbc.gridy =3;
			gbc.gridwidth=3;
			gbc.fill=GridBagConstraints.REMAINDER;
			jtf_signature.setPreferredSize(new Dimension(210,20));
			jtf_signature.setText(data.signature);
			gbl.setConstraints(jtf_signature, gbc);
			jp_north.add(jtf_signature);
			jp_north.setBackground(Color.white);
			
			this.add(jp_north,BorderLayout.NORTH);
			jp_center.setBackground(Color.white);
			jp_center.setPreferredSize(new Dimension(280,100));
			jb_start.setOpaque(true);//透明化
			jb_start.setFocusPainted(false);//选中后不设置边框
			jb_start.setBackground(Color.white);//背景
			jb_start.setForeground(Color.gray);//前景（文字）
			jp_center.add(jb_start);
			jb_cancel.setOpaque(true);//透明化
			jb_cancel.setFocusPainted(false);//选中后不设置边框
			jb_cancel.setBackground(Color.white);//背景
			jb_cancel.setForeground(Color.gray);//前景（文字）
			jp_center.add(jb_cancel);
			this.add(jp_center,BorderLayout.CENTER);
			
			jtf_birth.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {				
					jf_birth=new BirthFrame(ChangedataFrame.this);
					ChangedataFrame.this.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent e) {
							jf_birth.dispose();
						}
					}); 
				}
			});
			jb_start.addActionListener(new ActionListener() {
			
				public void actionPerformed(ActionEvent e) {
					String text = jtf_name.getText();
					String sex = String.valueOf(jcb_sex.getSelectedItem());
					String birthText = jtf_birth.getText();
					//2025年6月4日
					Date date = DateUtil.stringToDate(birthText, "yyyy年M月d日");
					java.sql.Date birth = new java.sql.Date(date.getTime());
					String signatureText = jtf_signature.getText();
					data.user.setNickname(text);
					data.user.setGender(sex);
					data.user.setBirthday(birth);
					data.user.setSignature(signatureText);

					Message message = new Message();
					message.setChatStatus(ChatStatus.CHANGEDATA);
					message.setUser(data.user);
					IOUtil.writeMessage(data.socket,message);
					try {
						Thread.sleep(2000);
					} catch (InterruptedException interruptedException) {
						interruptedException.printStackTrace();
					}
					ChangedataFrame.this.dispose();
				}
			});

			//取消
			jb_cancel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					ChangedataFrame.this.dispose();
				}
			});
			this.setVisible(true);
		
	}
}
