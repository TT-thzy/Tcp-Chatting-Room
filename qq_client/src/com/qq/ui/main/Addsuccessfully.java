package com.qq.ui.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * 添加好友成功页面&&删除成功
 * */
public class Addsuccessfully extends JFrame{
	private JPanel jp=new JPanel();
	private JLabel jlb=new JLabel("操作成功！");
	private Font addfont=new Font("微软雅黑",Font.PLAIN,12);
	public Addsuccessfully(){
		init();
	}
	
	public void init(){
		this.setSize(100,50);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		GridBagLayout gbl=new GridBagLayout();
		GridBagConstraints gbc=new GridBagConstraints();
		jp.setLayout(gbl);
		
		gbc.gridx=1;
		gbc.gridy=1;
		jlb.setPreferredSize(new Dimension(60,50));
		gbl.setConstraints(jlb, gbc);
		jp.add(jlb);


		jlb.setFont(addfont);
		
		this.getContentPane().add(jp);
		this.setVisible(true);
		
	}
	

}
