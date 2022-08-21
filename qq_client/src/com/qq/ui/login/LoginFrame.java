package com.qq.ui.login;

import com.qq.message.ChatStatus;
import com.qq.message.Message;
import com.qq.pojo.User;
import com.qq.ui.register.Register;
import com.qq.util.ConnectUtil;
import com.qq.util.FileUtil;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.*;


public class LoginFrame extends JFrame{
	public LoginFrame() {
		init();
	}
	//---north面板
	private JPanel jp_north =new JPanel();
	//---center面板
	private JPanel jp_center =new JPanel();
	//---south面板
	private JPanel jp_south =new JPanel();
	//---网袋布局
	private GridBagLayout gbl =new GridBagLayout();
	private GridBagConstraints gbc =new GridBagConstraints();
	//---组件
	private ImageIcon icon_loginBackground =new ImageIcon("resources\\image\\loginIcon\\登录背景.png");
	private Icon icon_acNumber =new ImageIcon("resources\\image\\loginIcon\\账号.png");
	private Icon icon_password =new ImageIcon("resources\\image\\loginIcon\\密码.png");
	private ImageIcon icon_system =new ImageIcon("resources\\image\\loginIcon\\图标.png");
	private JLabel jbl_loginBackground =new JLabel();
	private JLabel jbl_acNumbericon =new JLabel(icon_acNumber);
	private JComboBox<String> jtf_acNumber =new JComboBox<String>();
	private DefaultComboBoxModel<String> dcbm= new DefaultComboBoxModel();
	private JLabel jbl_password= new JLabel(icon_password);
	private JPasswordField jpf_password =new JPasswordField();
	private JCheckBox jcb_atlogin =new JCheckBox("自动登录");
	private JCheckBox jcb_rmpassword =new JCheckBox("记住密码");
	private JButton jb_fbpassword=new JButton("找回密码");
	private JButton jb_login=new JButton("登录");
	private JButton jb_register=new JButton("注册账号");
	//最小化相关
	private SystemTray  systemTray = SystemTray.getSystemTray();//系统托盘
	private TrayIcon  icon  = null ;//icon图标
	private MenuItem back = new MenuItem("还原");//菜单项
	private MenuItem exit = new MenuItem("退出");//菜单项
	private PopupMenu pm = new PopupMenu();//托盘图标用菜单

	//工具
	private Set<String> set=new HashSet<>();

	
	public void init() {
		//------------窗体设置----------------
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 330);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setTitle("QQ");
		this.setIconImage(icon_system.getImage());
		icon = new TrayIcon(Toolkit.getDefaultToolkit().createImage("resources\\image\\loginIcon\\最小化离线.png").getScaledInstance(15, 15, Image.SCALE_SMOOTH)
				,"我的qq");//设置图标icon
		pm.add(back);//添加菜单项
		pm.add(exit);//添加菜单项
		icon.setPopupMenu(pm);
//		this.addWindowListener(new WindowAdapter() {
//			public void windowIconified(WindowEvent e) {
//				LoginFrame.this.setVisible(false);
//				try {
//					systemTray.add(icon);
//
//				} catch (Exception e2) {
//					e2.printStackTrace();
//				}
//			}
//		});
//		//小图标返回添加事件
//		icon.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				int clt =e.getClickCount();
//				if (clt==2) {
//					systemTray.remove(icon);
//					LoginFrame.this.setVisible(true);
//					LoginFrame.this.setState(JFrame.NORMAL);
//				}
//			}
//		});
//		back.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				systemTray.remove(icon);
//				LoginFrame.this.setVisible(true);
//				LoginFrame.this.setState(JFrame.NORMAL);
//			}
//		});
//		//小图标退出添加事件
//		exit.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				System.exit(0);
//			}
//		});
		//----------north面板设置-------------
		jp_north.setPreferredSize(new Dimension(350,100));
		//jp_north.setBackground(new Color(100,150,255));
		icon_loginBackground.setImage(icon_loginBackground.getImage().getScaledInstance(jbl_loginBackground.getWidth()+400,jbl_loginBackground.getHeight()+110, Image.SCALE_DEFAULT));
		jbl_loginBackground.setIcon(icon_loginBackground);
		jp_north.add(jbl_loginBackground);
		this.add(jp_north,BorderLayout.NORTH);
		//----------center面板设置------------
		jp_center.setBackground(Color.white);
		jp_center.setLayout(gbl);
		gbc.insets =new Insets(5, 5, 5, 5);
		
		//---账号图片标签设置
		gbc.gridx =1;
		gbc.gridy =1;		
		gbl.setConstraints(jbl_acNumbericon, gbc);
		jp_center.add(jbl_acNumbericon);
		
		//---账号文本框设置
		jtf_acNumber.setEditable(true);
		gbc.gridx =2;
		gbc.gridy =1;
		gbc.gridwidth=2;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		gbl.setConstraints(jtf_acNumber, gbc);
		jp_center.add(jtf_acNumber);

		//----修改------------------------------------------------------------
		set=FileUtil.get();
		for (String string : set) {
			if(jtf_acNumber.getItemCount()==7) {
				jtf_acNumber.remove(0);
				jtf_acNumber.addItem(string.substring(0,string.indexOf("=")));

			}
			else {
				jtf_acNumber.addItem(string.substring(0,string.indexOf("=")));
			}
		}
		String[] pass =set.toArray(new String[set.size()]);
		//----修改------------------------------------------------------------

		//---密码图片标签设置
		gbc.gridx =1;
		gbc.gridy =2;
		gbc.gridwidth=1;
		gbc.fill=GridBagConstraints.REMAINDER;
		gbl.setConstraints(jbl_password, gbc);
		jp_center.add(jbl_password);
		
		//---密码框设置
		gbc.gridx =2;
		gbc.gridy =2;
		gbc.gridwidth=2;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		gbl.setConstraints(jpf_password, gbc);
		jp_center.add(jpf_password);
		
		//---复选框设置
		gbc.insets =new Insets(5, 10, 5, 5);
		gbc.gridx =1;
		gbc.gridy =3;
		gbc.gridwidth=1;
		jcb_atlogin.setOpaque(true);//透明化
		jcb_atlogin.setBorderPainted(false);//不绘制边框
		jcb_atlogin.setFocusPainted(false);//选中后不设置边框
		jcb_atlogin.setBackground(Color.white);//背景
		jcb_atlogin.setForeground(Color.gray);//前景（文字）
		gbl.setConstraints(jcb_atlogin, gbc);
		jp_center.add(jcb_atlogin);
		gbc.gridx =2;
		gbc.gridy =3;
		jcb_rmpassword.setOpaque(true);//透明化
		jcb_rmpassword.setBorderPainted(false);//不绘制边框
		jcb_rmpassword.setFocusPainted(false);//选中后不设置边框
		jcb_rmpassword.setBackground(Color.white);//背景
		jcb_rmpassword.setForeground(Color.gray);//前景（文字）
		gbl.setConstraints(jcb_rmpassword, gbc);
		jp_center.add(jcb_rmpassword);
		//---找回密码按钮设置
		gbc.gridx =3;
		gbc.gridy =3;
		jb_fbpassword.setOpaque(true);//透明化
		jb_fbpassword.setBorderPainted(false);//不绘制边框
		jb_fbpassword.setFocusPainted(false);//选中后不设置边框
		jb_fbpassword.setBackground(Color.white);//背景
		jb_fbpassword.setForeground(Color.gray);//前景（文字）
		gbl.setConstraints(jb_fbpassword,gbc);
		jp_center.add(jb_fbpassword);
		//---登录按钮设置
		gbc.gridx =1;
		gbc.gridy =4;
		gbc.gridwidth=3;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		jb_login.setOpaque(true);//透明化
		jb_login.setBorderPainted(false);//不绘制边框
		jb_login.setFocusPainted(false);//选中后不设置边框
		jb_login.setBackground(Color.BLUE);//背景
		jb_login.setForeground(Color.white);//前景（文字）
		
		gbl.setConstraints(jb_login,gbc);
		jp_center.add(jb_login);
		//----------south面板设置------------
		jp_south.setLayout(new FlowLayout(FlowLayout.LEFT,10,5));
		jp_south.setBackground(Color.white);
		this.add(jp_south,BorderLayout.SOUTH);
		jb_register.setOpaque(true);//透明化
		jb_register.setBorderPainted(false);//不绘制边框
		jb_register.setFocusPainted(false);//选中后不设置边框
		jb_register.setBackground(Color.white);//背景
		jb_register.setForeground(Color.gray);//前景（文字）
		jp_south.add(jb_register);
		
		

		jb_login.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('\n',0), "login");
		jtf_acNumber.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					jtf_acNumber.addItem(e.getItem().toString());
					if(jcb_rmpassword.isSelected()) {
						FileUtil.save(jtf_acNumber.getSelectedItem()+"="+jpf_password.getText());
						System.out.println(jtf_acNumber.getSelectedItem()+"="+jpf_password.getText());
					}
//					//--------修改---------------------
//					if(jcb_atlogin.isSelected()) {
//						String passtext=pass[jtf_acNumber.getSelectedIndex()];
//						String str1 =passtext.substring(0,passtext.indexOf("="));
//						String str2 =passtext.substring(str1.length()+1,passtext.length());
//						jpf_password.setText(str2);
//						new MyQQFrameLoading();
//						MyQQFrame.this.dispose();
//					}
					//--------修改--------------------
				}			
			}
		});
//		jb_login.getActionMap().put("login",new AbstractAction() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				System.out.println(jtf_acNumber.getSelectedItem());
//				System.out.println(jpf_password.getText());
//				new MyQQFrameLoading();
//				LoginFrame.this.dispose();
//			}
//		});
		//========update==========
		jb_login.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
//				System.out.println(jtf_acNumber.getSelectedItem());
//				System.out.println(jpf_password.getText());
//				if ("test".equals(jtf_acNumber.getSelectedItem())&&"test".equals(jpf_password.getText())){
//					new MyQQFrameLoading();
//					LoginFrame.this.dispose();
//				}else {
//					JOptionPane.showMessageDialog(LoginFrame.this,"登陆信息有误!");
//				}
				if(jcb_rmpassword.isSelected()) {
					FileUtil.save(jtf_acNumber.getSelectedItem()+"="+jpf_password.getText());
					System.out.println(jtf_acNumber.getSelectedItem()+"="+jpf_password.getText());
				}
				String username=String.valueOf(jtf_acNumber.getSelectedItem());
				String password=jpf_password.getText();
				System.out.println(username+"="+password);
				User user = new User();
				user.setUsername(username);
				user.setPassword(password);
				Message message = new Message();
				message.setUser(user);
				message.setChatStatus(ChatStatus.LOGIN);
				ConnectUtil.LoginConnectServer(message,LoginFrame.this);
			}
		});
		//========update==========
		jb_fbpassword.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				//========update==========
				new MyChangepass();
			}
		});

		jtf_acNumber.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(jtf_acNumber.getSelectedIndex()!=-1) {
					String passtext=pass[jtf_acNumber.getSelectedIndex()];
					String str1 =passtext.substring(0,passtext.indexOf("="));
					String str2 =passtext.substring(str1.length()+1,passtext.length());
					jpf_password.setText(str2);
				}
			}
		});

		jb_register.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//========update==========
				new Register();
				LoginFrame.this.dispose();
			}
		});
		
		
		this.add(jp_center);
		this.setVisible(true);
	}
	
}
