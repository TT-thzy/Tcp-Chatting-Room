package com.qq.ui.login;

import com.qq.collection.ManagerListFrameCollection;
import com.qq.pojo.User;
import com.qq.ui.main.ListFrame;

import java.net.Socket;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class MyQQFrameLoading extends JFrame{

	User user=null;
	List<User> userList=null;
	Socket socket=null;
	ListFrame listFrame=null;

	public MyQQFrameLoading(User user, List<User> userList, Socket socket) {
		this.user=user;
		this.userList=userList;
		this.socket=socket;
		init();
	}
	private JLabel background=new JLabel(new ImageIcon("resources\\image\\loginIcon\\loading.gif"));
	int count = 0;
	private void init() {
		
		this.setSize(400, 330);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.add(background);
		this.setUndecorated(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
//		new Thread(){
//			@Override
//			public void run() {
//				try {
//					Thread.sleep(2000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				MyQQFrameLoading.this.dispose();
//				listFrame = new ListFrame(user, userList, socket);
//			}
//		}.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		MyQQFrameLoading.this.dispose();
		listFrame = new ListFrame(user, userList, socket);
		ManagerListFrameCollection.put(user.getUsername(),listFrame);
//		System.out.println("size"+ManagerListFrameCollection.map.size());
//		ListFrame listFrame = ManagerListFrameCollection.get(user.getUsername());
//		System.out.println(listFrame);
	}
	
		
	
}
