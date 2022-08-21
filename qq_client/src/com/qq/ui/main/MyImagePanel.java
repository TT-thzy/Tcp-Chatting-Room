package com.qq.ui.main;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
/**
 * 设置面板背景图片
 * */
public class MyImagePanel extends JPanel{
	   private Image image = (Image) new ImageIcon("resources\\image\\mainIcon\\bk.png").getImage();
	   
	    protected void paintComponent(Graphics g) {  
	        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);  
	    }  
}
