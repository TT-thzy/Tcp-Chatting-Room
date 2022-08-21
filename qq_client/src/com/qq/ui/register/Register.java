package com.qq.ui.register;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

public class Register extends JFrame{
	//默认新建面板是流式布局
	//设置注册背景图片
	private JPanel jP=new JPanel(){
		public void paintComponent(Graphics g){
			g.drawImage(new ImageIcon("resources\\image\\registerIcon\\picc.png").getImage(), 0,0,this.getWidth(),this.getHeight(),null);
		}
	};
	//设置组件
	//标题
	private  JLabel jlbtitle1=new JLabel();
	private  JLabel jlbtitle2=new JLabel();
	//昵称
	private JLabel jlbname=new JLabel();
	public JTextField jtfname=new JTextField();
	//设置密码
	private JLabel jlbpassword=new JLabel();
	public JTextField jtfpassword=new JTextField();
	//区域号码下拉框
	private JComboBox<String> jcbnumber=new JComboBox<String>();
	public DefaultComboBoxModel<String> dcbmnumber=new DefaultComboBoxModel<String>();
	//手机号码
	private JLabel jlbTelephone=new JLabel();
	public JTextField jtfTelephone =new JTextField();
	//提示
	private  JLabel jlbTip=new JLabel();
	//幸运数字
	public JTextField jtfTip=new JTextField();
	//注册
	private JButton jbtRegister =new JButton();

	public Register() {
		init();
	}

	public void init() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1800, 1000);
		this.setTitle("MyQQ");
		this.setLocationRelativeTo(null);


		//设置注册按钮样式
		jbtRegister.setContentAreaFilled(true);//无填充
		jbtRegister.setBorderPainted(false);
		jbtRegister.setBackground(Color.BLUE);
		jbtRegister.setForeground(Color.WHITE);

		//设置布局(空布局)
		jP.setLayout(null);
		this.setContentPane(jP);

		//定义组件
		//标题
		//给标题设置文字
		jlbtitle1.setFont(new Font("黑体",Font.BOLD,60));
		jlbtitle1.setText("欢迎注册QQ");
		jlbtitle1.setBounds(1050,40,500,200);
		jP.add(jlbtitle1);
		jlbtitle2.setFont(new Font("黑体",Font.BOLD,50));
		jlbtitle2.setText("每一天，乐在沟通");
		jlbtitle2.setBounds(1050,100,500,200);
		jP.add(jlbtitle2);
//		昵称
		jlbname.setFont(new Font("黑体",Font.BOLD,40));
		jlbname.setText("账号：");
		jlbname.setBounds(1050,220,500,100);
		jP.add(jlbname);
		jtfname.setBounds(1050,300,450,60);
		jP.add(jtfname);
		//设置密码
		jlbpassword.setFont(new Font("黑体",Font.BOLD,40));
		jlbpassword.setText("设置密码：");
		jlbpassword.setBounds(1050,350,500,100);
		jP.add(jlbpassword);
		jtfpassword.setBounds(1050,430,450,60);
		jP.add(jtfpassword);

		//手机号码
		jlbTelephone.setFont(new Font("黑体",Font.BOLD,40));
		jlbTelephone.setText("请输入手机号码:");
		jlbTelephone.setBounds(1050,480,500,100);
		jP.add(jlbTelephone);
//      给区域号码下拉框添加内容
		dcbmnumber.addElement("+86");
		dcbmnumber.addElement("+852");
		dcbmnumber.addElement("+853");
		dcbmnumber.addElement("+886");
		jcbnumber.setModel(dcbmnumber);
		jcbnumber.setBounds(1050,560,120,60);
		jP.add(jcbnumber);
		//手机号码文本框
		jtfTelephone.setBounds(1180,560,320,60);
		jP.add(jtfTelephone);

		//小提示
		jlbTip.setFont(new Font("仿宋体",Font.BOLD,20));
		jlbTip.setText("你的幸运数字是？");
		jlbTip.setBounds(1050,600,300,100);
		jP.add(jlbTip);

		//提示文本框
		jtfTip.setBounds(1050,680,450,60);
		jP.add(jtfTip);

		//注册
		jbtRegister.setFont(new Font("仿宋体",Font.BOLD,40));
		jbtRegister.setText("立即注册");
		jbtRegister.setBounds(1050,760,450,60);
		jP.add(jbtRegister);


		this.setVisible(true);

		//监听事件

		jbtRegister.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isnull(jtfname.getText())&&isnull(jtfpassword.getText())&&isnull(jtfTelephone.getText())&&isnull(jtfTip.getText())) {
					new Edit(Register.this);
					Register.this.dispose();
				}else {

					final Timer timer=new Timer();
					JFrame jf = new Warn();
					timer.schedule(new TimerTask() {
						public void run() {
							if (jf!=null) {
								jf.dispose();

							}

						}
					}, 2000);
				}

			}
		});

		jtfname.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (maxlenth(jtfname.getText(), 20)) {
					e.consume();
				}
			}
		});

		//密码最多输入50个字节
		jtfpassword.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (maxlenth(jtfpassword.getText(), 50)) {
					e.consume();
				}
			}

		});


		//设置手机号码只能输入数字而且只能输入11位数
		jtfTelephone.addKeyListener(new KeyAdapter() {
			//手机号码只能输入数字
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar()>KeyEvent.VK_9||e.getKeyChar()<KeyEvent.VK_0) {
					e.consume();
				}
				//不能复制粘贴
				if (e.getKeyChar()==KeyEvent.VK_COPY) {
					e.consume();
				}
				//手机号码不能超过11位
				if (maxlenth(jtfTelephone.getText(), 20)) {
					e.consume();
				}

			}
		});
		//设置幸运数字只能输入数字
		jtfTip.addKeyListener(new KeyAdapter() {
			//密码只能输入数字
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar()>KeyEvent.VK_9||e.getKeyChar()<KeyEvent.VK_0) {
					e.consume();
				}
				//不能复制粘贴
				if (e.getKeyChar()==KeyEvent.VK_COPY) {
					e.consume();
				}
				if (maxlenth(jtfTip.getText(), 8)) {
					e.consume();
				}


			}
		});
	}
	public boolean maxlenth(String s,int len){
		if (s.length()>len) {
			return true;
		}else {
			return false;
		}
	}

	public boolean isnull(String s){
		if (s.length()==0) {
			return false;
		}else {
			return true;
		}


	}

	public static void main(String[] args) {
		new Register();
	}
}
