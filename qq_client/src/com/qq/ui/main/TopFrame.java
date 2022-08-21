package com.qq.ui.main;

import com.qq.pojo.User;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TopFrame extends JFrame{
	private JPanel jp=new MyImagePanel();
	private JPanel jp_south=new JPanel();
	
	private ImageIcon testimg=new ImageIcon(Toolkit.getDefaultToolkit().createImage("resources\\image\\mainIcon\\test.png").getScaledInstance(367,60,Image.SCALE_DEFAULT));

	//姓名
	private JLabel jlb_name=new JLabel("姓名");
	//账号
	private JLabel jlb_id=new JLabel("账号");
	private JLabel jlb_img=new JLabel(testimg);

	User user=null;

	public TopFrame(User user){
		this.user=user;
		init();
	}
	public void init(){
		this.setSize(367,250);
		this.setTitle("信息");
		this.setResizable(false);
		this.setUndecorated(true);
		
		GridBagLayout gbl=new GridBagLayout();
		GridBagConstraints gbc=new GridBagConstraints();
		jp.setLayout(gbl);

		if (user!=null){
			jlb_name.setText(user.getNickname());
			jlb_id.setText(user.getUsername());
		}

		gbc.insets=new Insets(0,20,0,50);
		gbc.gridx=1;
		gbc.gridy=1;
		gbc.gridwidth=1;
		jlb_name.setForeground(Color.white);
		gbl.setConstraints(jlb_name, gbc);
		jp.add(jlb_name);
		
		gbc.gridx=2;
		gbc.gridy=1;
		gbc.gridwidth=1;
		jlb_id.setForeground(Color.white);
		gbl.setConstraints(jlb_id, gbc);
		jp.add(jlb_id);
		

		jp_south.add(jlb_img);
		
		this.getContentPane().add(jp,BorderLayout.CENTER);
		this.getContentPane().add(jp_south,BorderLayout.SOUTH);
	}

}
