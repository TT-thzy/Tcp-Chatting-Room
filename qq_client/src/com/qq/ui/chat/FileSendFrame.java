package com.qq.ui.chat;

import com.qq.message.ChatStatus;
import com.qq.message.Message;
import com.qq.pojo.MyFile;
import com.qq.pojo.User;
import com.qq.util.IOUtil;
import com.qq.util.PhotoUtil;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class FileSendFrame extends JFrame{

	private static final long serialVersionUID = -569174783640213362L;
	
	public static final Integer FRAME_WIDTH = 450;
	public static final Integer FRAME_HEIGHT = 200;
	

	
    FileSendFrame fileFrame;
    
	public FileSendFrame(Socket socket , User sender , User receiver) {
		
		fileFrame = this;
		
		setTitle("文件发送界面");
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		//窗体不可扩大
		setResizable(false);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//获取屏幕
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;
		//屏幕居中处理
		setLocation((width-FRAME_WIDTH)/2, (height-FRAME_HEIGHT)/2);
		setLayout(null);
		
		//创建一个标签
		JLabel fileLabel = new JLabel("文件路径: ");
		//设置位置、大小
		fileLabel.setBounds(60, 20, 150, 30);
		fileLabel.setFont(new Font("宋体" , 0 , 16));
		//设置标签文本的颜色为白色
		fileLabel.setForeground(Color.BLACK);
		add(fileLabel);
		

		//账号文本框
		JTextField filePathField = new JTextField();
		//设置文本框的位置、大小
		filePathField.setBounds(150, 20, 240, 30);
		add(filePathField);
		
		//创建一个文字按钮
		JButton enter = new JButton("选择文件");
		//设置位置、大小
		enter.setBounds(60, 130, 130, 25);
		enter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				//设置选择文件是，可不可以选择文件夹
				jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				//打开文件选择窗体
				int state = jfc.showDialog(new JLabel(), "选择文件");
				
				if(state == JFileChooser.CANCEL_OPTION) {
					//取消
					return;
				}
				//获取文件
				File file = jfc.getSelectedFile();
				filePathField.setText(file.getAbsolutePath());
			}
		});
		add(enter);
		
		//文件发送按钮
		JButton sendFileBtn = new JButton("发送文件");
		sendFileBtn.setBounds(250, 130, 140, 25);
		sendFileBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String filePath = filePathField.getText();
					System.out.println(filePath);
					if(filePath != null && !("".equals(filePath))) {
						//做文件上传处理
						//把文件从磁盘读取到内存当中
						File file = new File(filePath);
						//把fis弄成byte数组，然后使用byte数组进行文件上传，对内存消耗非常大
						byte[] bytes = PhotoUtil.transform(file);
						Message message = new Message();
						//发送人
						message.setSender(sender);
						//接收人
						message.setReceive(receiver);
						//文件数据
						MyFile myFile = new MyFile();
						myFile.setData(bytes);
						//文件名
						myFile.setName(file.getName());
						message.setFile(myFile);
						//发送文件
						message.setChatStatus(ChatStatus.SINGLEFILE);
						IOUtil.writeMessage(socket,message);
						//隐藏当前窗体
						fileFrame.dispose();
						JOptionPane.showMessageDialog(null, "文件发送成功，等待对方接收！");
					}else {
						JOptionPane.showMessageDialog(null,"文件路径不能为空!");
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}
		});
		add(sendFileBtn);
		
//		//滚动进度条
//		JProgressBar progressBar = new JProgressBar();
//		// 设置进度的 最小值 和 最大值
//        progressBar.setMinimum(MIN_PROGRESS);
//        progressBar.setMaximum(MAX_PROGRESS);
//        progressBar.setBounds(60, 80, 330, 25);
//        // 设置当前进度值
//        progressBar.setValue(currentProgress);
//        // 绘制百分比文本（进度条中间显示的百分数）
//        progressBar.setStringPainted(true);
//        // 添加进度改变通知
//        progressBar.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                System.out.println("当前进度值: " + progressBar.getValue() + "; " +
//                        "进度百分比: " + progressBar.getPercentComplete());
//            }
//        });
//        add(progressBar);
//
//        new Timer(500, new ActionListener() {
//	        @Override
//	        public void actionPerformed(ActionEvent e) {
//	            currentProgress++;
//	            if (currentProgress > MAX_PROGRESS) {
//	                currentProgress = MIN_PROGRESS;
//
//	            }
//	            progressBar.setValue(currentProgress);
//	            progressBar.setString("传输完成");
//	        }
//	    }).start();
		
		setVisible(true);
	}
	

	private static final int MIN_PROGRESS = 0;
    private static final int MAX_PROGRESS = 100;

    private static int currentProgress = MIN_PROGRESS;

}
