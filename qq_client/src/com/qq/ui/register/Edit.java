package com.qq.ui.register;

import com.qq.message.ChatStatus;
import com.qq.message.Message;
import com.qq.pojo.User;
import com.qq.util.ConnectUtil;
import com.qq.util.PhotoUtil;
import com.qq.util.UUIDUtil;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Edit extends JFrame{
        //设置注册背景图片
//         private JPanel jP=new JPanel();
//         private  JLabel jlb=new JLabel(new ImageIcon("resources\\image\\registerIcon\\edit.png"));
        private JPanel jP=new JPanel(){
                public void paintComponent(Graphics g){
                        super.paintComponent(g);
                        g.drawImage(new ImageIcon("resources\\image\\registerIcon\\edit.png").getImage(), 10,10,this.getWidth(),this.getHeight(),null);

                }
        };
        //设置组件
        //编辑昵称
        private JLabel jlbNickname=new JLabel();
        private JTextField jtf=new JTextField();
        //编辑性别
        private JLabel jlbsex=new JLabel();
        private JComboBox<String> jcbsex=new JComboBox<String>();
        private DefaultComboBoxModel<String> dcbmsex=new DefaultComboBoxModel<String>();
        //编辑所在地
        private  JLabel jlbaddress=new JLabel();
        private JComboBox<String> jcbaddress=new JComboBox<String>();
        private DefaultComboBoxModel<String> dcbmaddress=new DefaultComboBoxModel<String>();
        //编辑个性签名
        private JLabel jlbmotto=new JLabel();
        private JTextArea jta=new JTextArea(20,20);
        private JScrollPane jsp=new JScrollPane();

        //提交按钮
        private JButton jbsubmit =new JButton();

        Register register=null;

        public  Edit(Register register){
                this.register=register;
                init();
        }

        public void init() {
                this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                this.setSize(1000, 600);
                this.setTitle("MyQQ账号信息编辑");
                this.setLocationRelativeTo(null);

                //设置布局(空布局)
                jP.setLayout(null);
                this.setContentPane(jP);

                //设置昵称位置
                jlbNickname.setFont(new Font("等线",Font.BOLD,20));
                jlbNickname.setText("设置昵称：");
                jlbNickname.setBounds(400,50,100,100);
                jP.add(jlbNickname);
                //设置昵称文本框位置
                jtf.setBounds(400,120,250,40);
                jP.add(jtf);
                //设置编辑性别组件位置
                jlbsex.setFont(new Font("等线",Font.BOLD,20));
                jlbsex.setText("性别：");
                jlbsex.setBounds(400,130,100,100);
                jP.add(jlbsex);
                //将性别下拉组件添加模型
                dcbmsex.addElement("男");
                dcbmsex.addElement("女");
                jcbsex.setModel(dcbmsex);
                //设置下拉组件位置
                jcbsex.setBounds(400,200,250,40);
                jP.add(jcbsex);
                //设置编辑所在地组件位置
                jlbaddress.setFont(new Font("等线",Font.BOLD,20));
                jlbaddress.setText("所在地：");
                jlbaddress.setBounds(400,210,100,100);
                jP.add(jlbaddress);
//                将性别下拉组件添加模型
                dcbmaddress.addElement("河北省");
                dcbmaddress.addElement("山西省");
                dcbmaddress.addElement("辽宁省");
                dcbmaddress.addElement("吉林省");
                dcbmaddress.addElement("黑龙江省");
                dcbmaddress.addElement("江苏省");
                dcbmaddress.addElement("浙江省");
                dcbmaddress.addElement("安徽省");
                dcbmaddress.addElement("福建省");
                dcbmaddress.addElement("江西省");
                dcbmaddress.addElement("山东省");
                dcbmaddress.addElement("河南省");
                dcbmaddress.addElement("湖北省");
                dcbmaddress.addElement("湖南省");
                dcbmaddress.addElement("广东省");
                dcbmaddress.addElement("海南省");
                dcbmaddress.addElement("四川省");
                dcbmaddress.addElement("贵州省");
                dcbmaddress.addElement("云南省");
                dcbmaddress.addElement("陕西省");
                dcbmaddress.addElement("甘肃省");
                dcbmaddress.addElement("青海省");
                dcbmaddress.addElement("台湾省");
                dcbmaddress.addElement("青海省");
                dcbmaddress.addElement("内蒙古自治区");
                dcbmaddress.addElement("广西壮族自治区");
                dcbmaddress.addElement("西藏自治区");
                dcbmaddress.addElement("宁夏回族自治区");
                dcbmaddress.addElement("新疆维吾尔自治区");
                dcbmaddress.addElement("北京市");
                dcbmaddress.addElement("天津市");
                dcbmaddress.addElement("上海市");
                dcbmaddress.addElement("重庆市");
                dcbmaddress.addElement("香港特别行政区");
                dcbmaddress.addElement("澳门特别行政区");
                jcbaddress.setModel(dcbmaddress);
                //设置下拉组件位置
                jcbaddress.setBounds(400,280,250,40);
                jP.add(jcbaddress);

                //个性签名组件位置设置

                //将文本域设置为可以换行
                jta.setLineWrap(true);
                //将文本域添加到viewport中
                jsp.getViewport().add(jta);

                jlbmotto.setFont(new Font("等线",Font.BOLD,20));
                jlbmotto.setText("个性签名：");
                jlbmotto.setBounds(400,290,100,100);
                jP.add(jlbmotto);
                //设置文本域位置
                jsp.setBounds(400,360,250,40);
                jP.add(jsp);

                //设置注册按钮样式
                jbsubmit.setContentAreaFilled(true);//无填充
                jbsubmit.setBorderPainted(false);
                jbsubmit.setBackground(Color.BLUE);
                jbsubmit.setForeground(Color.WHITE);
                //设置提交按钮位置
                jbsubmit.setFont(new Font("仿宋体",Font.BOLD,40));
                jbsubmit.setText("提交");
                jbsubmit.setBounds(400,420,250,40);
                jP.add(jbsubmit);

                //将下拉改变为可以填写
                jcbsex.setEditable(true);
                jcbaddress.setEditable(true);

//                //给窗体添加图片
//                this.getContentPane().add(jlb);
                //将内容面板设置成自己的面板
                this.setContentPane(jP);

                this.setVisible(true);

                jtf.addKeyListener(new KeyAdapter() {
                        public void keyTyped(KeyEvent e) {
                                if (register.maxlenth(jtf.getText(), 30)) {
                                        e.consume();
                                }
                        }
                });

                jta.addKeyListener(new KeyAdapter() {
                        public void keyTyped(KeyEvent e) {
                                if (register.maxlenth(jta.getText(), 50)) {
                                        e.consume();
                                }
                        }
                });

                jbsubmit.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                System.out.println("账号"+register.jtfname.getText());
                                System.out.println("密码"+register.jtfpassword.getText());
                                System.out.println("手机密码"+String.valueOf(register.dcbmnumber.getSelectedItem())+register.jtfTelephone.getText());
                                System.out.println("幸运数字"+register.jtfTip.getText());
                                System.out.println("性别"+String.valueOf(jcbsex.getSelectedItem()));
                                System.out.println("所在地"+String.valueOf(jcbaddress.getSelectedItem()));
                                System.out.println("个性签名"+jta.getText());
                                System.out.println("个性签名"+jtf.getText());


                                User user = new User();
                                if (register.isnull(jtf.getText())&&register.isnull(jta.getText())) {
                                        user.setUid(UUIDUtil.getUUID());
                                        user.setUsername(register.jtfname.getText());
                                        user.setPassword(register.jtfpassword.getText());
                                        user.setNickname(jtf.getText());
                                        user.setSignature(jta.getText());
                                        user.setGender(String.valueOf(jcbsex.getSelectedItem()));
                                        user.setBirthday(new Date(new java.util.Date().getTime()));
                                        user.setStatu(0);
                                        user.setPhoto(PhotoUtil.transform(new File("resources\\image\\uicon\\ls.png")));
                                        user.setCreateTime(new Date(new java.util.Date().getTime()));
                                        user.setPhoneNum(String.valueOf(register.dcbmnumber.getSelectedItem())+register.jtfTelephone.getText());
                                        user.setLuckyNum(register.jtfTip.getText());
                                        user.setAddress(String.valueOf(jcbaddress.getSelectedItem()));
                                        user.setTest1(2);
                                        Message message = new Message();
                                        message.setUser(user);
                                        message.setChatStatus(ChatStatus.REGISTER);
                                        ConnectUtil.RegisterConnectServer(message,Edit.this);
                                }else {
                                        JFrame jf = new Warn();
                                        final Timer timer=new Timer();
                                        timer.schedule(new TimerTask() {
                                                public void run() {
                                                        if (jf!=null) {
                                                                jf.dispose();
                                                                timer.cancel();
                                                        }else {
                                                                timer.cancel();//计时器停止
                                                        }


                                                }
                                        }, 1500);
                                }
                        }
                });

        }



        public static void main(String[] args) {
                new Register();
        }
}