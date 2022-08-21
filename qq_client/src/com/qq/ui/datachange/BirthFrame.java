package com.qq.ui.datachange;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BirthFrame extends JFrame{
	public BirthFrame(ChangedataFrame f1) {
		f2=f1;
		init();
	}

	//---center面板
	public ChangedataFrame f2;
	private JPanel jp_center =new JPanel();
	private GridBagLayout gbl =new GridBagLayout();
	private GridBagConstraints gbc =new GridBagConstraints();
	private JComboBox<String> jcb_year =new JComboBox<String>();
	private JComboBox<String> jcb_month =new JComboBox<String>();
	private JComboBox<Integer> jcb_day =new JComboBox<Integer>();
	private JButton jb_save =new JButton("保存");
	private ImageIcon icon_system =new ImageIcon("resources\\image\\loginIcon\\图标.png");
	public String birth;
	private void init() {
		this.setSize(350, 80);
		this.setLocationRelativeTo(null);
		this.setTitle("生日");
		this.setIconImage(icon_system.getImage());
		this.setResizable(false);
		int year=Integer.parseInt(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
		for (int i = 1970; i <= year; i++) {
			jcb_year.addItem(i+"年");
		}
		jcb_year.setSelectedItem("2021年");
		jcb_year.setPreferredSize(new Dimension(80,20));
//		this.add(jcb_year);
		for (int i = 1; i <= 12; i++) {
			jcb_month.addItem(i+"月");
		}
		jcb_month.setPreferredSize(new Dimension(60,20));
		
//		this.add(jcb_month);
		
		jp_center.setBackground(Color.white);
		jp_center.setLayout(gbl);
		gbc.insets =new Insets(5, 5, 5, 5);
		gbc.gridx =1;
		gbc.gridy =1;		
		gbl.setConstraints(jcb_year, gbc);
		jp_center.add(jcb_year);
		gbc.gridx =2;
		gbc.gridy =1;		
		gbl.setConstraints(jcb_month, gbc);
		jp_center.add(jcb_month);
		
	    
	    jcb_month.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String year=(String)jcb_year.getSelectedItem();
				int yearInt=Integer.parseInt(year.substring(0,4));
				String month=(String) jcb_month.getSelectedItem();
				jcb_day.removeAllItems();
				switch(month){
				  case "4月":
				  case "6月":
				  case "9月":
				  case "11月":
					  for (int i = 1; i <=30; i++) {
							jcb_day.addItem(i);
					}
					  break;
				  case "2月":
					  for (int i = 1; i <=28; i++) {
							jcb_day.addItem(i);
					  }
					  if(yearInt%4==0&&yearInt%100!=0||yearInt%400==0) {
						  jcb_day.addItem(29);
					  }
					  break;
				  default:
					  for (int i = 1; i <=31; i++) {
							jcb_day.addItem(i);
					  }
					  break;
				}
			}
		});

	    jcb_year.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String year=(String)jcb_year.getSelectedItem();
				int yearInt=Integer.parseInt(year.substring(0,4));
				String month=(String) jcb_month.getSelectedItem();
				jcb_day.removeAllItems();
				switch(month){
				  case "4月":
				  case "6月":
				  case "9月":
				  case "11月":
					  for (int i = 1; i <=30; i++) {
							jcb_day.addItem(i);
					}
					  break;
				  case "2月":
					  for (int i = 1; i <=28; i++) {
							jcb_day.addItem(i);
					  }
					  if(yearInt%4==0&&yearInt%100!=0||yearInt%400==0) {
						  jcb_day.addItem(29);
					  }
					  break;
				  default:
					  for (int i = 1; i <=31; i++) {
							jcb_day.addItem(i);
					  }
					  break;
				}
			}
		});
			
		
		

	    gbc.gridx =3;
		gbc.gridy =1;	
		jcb_day.setPreferredSize(new Dimension(60,20));
		for (int i = 1; i <=31; i++) {
			jcb_day.addItem(i);
		}
		
		jcb_day.setSelectedItem(1);
		gbl.setConstraints(jcb_day, gbc);
		jp_center.add(jcb_day);
		
		jb_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BirthFrame.this.dispose();
				birth=(String) jcb_year.getSelectedItem()+(String) jcb_month.getSelectedItem()+jcb_day.getSelectedItem()+"日";
				f2.jtf_birth.setText(birth);
				
				
			}
		});
	    gbc.gridx =4;
	    gbc.gridy =1;
	    jb_save.setOpaque(true);//透明化
	    jb_save.setFocusPainted(false);//选中后不设置边框
	    jb_save.setBackground(Color.white);//背景
	    jb_save.setForeground(Color.gray);//前景（文字）
		jb_save.setPreferredSize(new Dimension(60,20));
		gbl.setConstraints(jb_save, gbc);
		jp_center.add(jb_save);
		
	    this.add(jp_center,BorderLayout.CENTER);
		this.setVisible(true);
		
	}
}
