package com.qq.ui.main;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.*;
import java.util.List;
import java.awt.Point;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.SampleModel;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MouseInputListener;

import com.qq.collection.ManagerChatFrameCollection;
import com.qq.collection.ManagerListFrameCollection;
import com.qq.message.ChatStatus;
import com.qq.message.Message;
import com.qq.pojo.User;
import com.qq.ui.chat.ChatFrame;
import com.qq.ui.datachange.Usersdata;
import com.qq.util.IOUtil;
import com.qq.util.PhotoUtil;


/**
 * 陈玉琪 主界面
 */
public class ListFrame extends JFrame {
    //窗体
    private JFrame jf = new TopFrame(null);
    private JFrame sc = null;
    //面板
    private JPanel jp_north = new MyImagePanel();
    private JPanel jp_center = new JPanel();
    private JPanel jp_south = new JPanel();
    //系统托盘
    private SystemTray st = SystemTray.getSystemTray();
    //图片
    private ImageIcon img = null;
    private TrayIcon ti = new TrayIcon(Toolkit.getDefaultToolkit().createImage("resources\\image\\mainIcon\\qq.png").getScaledInstance(15, 15, Image.SCALE_SMOOTH));
    private ImageIcon stateimg = new ImageIcon(Toolkit.getDefaultToolkit().createImage("resources\\image\\mainIcon\\dog.jpg").getScaledInstance(100, 30, Image.SCALE_DEFAULT));
    private ImageIcon onlineimg = new ImageIcon(Toolkit.getDefaultToolkit().createImage("resources\\image\\mainIcon\\online.png").getScaledInstance(15, 15, Image.SCALE_DEFAULT));
    private ImageIcon leaveimg = new ImageIcon(Toolkit.getDefaultToolkit().createImage("resources\\image\\mainIcon\\leave.png").getScaledInstance(15, 15, Image.SCALE_DEFAULT));
    private ImageIcon offlineimg = new ImageIcon(Toolkit.getDefaultToolkit().createImage("resources\\image\\mainIcon\\offline.png").getScaledInstance(15, 15, Image.SCALE_DEFAULT));
    private ImageIcon pdndimg = new ImageIcon(Toolkit.getDefaultToolkit().createImage("resources\\image\\mainIcon\\pdnd.png").getScaledInstance(15, 15, Image.SCALE_DEFAULT));

    private ImageIcon potimg = new ImageIcon(Toolkit.getDefaultToolkit().createImage("resources\\image\\mainIcon\\pot.png").getScaledInstance(20, 25, Image.SCALE_DEFAULT));
    private ImageIcon xunzhangimg = new ImageIcon(Toolkit.getDefaultToolkit().createImage("resources\\image\\mainIcon\\xunzhang.png").getScaledInstance(25, 20, Image.SCALE_DEFAULT));
    private ImageIcon areaimg = new ImageIcon(Toolkit.getDefaultToolkit().createImage("resources\\image\\mainIcon\\area.png").getScaledInstance(25, 20, Image.SCALE_DEFAULT));
    private ImageIcon decorateimg = new ImageIcon(Toolkit.getDefaultToolkit().createImage("resources\\image\\mainIcon\\decorate.png").getScaledInstance(25, 20, Image.SCALE_DEFAULT));
    private ImageIcon dakaimg = new ImageIcon(Toolkit.getDefaultToolkit().createImage("resources\\image\\mainIcon\\daka.png").getScaledInstance(25, 20, Image.SCALE_DEFAULT));
    private ImageIcon qqimg = new ImageIcon(Toolkit.getDefaultToolkit().createImage("resources\\image\\mainIcon\\QQtopTitle.png").getScaledInstance(20, 20, Image.SCALE_DEFAULT));
    private ImageIcon weatherimg = new ImageIcon(Toolkit.getDefaultToolkit().createImage("resources\\image\\mainIcon\\weather.png").getScaledInstance(50, 50, Image.SCALE_DEFAULT));
    private ImageIcon searchimg = new ImageIcon(Toolkit.getDefaultToolkit().createImage("resources\\image\\mainIcon\\search.png").getScaledInstance(20, 20, Image.SCALE_DEFAULT));

    private ImageIcon addimg = new ImageIcon(Toolkit.getDefaultToolkit().createImage("resources\\image\\mainIcon\\add.png").getScaledInstance(48, 58, Image.SCALE_DEFAULT));
    private ImageIcon leftimg = new ImageIcon(Toolkit.getDefaultToolkit().createImage("resources\\image\\mainIcon\\left.png").getScaledInstance(57, 58, Image.SCALE_DEFAULT));
    private ImageIcon rightimg = new ImageIcon(Toolkit.getDefaultToolkit().createImage("resources\\image\\mainIcon\\right.png").getScaledInstance(249, 58, Image.SCALE_DEFAULT));


    //标签
    //头像
    public JLabel jlb_img = null;
    //昵称
    public JLabel jlb_name = null;
    //账号
    public JLabel jlb_id = null;
    //状态
    public JLabel jlb_state = null;
    private JLabel jlb_qq = new JLabel(qqimg);
    private JLabel jlb_search = new JLabel(searchimg);
    private JLabel jlb_left = new JLabel(leftimg);
    private JLabel jlb_right = new JLabel(rightimg);
    //列表
    public JList<User> jlist_single = null;
    //滚动面板
    private JScrollPane jsp = null;
    //按钮
    private JButton add = new JButton(addimg);
    private JButton change = new JButton("修改信息");
    private JButton close = new JButton("×");
    private JButton small = new JButton("—");
    private JButton pot = new JButton(potimg);
    private JButton xunzhang = new JButton(xunzhangimg);
    private JButton daka = new JButton(dakaimg);
    private JButton decorate = new JButton(decorateimg);
    private JButton area = new JButton(areaimg);
    //模型
    private DefaultListModel<User> model = new DefaultListModel<User>();
    //选项卡
    private JTabbedPane tabed = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
    //字体
    private Font namefont = new Font("微软雅黑", Font.BOLD, 20);
    private Font tfont = new Font("微软雅黑", Font.PLAIN, 15);
    private Font jbfont = new Font("微软雅黑", Font.PLAIN, 20);
    //上下文菜单
    private JPopupMenu state = new JPopupMenu();
    private JPopupMenu lisetdelete = new JPopupMenu();
    //菜单项
    private JMenuItem state_online = new JMenuItem("我在线上", onlineimg);
    private JMenuItem state_offline = new JMenuItem("离线", offlineimg);
    private JMenuItem state_leave = new JMenuItem("离开", leaveimg);
    private JMenuItem state_pdnd = new JMenuItem("请勿打扰", pdndimg);
    private JMenuItem jlistdelete = new JMenuItem("删除");
    //文本框域
    public JTextArea jlb_ss = null;
    //文本框
    private JTextField search = new JTextField("请输入找查好友的账号");
    public Point p = null;

    //========update==========
    //登录进来的用户
    public User user = null;
    //用户所有好友
    public List<User> userList = null;
    //当前管道
    Socket socket = null;

    public ListFrame(User user, List<User> userList, Socket socket) {
        this.user = user;
        this.userList = userList;
        this.socket = socket;
        init();
    }
    //========update==========

    public void init() {
        this.setSize(385, 730);
        this.setTitle("好友列表");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setUndecorated(true);

        //----------------jp_north----------------

        //jp_north使用网袋布局
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        jp_north.setLayout(gbl);

        //jp_north面板的一些调整
        jp_north.setForeground(Color.WHITE);
        jp_north.setPreferredSize(new Dimension(385, 165));//大小
        this.getContentPane().add(jp_north, BorderLayout.NORTH);

        //上下文菜单添加菜单项
        state.add(state_online);
        state.add(state_leave);
        state.add(state_pdnd);
        state.add(state_offline);

        //设置上下文菜单样式
        state.setBorder(BorderFactory.createEmptyBorder());//无边框
        state_online.setBackground(Color.white);//背景色
        state_leave.setBackground(Color.white);
        state_pdnd.setBackground(Color.white);
        state_offline.setBackground(Color.white);
        state_online.setFont(tfont);//字体
        state_leave.setFont(tfont);
        state_pdnd.setFont(tfont);
        state_offline.setFont(tfont);
        state_online.setPreferredSize(new Dimension(120, 40));//大小
        state_leave.setPreferredSize(new Dimension(120, 40));
        state_pdnd.setPreferredSize(new Dimension(120, 40));
        state_offline.setPreferredSize(new Dimension(120, 40));

        //获得当前窗体坐标
        p = this.getLocation();

        //jp_north添加组件

        //添加面板最上方组件
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(0, 20, 5, 20);
        jlb_qq.setPreferredSize(new Dimension(15, 25));
        gbl.setConstraints(jlb_qq, gbc);
        jp_north.add(jlb_qq);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(0, 0, 10, 10);
        area.setPreferredSize(new Dimension(17, 25));
        area.setContentAreaFilled(false);
        area.setBorder(BorderFactory.createEmptyBorder());
        gbl.setConstraints(area, gbc);
        jp_north.add(area);

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(0, 0, 10, 20);
        decorate.setPreferredSize(new Dimension(20, 25));
        decorate.setContentAreaFilled(false);
        decorate.setBorder(BorderFactory.createEmptyBorder());
        gbl.setConstraints(decorate, gbc);
        jp_north.add(decorate);

        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(0, 0, 10, 5);
        daka.setPreferredSize(new Dimension(20, 25));
        daka.setContentAreaFilled(false);
        daka.setBorder(BorderFactory.createEmptyBorder());
        gbl.setConstraints(daka, gbc);
        jp_north.add(daka);

        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(0, 0, 10, 20);
        xunzhang.setPreferredSize(new Dimension(25, 25));
        xunzhang.setContentAreaFilled(false);
        xunzhang.setBorder(BorderFactory.createEmptyBorder());
        gbl.setConstraints(xunzhang, gbc);
        jp_north.add(xunzhang);

        gbc.gridx = 6;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        pot.setPreferredSize(new Dimension(15, 25));
        pot.setContentAreaFilled(false);
        pot.setBorder(BorderFactory.createEmptyBorder());
        gbl.setConstraints(pot, gbc);
        jp_north.add(pot);

        gbc.gridx = 7;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        small.setPreferredSize(new Dimension(15, 25));
        small.setContentAreaFilled(false);
        small.setBorder(BorderFactory.createEmptyBorder());
        small.setForeground(Color.white);
        gbl.setConstraints(small, gbc);
        jp_north.add(small);

        gbc.gridx = 8;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        close.setPreferredSize(new Dimension(15, 25));
        close.setContentAreaFilled(false);
        close.setBorder(BorderFactory.createEmptyBorder());
        close.setForeground(Color.white);
        close.setFont(jbfont);
        gbl.setConstraints(close, gbc);
        jp_north.add(close);


        //添加头像
        img = new ImageIcon(PhotoUtil.byteToPhoto(user.getPhoto()).getScaledInstance(70, 70, Image.SCALE_DEFAULT));
        jlb_img = new JLabel(img);
        gbc.insets = new Insets(20, 20, 10, 20);
//		new Insets(top, left, bottom, right)
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 3;
        jlb_img.setPreferredSize(new Dimension(70, 70));
        gbl.setConstraints(jlb_img, gbc);
        jp_north.add(jlb_img);


        //添加姓名标签
        jlb_name = new JLabel(user.getNickname());
        jlb_name.setFont(namefont);
        jlb_name.setPreferredSize(new Dimension(100, 20));
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(10, 0, 0, 0);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        jlb_name.setForeground(Color.white);
        gbl.setConstraints(jlb_name, gbc);
        jp_north.add(jlb_name);

        //添加账号标签
        jlb_id = new JLabel(user.getUsername());
        jlb_id.setFont(tfont);
        jlb_id.setPreferredSize(new Dimension(100, 20));
        jlb_id.setForeground(Color.white);
        gbc.gridx = 5;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbl.setConstraints(jlb_id, gbc);
        jp_north.add(jlb_id);

        //添加个性签名标签
        jlb_ss = new JTextArea(user.getSignature());
        jlb_ss.setFont(tfont);
        jlb_ss.setPreferredSize(new Dimension(134, 30));
        jlb_ss.setForeground(Color.white);
        jlb_ss.setOpaque(false);
        jlb_ss.setSelectedTextColor(Color.white);
        jlb_ss.setSelectionColor(new Color(100, 149, 237));
//		gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        gbc.gridheight = 1;
        gbl.setConstraints(jlb_ss, gbc);
        jp_north.add(jlb_ss);
        jlb_ss.setEditable(false);

        //添加状态标签
        jlb_state = new JLabel(onlineimg);
        jlb_state.setFont(tfont);
        jlb_state.setPreferredSize(new Dimension(60, 20));
        jlb_state.setForeground(Color.white);
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbl.setConstraints(jlb_state, gbc);
        jp_north.add(jlb_state);

        jlb_search.setPreferredSize(new Dimension(30, 30));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbl.setConstraints(jlb_search, gbc);
        jp_north.add(jlb_search);

        search.setBorder(BorderFactory.createEmptyBorder());
        search.setPreferredSize(new Dimension(275, 30));
        search.setForeground(new Color(216, 191, 216));
        search.setBackground(Color.white);
        search.setOpaque(false);
        search.setSelectedTextColor(Color.white);
        search.setSelectionColor(new Color(100, 149, 237));
        search.setFont(tfont);
        search.setEditable(false);
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 7;
        gbc.gridheight = 1;
        gbl.setConstraints(search, gbc);
        jp_north.add(search);

        //========update==========
        //头像点击事件
        jlb_img.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("头像点击事件");
                new Usersdata(user, socket);
            }
        });
        //========update==========

        //添加状态点击事件
        jlb_state.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                state.show(jp_north, jlb_state.getX() + 30, jlb_state.getY() + 20);
            }
        });

        //状态选择我在线上
        state_online.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jlb_state.setIcon(onlineimg);
            }
        });
        //状态选择离开
        state_leave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jlb_state.setIcon(leaveimg);
            }
        });
        //状态选择请勿打扰
        state_pdnd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jlb_state.setIcon(pdndimg);
            }
        });
        //状态选择离线
        state_offline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jlb_state.setIcon(offlineimg);
            }
        });

        //点击“×”关闭本窗体
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
                    ListFrame.this.dispose();
                }


            }
        });

        //给jlb_ss添加鼠标事件
        jlb_ss.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {//鼠标离开对个性签名进行保存
                jlb_ss.setEditable(false);
                jlb_ss.setOpaque(false);
                jlb_ss.setForeground(Color.white);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {//双击个性签名对其进行编辑
                    jlb_ss.setEditable(true);
                    jlb_ss.setOpaque(true);
                    jlb_ss.setForeground(Color.black);
                }

            }
        });

        //给搜索框添加键盘事件
        search.registerKeyboardAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("search");
                System.out.println(search.getText());
                if (search.getText() == null || search.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "输入内容不能为空");
                }else {
                    Message message = new Message();
                    message.setUser(user);
                    message.setChatStatus(ChatStatus.FINDFRIEND);
                    message.setId(search.getText());
                    IOUtil.writeMessage(socket, message);
                }
//				sc=new SearchFrame(new ArrayList<User>());//##################添加对象
//				sc.setLocation(p.x,p.y+165);
            }
        }, KeyStroke.getKeyStroke('\n', 0), JComponent.WHEN_IN_FOCUSED_WINDOW);//按回车键进行搜索


        //给搜索框添加鼠标事件
        search.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
                search.setEditable(false);
                search.setOpaque(false);
                search.setForeground(new Color(216, 191, 216));

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON1) {
                    search.setText("");
                    search.setForeground(Color.black);
                    search.setEditable(true);
                    search.setOpaque(true);
                    if (sc != null) {
                        sc.dispose();
                    }

                }
            }
        });


        //最小化
        //给small按钮添加鼠标事件
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
                    try {
                        st.add(ti);
                        ListFrame.this.setVisible(false);
                        ListFrame.this.setState(JFrame.NORMAL);
                    } catch (Exception e2) {
                    }
                }
            }
        });

        //给图标ti添加鼠标点击事件
        ti.addMouseListener(new MouseListener() {//

            @Override
            public void mouseReleased(MouseEvent e) {
                //鼠标释放 发生在mousePressed和mouseDragged之后
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // 鼠标按下不松无论是否在原地
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // 鼠标离开组件
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // 鼠标进入组件
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                //鼠标在原地按下松开
                if (e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON1) {
                    st.remove(ti);
                    ListFrame.this.setVisible(true);
                    ListFrame.this.setState(JFrame.NORMAL);
                }
            }
        });


        //----------------jp_center----------------

        jp_center.setSize(new Dimension(385, 650));
        jp_center.setBackground(Color.white);

        // //测试数据
        // User user = new User();
        // model.addElement(user);

        // User user2 = new User();
        // model.addElement(user2);


        //jlist的一些调整
        //========update==========
        jlist_single = new JList<User>();
        if (userList != null) {
            jlist_single.setListData(userList.toArray(new User[userList.size()]));
        }
        //========update==========
        jlist_single.setLayoutOrientation(jlist_single.VERTICAL);
        jlist_single.setFixedCellHeight(50);//行高
        jlist_single.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        //渲染
        jlist_single.setCellRenderer(new MyQQCellPanelRender());

        //将jlist添加到滚动面板jsp中
        jsp = new JScrollPane(jlist_single, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //添加选项卡
        tabed.addTab("好友 ", null, jsp, "好友");
        tabed.addTab("群聊 ", null, new JPanel(), "群聊");
        tabed.setUI(new MyTabed());
        tabed.setBackground(Color.white);
        tabed.setFont(tfont);
        this.getContentPane().add(tabed, BorderLayout.CENTER);

        jlist_single.setBorder(BorderFactory.createEmptyBorder());
        jsp.setBorder(new MatteBorder(1, 0, 0, 0, new Color(193, 193, 193)));
//		new MatteBorder(top, left, bottom, right, matteColor)
        //上下文菜单添加菜单项
        lisetdelete.add(jlistdelete);
        jlistdelete.setFont(tfont);

        //========update==========
        //列表点击事件
        jlist_single.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
                    User receiver = jlist_single.getSelectedValue();
                    ChatFrame chatFrame = new ChatFrame(user, receiver, socket);
                    ManagerChatFrameCollection.put(user.getUsername() + "" + receiver.getUsername(), chatFrame);
                }
            }
        });
        //========update==========


        jlist_single.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                jf.setVisible(false);
                jf.dispose();
                User user = jlist_single.getSelectedValue();
                jf = new TopFrame(user);
                System.out.println(1);

            }
        });

        //list添加鼠标事件
        jlist_single.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 ){
                    jf.setLocation(p.x - 367, e.getYOnScreen());
                    jf.setVisible(true);
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub
                System.out.println("鼠标按下");

            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("鼠标退出");
                jf.dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("鼠标进入list");
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("list被点击");
                if (e.getButton() == MouseEvent.BUTTON3 && e.getClickCount()==1) {
                    System.out.println("lisetdelete");
                    lisetdelete.show(jlist_single, e.getX()+7,e.getY()+15);
                }
            }
        });

        jlistdelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user2=jlist_single.getSelectedValue();
                if (user2!=null){
                    if (user2.getStatu()==1){
                        Message message = new Message();
                        message.setSender(user);
                        message.setFriendId(user2.getUsername());
                        message.setChatStatus(ChatStatus.DELETEFRIEND);
                        IOUtil.writeMessage(socket,message);
                    }else{
                        JOptionPane.showMessageDialog(null,"对方不在线无法删除");

                    }
                }else{
                    JOptionPane.showMessageDialog(null,"请选择需要删除的对象" +
                            "" +
                            "" +
                            "" +
                            "");

                }


            }
        });


        //----------------jp_south---------------

        GridBagLayout gbl1 = new GridBagLayout();
        GridBagConstraints gbc1 = new GridBagConstraints();
        jp_south.setLayout(gbl1);
        jp_south.setBackground(Color.white);
        jp_south.setPreferredSize(new Dimension(385, 58));

        jlb_left.setPreferredSize(new Dimension(57, 58));
        gbc1.gridx = 1;
        gbc1.gridy = 1;
        gbl1.setConstraints(jlb_left, gbc1);
        jp_south.add(jlb_left);

        add.setPreferredSize(new Dimension(48, 58));
        add.setBorder(BorderFactory.createEmptyBorder());
        gbc1.gridx = 2;
        gbc1.gridy = 1;
        gbl1.setConstraints(add, gbc1);
        jp_south.add(add);

        jlb_right.setPreferredSize(new Dimension(249, 58));
        gbc1.gridx = 3;
        gbc1.gridy = 1;
        gbl1.setConstraints(jlb_right, gbc1);
        jp_south.add(jlb_right);


        this.getContentPane().add(jp_south, BorderLayout.SOUTH);

        //添加按钮添加事件
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddFrame(user, socket);//####################添加对象

            }
        });

        //添加窗体拖拽事件
        MouseEventListener mouseListener = new MouseEventListener(this);
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);


        this.setVisible(true);

        ListFrame.this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                ListFrame listFrame = ManagerListFrameCollection.get(user.getUsername());
                ManagerListFrameCollection.remove(user.getUsername());
                listFrame.dispose();
                Message message = new Message();
                message.setUser(user);
                message.setChatStatus(ChatStatus.QUIT);
                IOUtil.writeMessage(socket,message);
            }
        });

//        ListFrame.this.addMouseMotionListener(new MouseMotionAdapter() {
//            @Override
//            public void mouseDragged(MouseEvent e) {
//                p=new Point(e.getX(),e.getY());
//            }
//        });
    }

    class MouseEventListener implements MouseInputListener {

        Point origin;
        //鼠标拖拽想要移动的目标组件
        ListFrame frame;

        public MouseEventListener(ListFrame frame) {
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
            p = this.frame.getLocation();
            this.frame.setLocation(
                    p.x + (e.getX() - origin.x),
                    p.y + (e.getY() - origin.y));
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }
    }


}
