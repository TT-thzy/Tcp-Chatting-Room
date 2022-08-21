package com.qq.ui.main;

import com.qq.message.Message;
import com.qq.pojo.User;
import com.qq.util.IOUtil;

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
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

import static com.qq.message.ChatStatus.ADDFRIEND;
import static com.qq.message.ChatStatus.DELETEFRIEND;

/**
 * 添加好友窗口&&删除好友
 */
public class AddFrame extends JFrame {
    private User user;
    private JPanel jp_north = new MyImagePanel();
    private JPanel jp_south = new JPanel();
    private JButton close = new JButton("×");
    private JButton small = new JButton("—");
    private JButton add = new JButton("添加");
    private JButton delete = new JButton("删除");

    private ImageIcon qqimg = new ImageIcon(Toolkit.getDefaultToolkit().createImage("resources\\image\\mainIcon\\QQtopTitle.png").getScaledInstance(20, 20, Image.SCALE_DEFAULT));

    private JLabel jlb_title = null;
    private JLabel jlb_qq = new JLabel(qqimg);

    private Font jbfont = new Font("微软雅黑", Font.PLAIN, 20);
    private Font tfont = new Font("微软雅黑", Font.PLAIN, 15);
    private Font addfont = new Font("微软雅黑", Font.PLAIN, 12);

    private JTextField tadd = new JTextField();
    Socket socket = null;

    public AddFrame(User user, Socket socket) {
        this.socket = socket;
        this.user = user;
        init();
    }

    public void init() {
        this.setSize(500, 300);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        //--------------jp_north---------------
        jp_north.setPreferredSize(new Dimension(500, 35));

        jlb_title=new JLabel(user.getNickname()+"  添加好友");

        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        jp_north.setLayout(gbl);

        gbc.gridx = 1;
        gbc.gridy = 1;
        jlb_qq.setPreferredSize(new Dimension(30, 35));
        gbl.setConstraints(jlb_qq, gbc);
        jp_north.add(jlb_qq);

        gbc.gridx = 2;
        gbc.gridy = 1;
        jlb_title.setPreferredSize(new Dimension(370, 35));
        jlb_title.setForeground(Color.white);
        jlb_title.setFont(tfont);
        gbl.setConstraints(jlb_title, gbc);
        jp_north.add(jlb_title);

        gbc.gridx = 3;
        gbc.gridy = 1;
        small.setPreferredSize(new Dimension(40, 35));
        small.setContentAreaFilled(false);
        small.setBorder(BorderFactory.createEmptyBorder());
        small.setForeground(Color.white);
        gbl.setConstraints(small, gbc);
        jp_north.add(small);

        gbc.gridx = 4;
        gbc.gridy = 1;
        close.setPreferredSize(new Dimension(40, 35));
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
                close.setForeground(new Color(147, 112, 219));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    AddFrame.this.dispose();
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
                small.setForeground(new Color(147, 112, 219));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    AddFrame.this.setExtendedState(JFrame.ICONIFIED);
                }


            }
        });


        //--------------jp_north---------------
        jp_south.setPreferredSize(new Dimension(500, 450));
        jp_south.setBackground(new Color(235, 242, 239));

        GridBagLayout gbl1 = new GridBagLayout();
        GridBagConstraints gbc1 = new GridBagConstraints();
        jp_south.setLayout(gbl1);

        gbc1.gridx = 1;
        gbc1.gridy = 1;
        gbc1.gridwidth = 2;
        gbc1.insets = new Insets(120, 0, 0, 20);
        tadd.setPreferredSize(new Dimension(150, 30));
        gbl1.setConstraints(tadd, gbc1);
        jp_south.add(tadd);

        gbc1.gridx = 1;
        gbc1.gridy = 2;
        gbc1.gridwidth = 1;
        gbc1.insets = new Insets(20, 0, 0, 20);
        add.setPreferredSize(new Dimension(60, 30));
        add.setBackground(new Color(0, 155, 219));
        add.setForeground(Color.white);
        add.setFont(addfont);
        gbl1.setConstraints(add, gbc1);
        jp_south.add(add);

        gbc1.gridx = 2;
        gbc1.gridy = 2;
        delete.setPreferredSize(new Dimension(60, 30));
        delete.setBackground(new Color(0, 155, 219));
        delete.setForeground(Color.white);
        delete.setFont(addfont);
        gbl1.setConstraints(delete, gbc1);
        jp_south.add(delete);

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String text = tadd.getText();
                if (text != null) {
                    Message message = new Message();
                    message.setSender(user);
                    message.setFriendId(text);
                    message.setChatStatus(ADDFRIEND);
                    IOUtil.writeMessage(socket,message);
                }else {
                    JOptionPane.showMessageDialog(null,"账号不能为空!");
                }

//				try {
//					if (true) {
//						JFrame jf=new Addsuccessfully();
//						final Timer timer = new Timer();
//						timer.schedule(new TimerTask() {
//							public void run() {
//								if (jf!=null) {
//									jf.dispose();
//									timer.cancel();//计时器停止
//								}
//							}
//						}, 1000);
//
//					}else {
//						JFrame jf=new Addfail();
//						final Timer timer = new Timer();
//						timer.schedule(new TimerTask() {
//							public void run() {
//								if (jf!=null) {
//									jf.dispose();
//									timer.cancel();//计时器停止
//								}
//							}
//						}, 1000);
//					}
//				} catch (Exception e1) {
//					e1.printStackTrace();
//				}

            }
        });

        delete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String text = tadd.getText();
                if (text != null) {
                    Message message = new Message();
                    message.setSender(user);
                    message.setFriendId(text);
                    message.setChatStatus(DELETEFRIEND);
                    IOUtil.writeMessage(socket,message);
                }else {
                    JOptionPane.showMessageDialog(null,"账号不能为空!");
                }

//                try {
//                    if (true) {
//                        JFrame jf = new Addsuccessfully();
//                        final Timer timer = new Timer();
//                        timer.schedule(new TimerTask() {
//                            public void run() {
//                                if (jf != null) {
//                                    jf.dispose();
//                                    timer.cancel();//计时器停止
//                                }
//                            }
//                        }, 1000);
//
//                    } else {
//                        JFrame jf = new Addfail();
//                        final Timer timer = new Timer();
//                        timer.schedule(new TimerTask() {
//                            public void run() {
//                                if (jf != null) {
//                                    jf.dispose();
//                                    timer.cancel();//计时器停止
//                                }
//                            }
//                        }, 1000);
//                    }
//                } catch (Exception e1) {
//                    e1.printStackTrace();
//                }

            }
        });

        this.getContentPane().add(jp_north, BorderLayout.NORTH);
        this.getContentPane().add(jp_south, BorderLayout.SOUTH);

        //窗口拖拽
        MouseEventListener mouseListener = new MouseEventListener(this);
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);
        this.setVisible(true);


    }

    class MouseEventListener implements MouseInputListener {

        Point origin;
        //鼠标拖拽想要移动的目标组件
        AddFrame frame;

        public MouseEventListener(AddFrame frame) {
            this.frame = frame;
            origin = new Point();
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        //记录鼠标按下时的点
        @Override
        public void mousePressed(MouseEvent e) {
            origin.x = e.getX();
            origin.y = e.getY();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

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
        public void mouseMoved(MouseEvent e) {
        }

    }


}
