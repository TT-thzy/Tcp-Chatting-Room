package com.qq.ui.datachange;

import com.qq.message.ChatStatus;
import com.qq.message.Message;
import com.qq.pojo.User;
import com.qq.util.DateUtil;
import com.qq.util.IOUtil;
import com.qq.util.PhotoUtil;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.Socket;
import java.sql.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileFilter;


public class Usersdata extends JFrame {

    User user = null;
    Socket socket = null;

    public Usersdata(User user, Socket socket) {
        this.user = user;
        this.socket = socket;
        init();
    }

    //---north面板
    private JPanel jp_north = new JPanel();
    //---center面板
    private JPanel jp_center = new JPanel();
    //---south面板
    private JPanel jp_south = new JPanel();
    private JPanel jp_north2 = new JPanel();
    private JPanel jp_center2 = new JPanel();
    private JPanel jp_south2 = new JPanel();

    private ImageIcon icon_system = new ImageIcon("resources\\image\\loginIcon\\图标.png");
    //头像
    private ImageIcon icon_users = null;
    private JLabel jbl_users = new JLabel();
    //---网袋布局
    private GridBagLayout gbl = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();
    private ImageIcon icon_qq = new ImageIcon("resources\\image\\loginIcon\\账号.png");
    private JLabel jbl_acNumbericon = new JLabel(icon_qq);
    private ImageIcon icon_data = new ImageIcon("resources\\image\\loginIcon\\资料.png");
    private JLabel jbl_dataicon = new JLabel(icon_data);
    //用户名
    public JLabel jbl_usersname = null;
    //账号
    private JLabel jbl_qq = null;
    //
    public JLabel jbl_data = null;
    //个性签名
    public JLabel jbl_signature = null;
    private JButton jb_changedata = new JButton("修改资料");
    public String usersname = null;
    public String signature = null;

    private void init() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(380, 600);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle(user.getNickname() + " 用户资料");

        //填充数据
        icon_users = new ImageIcon(PhotoUtil.byteToPhoto(user.getPhoto()));
        jbl_usersname = new JLabel(user.getNickname());
        jbl_qq = new JLabel(user.getUsername());
//		"男 21岁 5月3日（公历)"
        String gender = user.getGender();
        Date birthday = user.getBirthday();
        String date=DateUtil.dateToString(birthday);
        jbl_data = new JLabel(gender+" "+date+" (公历)");
        jbl_signature = new JLabel(user.getSignature());
        usersname = jbl_usersname.getText();
        signature = jbl_signature.getText();

        this.setIconImage(icon_system.getImage());
        jp_north.setPreferredSize(new Dimension(380, 100));
        jp_north.setBackground(Color.white);
        icon_users.setImage(icon_users.getImage().getScaledInstance(jbl_users.getWidth() + 80, jbl_users.getHeight() + 80, Image.SCALE_DEFAULT));
        jbl_users.setIcon(icon_users);
        jp_north.add(jbl_users);
        this.add(jp_north, BorderLayout.NORTH);

        jp_center.setPreferredSize(new Dimension(380, 100));
        jp_center.setBackground(Color.white);
        Font font = new Font("楷体", Font.BOLD, 30);
        jbl_usersname.setFont(font);
        jbl_usersname.setPreferredSize(new Dimension(300,50));
        jbl_usersname.setHorizontalAlignment(SwingConstants.CENTER);
        jbl_signature.setHorizontalAlignment(SwingConstants.CENTER);
        jp_center.add(jbl_usersname);
        this.add(jp_center, BorderLayout.CENTER);

        jp_south.setPreferredSize(new Dimension(380, 400));
        jp_south.setLayout(new BorderLayout());
        jbl_signature.setPreferredSize(new Dimension(250, 50));
        jp_north2.setPreferredSize(new Dimension(380, 50));
        jp_north2.setBackground(Color.white);
        Font font2 = new Font("宋体", Font.BOLD, 18);
        jbl_signature.setFont(font2);
        jp_north2.add(jbl_signature);
        jp_south.add(jp_north2, BorderLayout.NORTH);


        jp_center2.setPreferredSize(new Dimension(380, 50));
        jp_center2.setBackground(Color.white);
        jp_center2.setLayout(gbl);
        gbc.insets = new Insets(5, 5, 5, 5);

        //---账号图片标签设置
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbl.setConstraints(jbl_acNumbericon, gbc);
        jp_center2.add(jbl_acNumbericon);

        //---账号资料设置
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbl.setConstraints(jbl_qq, gbc);
        jp_center2.add(jbl_qq);

        //---资料图片标签设置
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.REMAINDER;
        gbl.setConstraints(jbl_dataicon, gbc);
        jp_center2.add(jbl_dataicon);

        //---资料设置
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbl.setConstraints(jbl_data, gbc);
        jp_center2.add(jbl_data);
        jp_south.add(jp_center2, BorderLayout.CENTER);


        jp_south2.setPreferredSize(new Dimension(380, 250));
        jp_south2.setBackground(Color.white);
        jb_changedata.setOpaque(true);//透明化
        jb_changedata.setBorderPainted(false);//不绘制边框
        jb_changedata.setFocusPainted(false);//选中后不设置边框
        jb_changedata.setBackground(Color.white);//背景
        jb_changedata.setForeground(Color.gray);//前景（文字）
        jp_south2.add(jb_changedata);
        jp_south.add(jp_south2, BorderLayout.SOUTH);
        this.add(jp_south, BorderLayout.SOUTH);
        this.setVisible(true);
        jb_changedata.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChangedataFrame(Usersdata.this);
                Usersdata.this.dispose();
            }
        });
        jbl_users.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileFilter() {
                    @Override
                    public String getDescription() {
                        return null;
                    }

                    @Override
                    public boolean accept(File f) {

                        return (f.getName().endsWith(".jpg")) || (f.getName().endsWith(".png"));
                    }
                });
                int choose = fileChooser.showOpenDialog(Usersdata.this);
                if (choose == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    if (file != null) {
                        ImageIcon icon = new ImageIcon(file.getAbsolutePath());
                        icon.setImage(icon.getImage().getScaledInstance(jbl_users.getWidth(), jbl_users.getHeight(), Image.SCALE_DEFAULT));
                        jbl_users.setIcon(icon);

                        Message message = new Message();
                        byte[] bytes = PhotoUtil.transform(file);
                        user.setPhoto(bytes);
                        message.setUser(user);
                        message.setChatStatus(ChatStatus.CHANGEHEADERPHOTO);
                        IOUtil.writeMessage(socket,message);
                    }
                }

            }
        });

    }
}
