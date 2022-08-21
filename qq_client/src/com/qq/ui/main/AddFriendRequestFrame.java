package com.qq.ui.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.MouseInputListener;

import com.qq.pojo.User;
/**
 * 好友申请界面
 * */
public class AddFriendRequestFrame extends JFrame{
	private User user;
	private JPanel jp_north=new MyImagePanel();
	private JPanel jp_south=new JPanel();
	private JButton close=new JButton("×");
	private JButton small=new JButton("—");
	private JButton ok=new JButton("同意");
	private JButton no=new JButton("拒绝");

	private ImageIcon qqimg=new ImageIcon(Toolkit.getDefaultToolkit().createImage("resources\\image\\mainIcon\\QQtopTitle.png").getScaledInstance(20,20,Image.SCALE_DEFAULT));

	private JLabel jlb_title=new JLabel("添加好友");
	private JLabel jlb_qq=new JLabel(qqimg);

	private Font jbfont=new Font("微软雅黑",Font.PLAIN,20);
	private Font tfont=new Font("微软雅黑",Font.PLAIN,15);
	private Font addfont=new Font("微软雅黑",Font.PLAIN,12);

	private JLabel jlb_name=null;

	public AddFriendRequestFrame(User user){
		this.user=user;
		init();
	}

	public void init(){
		this.setSize(500,300);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		//--------------jp_north---------------
		jp_north.setPreferredSize(new Dimension(500,35));

		GridBagLayout gbl=new GridBagLayout();
		GridBagConstraints gbc=new GridBagConstraints();
		jp_north.setLayout(gbl);

		gbc.gridx=1;
		gbc.gridy=1;
		jlb_qq.setPreferredSize(new Dimension(30,35));
		gbl.setConstraints(jlb_qq, gbc);
		jp_north.add(jlb_qq);

		gbc.gridx=2;
		gbc.gridy=1;
		jlb_title.setPreferredSize(new Dimension(370,35));
		jlb_title.setForeground(Color.white);
		jlb_title.setFont(tfont);
		gbl.setConstraints(jlb_title, gbc);
		jp_north.add(jlb_title);

		gbc.gridx=3;
		gbc.gridy=1;
		small.setPreferredSize(new Dimension(40,35));
		small.setContentAreaFilled(false);
		small.setBorder(BorderFactory.createEmptyBorder());
		small.setForeground(Color.white);
		gbl.setConstraints(small, gbc);
		jp_north.add(small);

		gbc.gridx=4;
		gbc.gridy=1;
		close.setPreferredSize(new Dimension(40,35));
		close.setContentAreaFilled(false);
		close.setBorder(BorderFactory.createEmptyBorder());
		close.setForeground(Color.white);
		close.setFont(jbfont);
		gbl.setConstraints(close, gbc);
		jp_north.add(close);

		close.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				close.setForeground(Color.white);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				close.setForeground(new Color(147,112,219));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton()==MouseEvent.BUTTON1) {
					AddFriendRequestFrame.this.dispose();
				}


			}
		});

		small.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				small.setForeground(Color.white);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				small.setForeground(new Color(147,112,219));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton()==MouseEvent.BUTTON1) {
					AddFriendRequestFrame.this.setExtendedState(JFrame.ICONIFIED);
				}


			}
		});


		//--------------jp_north---------------
		jp_south.setPreferredSize(new Dimension(500,450));
		jp_south.setBackground(new Color(235,242,239));

		GridBagLayout gbl1=new GridBagLayout();
		GridBagConstraints gbc1=new GridBagConstraints();
		jp_south.setLayout(gbl1);

		gbc1.gridx=1;
		gbc1.gridy=1;
		gbc1.gridwidth=2;
		gbc1.insets=new Insets(120, 0, 0, 20);
		jlb_name=new JLabel(user.getNickname()+"请求添加你为好友");
		jlb_name.setFont(tfont);
		jlb_name.setPreferredSize(new Dimension(150,30));
		gbl1.setConstraints(jlb_name, gbc1);
		jp_south.add(jlb_name);

		gbc1.gridx=1;
		gbc1.gridy=2;
		gbc1.gridwidth=1;
		gbc1.insets=new Insets(20, 0, 0, 20);
		ok.setPreferredSize(new Dimension(60,30));
		ok.setBackground(new Color(0,155,219));
		ok.setForeground(Color.white);
		ok.setFont(addfont);
		gbl1.setConstraints(ok, gbc1);
		jp_south.add(ok);

		gbc1.gridx=2;
		gbc1.gridy=2;
		no.setPreferredSize(new Dimension(60,30));
		no.setBackground(new Color(0,155,219));
		no.setForeground(Color.white);
		no.setFont(addfont);
		gbl1.setConstraints(no, gbc1);
		jp_south.add(no);

		//点击同意
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {


			}
		});

		//点击不同意
		no.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {


			}
		});

		this.getContentPane().add(jp_north,BorderLayout.NORTH);
		this.getContentPane().add(jp_south,BorderLayout.SOUTH);

		//窗口拖拽
		MouseEventListener mouseListener = new MouseEventListener(this);
	    this.addMouseListener(mouseListener);
	    this.addMouseMotionListener(mouseListener);
	    this.setVisible(true);



	}

	class MouseEventListener implements MouseInputListener {

	    Point origin;
	    //鼠标拖拽想要移动的目标组件
	    AddFriendRequestFrame frame;

	    public MouseEventListener(AddFriendRequestFrame frame) {
	      this.frame = frame;
	      origin = new Point();
	    }

	    @Override
	    public void mouseClicked(MouseEvent e) {}

	    //记录鼠标按下时的点
	    @Override
	    public void mousePressed(MouseEvent e) {
	      origin.x = e.getX();
	      origin.y = e.getY();
	    }

	    @Override
	    public void mouseReleased(MouseEvent e) {}
	    @Override
	    public void mouseEntered(MouseEvent e) {
	    }

	    @Override
	    public void mouseExited(MouseEvent e) {
	    }

	    //鼠标在标题栏拖拽时，设置窗口的坐标位置
	    //窗口新的坐标位置 = 移动前坐标位置+（鼠标指针当前坐标-鼠标按下时指针的位置）
	    @Override
	    public void mouseDragged(MouseEvent e) {
	      Point p = this.frame.getLocation();
	      this.frame.setLocation(
	        p.x + (e.getX() - origin.x),
	        p.y + (e.getY() - origin.y));
	    }

	    @Override
	    public void mouseMoved(MouseEvent e) {}

	   }

}
