package com.qq.ui.main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;



import com.qq.pojo.User;
import com.qq.util.PhotoUtil;



/**
 * 
 * 自定义渲染面板
 * 
 * */

public class MyQQCellPanelRender extends JPanel implements ListCellRenderer{
	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		User user=(User)value;
		
		JPanel jPanel=new JPanel();
		//测试数据
//		ImageIcon img=new ImageIcon(Toolkit.getDefaultToolkit().createImage("img/dog.jpg").getScaledInstance(45,45,Image.SCALE_DEFAULT));
		
		ImageIcon img=null;
		try {
			img = new ImageIcon(PhotoUtil.byteToPhoto(user.getPhoto()).getScaledInstance(45,45,Image.SCALE_DEFAULT));

		} catch (Exception e) {
			e.printStackTrace();
		}
		JLabel photo=new JLabel(img);
		
//		JLabel name=new JLabel("姓名");//测试数据
		JLabel name=new JLabel(user.getNickname());
		
		
		GridBagLayout gbl=new GridBagLayout();
		GridBagConstraints gbc=new GridBagConstraints();
		jPanel.setLayout(gbl);

		gbc.insets=new Insets(0,0,0,80);

		gbc.gridx=0;
		gbc.gridy=0;
		photo.setPreferredSize(new Dimension(100,50));
		gbl.setConstraints(photo, gbc);
		jPanel.add(photo);
		
		gbc.gridx=1;
		gbc.gridy=0;
		name.setFont(new Font("微软雅黑",Font.PLAIN,12));
		name.setPreferredSize(new Dimension(200,50));
		gbl.setConstraints(name, gbc);
		jPanel.add(name);
		jPanel.setBackground(Color.white);
		if (isSelected) {
			jPanel.setBackground(new Color(245,245,245));
		}
		System.out.println(user);
		if	(user.getStatu()==0){
			jPanel.setBackground(Color.GRAY);
		}
		return jPanel;
	}

}
