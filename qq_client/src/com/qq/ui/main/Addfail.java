package com.qq.ui.main;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.qq.pojo.User;
import com.qq.ui.main.AddFrame;

/**
 * 添加失败页面
 * */
public class Addfail extends JFrame{
	private JPanel jp=new JPanel();
	private JLabel jlb=new JLabel("账号错误或不存在");
	private Font addfont=new Font("微软雅黑",Font.PLAIN,12);
	public Addfail(){
		init();
	}

	public void init(){
		this.setSize(150,50);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		GridBagLayout gbl=new GridBagLayout();
		GridBagConstraints gbc=new GridBagConstraints();
		jp.setLayout(gbl);

		gbc.gridx=1;
		gbc.gridy=1;
		jlb.setPreferredSize(new Dimension(100,50));
		gbl.setConstraints(jlb, gbc);
		jp.add(jlb);


		jlb.setFont(addfont);

		this.getContentPane().add(jp);
		this.setVisible(true);

	}
	public static void main(String[] args) {

//		new AddFrame(new User());
	}

}
