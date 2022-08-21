package com.qq.ui.register;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class Warn  extends JFrame{
	private JPanel jp=new JPanel();
	private JLabel jlb=new JLabel("请将信息填写完整");
	private Font addfont=new Font("微软雅黑",Font.PLAIN,12);
	public Warn(){
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

}