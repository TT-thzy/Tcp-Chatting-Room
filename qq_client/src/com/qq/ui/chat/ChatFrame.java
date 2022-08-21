package com.qq.ui.chat;

import com.qq.collection.ManagerChatFrameCollection;
import com.qq.message.ChatStatus;
import com.qq.message.Message;
import com.qq.pojo.FontStyle;
import com.qq.pojo.User;
import com.qq.util.FontSupport;
import com.qq.util.IOUtil;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.Socket;
import java.util.List;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileFilter;


public class ChatFrame extends JFrame{
//	public static void main(String[] args) {
//		new ChatFrame();
//	}
	private static final long serialVersionUID = -8945833334986986964L;

	/**
	 *  服务器窗体宽度
	 */
	public static final Integer f_WIDTH = 750;
	
	/**
	 * 服务器窗体高度
	 */
	public static final Integer f_HEIGHT = 605;

	User sender=null;

	User receiver=null;

	Socket socket=null;

	//接收框
	public JTextPane acceptPane;
	
	public ChatFrame(User sender,User receiver,Socket socket) {
		this.sender=sender;
		this.receiver=receiver;
		this.socket=socket;

		this.setTitle(sender.getNickname()+"正在与"+receiver.getNickname()+"聊天");
		setSize(f_WIDTH, f_HEIGHT);
		//窗体不可扩大
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//获取屏幕
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;
		//屏幕居中处理
		setLocation((width-f_WIDTH)/2, (height-f_HEIGHT)/2);
		
		//加载窗体的背景图片
		ImageIcon imageIcon = new ImageIcon("resources\\image\\chatIcon\\beijing.jpg");
		//创建一个标签并将图片添加进去
		JLabel fBg = new JLabel(imageIcon);
		//设置图片的位置和大小
		fBg.setBounds(0, 0, f_WIDTH, f_HEIGHT);
		this.add(fBg);
		//设置窗体图标
		ImageIcon icon_system = new ImageIcon("resources\\image\\chatIcon\\图标.png");
		this.setIconImage(icon_system.getImage());
		
		//接收框
	    acceptPane = new JTextPane();
		acceptPane.setOpaque(false);//设置透明
		acceptPane.setFont(new Font("宋体", 0, 16));
		
		//设置接收框滚动条
		JScrollPane scoPaneOne = new JScrollPane(acceptPane);
		scoPaneOne.setBounds(15, 20, 500, 332);
		//设置背景透明
//		contentPane.setLayout(new BorderLayout(0, 0));
//		setContentPane(contentPane);
//		ShadePanel shadePanel = new ShadePanel();// 创建渐变背景面板
		scoPaneOne.setOpaque(false);
		scoPaneOne.getViewport().setOpaque(false);
		fBg.add(scoPaneOne);
		
		
		//当前在线用户列表
//		JList lstUser = new JList();
//		lstUser.setFont(new Font("宋体", 0, 14));
//		lstUser.setVisibleRowCount(17);
//		lstUser.setFixedCellWidth(180);
//		lstUser.setFixedCellHeight(18);
//		
//		JScrollPane spUser = new JScrollPane(lstUser);
//		spUser.setFont(new Font("宋体", 0, 14));
//		spUser.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//		spUser.setBounds(530, 17, 200, 507);
//		fBg.add(spUser);
		ImageIcon rightIcon = new ImageIcon("resources\\image\\chatIcon\\liaotian.png");
		JLabel right = new JLabel(rightIcon);
		right.setBounds(500, 19, 250, 542);
		fBg.add(right);
		
		//输入框
		JTextPane sendPane = new JTextPane();
		sendPane.setOpaque(false);
		sendPane.setFont(new Font("宋体", 0, 16));
		
		JScrollPane scoPane = new JScrollPane(sendPane);
		scoPane.setBounds(15, 400, 500, 122);
		scoPane.setOpaque(false);
		scoPane.getViewport().setOpaque(false);
		fBg.add(scoPane);
		
		//添加表情选择
		JLabel lblface = new JLabel(new ImageIcon("resources\\image\\chatIcon\\QQbiaoqing.png"));
		lblface.setBounds(15, 363, 35, 35);
		fBg.add(lblface);
		
		//添加抖动效果
		JLabel lbldoudong = new JLabel(new ImageIcon("resources\\image\\chatIcon\\QQdoudong.png"));
		lbldoudong.setBounds(50, 363, 35, 35);
		fBg.add(lbldoudong);
		
//		//设置截图
//		JLabel lblcut = new JLabel(new ImageIcon("resources\\image\\chatIcon\\QQjietu.png"));
//		lblcut.setBounds(85, 363, 35, 35);
//		fBg.add(lblcut);
		
		//设置文件选项
		JLabel lblfile = new JLabel(new ImageIcon("resources\\image\\chatIcon\\QQwenjian.png"));
		lblfile.setBounds(85, 363, 35, 35);
		fBg.add(lblfile);
		
		//设置字体选项
		JButton lblfontchoose = new JButton(new ImageIcon("resources\\image\\chatIcon\\ziti.png"));
		lblfontchoose.setBounds(120, 363, 35, 35);
		fBg.add(lblfontchoose);
		
		
		//字体下拉选项
		JComboBox fontFamilyCmb = new JComboBox();
		GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		String[] str = graphicsEnvironment.getAvailableFontFamilyNames();
		for (String string : str) {
			fontFamilyCmb.addItem(string);
		}
		fontFamilyCmb.setSelectedItem("楷体");
		fontFamilyCmb.setBounds(195, 363, 150, 35);
		fBg.add(fontFamilyCmb);
		
		/*
		 * 发送按钮
		 */
		JButton send = new JButton("发       送");
		send.setBorderPainted(false);//按钮边框
		send.setFocusPainted(false);//选中后文字边框
		send.setForeground(Color.WHITE);
		send.setBackground(new Color(0,191,255));
		send.setBounds(400, 530, 114, 30);
		fBg.add(send);
		
		setVisible(true);

		//表情事件
		lblface.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) {
				new FaceFrame(sendPane);
			 }
		});
		//抖动事件
		lbldoudong.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Message message = new Message();
				sendPane.setText("抖动消息");
				List<FontStyle> fontStyles = FontSupport.fontEncode(sendPane);
				message.setChatStatus(ChatStatus.SINGLEDD);
				message.setSender(sender);
				message.setReceive(receiver);
				message.setContent(fontStyles);
				IOUtil.writeMessage(socket,message);
				sendPane.setText(null);
				List<FontStyle> content = message.getContent();
				FontSupport.fontDecode(acceptPane,content,message.getSender().getNickname(),message.getReceive().getNickname());
				System.out.println("shake!");
			}
		});
		//截图
//		lblcut.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				new photoCaptureFrame(sendPane);
//				System.out.println("截图");
//			}
//		});
		
		//文件
		lblfile.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new Thread(){
					@Override
					public void run() {
						new FileSendFrame(socket,sender,receiver);
					}
				}.start();
			}
		});
		//字体事件
		lblfontchoose.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JColorChooser colorChooser = new JColorChooser();
				Color color = colorChooser.showDialog(ChatFrame.this, "字体颜色", Color.BLACK);
				if (sendPane.getText()!=null){
					FontSupport.setFont(sendPane, color, fontFamilyCmb.getSelectedItem().toString(), Font.BOLD, 60);
				}
				System.out.println("字体");
			}
		});
		//发送事件
		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("发送");
				Message message = new Message();
				List<FontStyle> fontStyles = FontSupport.fontEncode(sendPane);
				message.setSender(sender);
				message.setReceive(receiver);
				message.setContent(fontStyles);
				message.setChatStatus(ChatStatus.SINGLECHAT);
				IOUtil.writeMessage(socket,message);
				sendPane.setText(null);
				List<FontStyle> content = message.getContent();
				FontSupport.fontDecode(acceptPane,content,message.getSender().getNickname(),message.getReceive().getNickname());

			}
		});

		ChatFrame.this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				System.out.println("shut down");
				ChatFrame chatFrame = ManagerChatFrameCollection.get(sender.getUsername() + "" + receiver.getUsername());
				ManagerChatFrameCollection.remove(sender.getUsername() + "" + receiver.getUsername());
				chatFrame.dispose();
			}
		});
	}
}
