package com.qq.ui.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Changepasshint3 extends JFrame{
	 Changepasshint3(){
		 init();
	 }
	private JPanel jp_center =new JPanel();
	private GridBagLayout gbl =new GridBagLayout();
	private GridBagConstraints gbc =new GridBagConstraints();
    private JLabel jlb_hint1 = new JLabel("密码不一致");
    private JButton jb_confirm =new JButton("确定");
	private void init() {
		this.setSize(180, 180);
		this.setLocationRelativeTo(null);
		this.setTitle("提示");
		this.setResizable(false);
		this.setUndecorated(true);
		jb_confirm.setOpaque(true);//透明化
		jb_confirm.setFocusPainted(false);//选中后不设置边框
		jb_confirm.setBackground(Color.white);//背景
		jb_confirm.setForeground(Color.gray);//前景（文字）
		gbc.insets =new Insets(5, 5, 5, 5);
		gbc.gridx =1;
		gbc.gridy =1;		
		gbl.setConstraints(jlb_hint1, gbc);
		jp_center.add(jlb_hint1);	
		gbc.gridx =2;
		gbc.gridy =1;
		gbc.gridwidth=1;
		gbc.fill=GridBagConstraints.REMAINDER;
		gbl.setConstraints(jb_confirm, gbc);
		jp_center.add(jb_confirm);
		jp_center.setBackground(Color.white);
		this.add(jp_center,BorderLayout.CENTER);
		jb_confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Changepasshint3.this.dispose();
				
			}
		});
		this.setVisible(true);
		
	}
}
