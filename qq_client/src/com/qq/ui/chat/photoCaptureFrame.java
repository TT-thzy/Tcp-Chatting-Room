package com.qq.ui.chat;

import com.qq.util.DateUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;

/**
 * @program: HomeWork
 * @description: 截图 2
 * @author: Mr.Wang
 * @create: 2021-07-27 20:57
 **/
public class photoCaptureFrame extends JFrame {

    int start_x;
    int start_y;
    int end_x;
    int end_y;

    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

    Rectangle rectangle = null;

    Robot robot = null;

    Graphics g = null;

    JPanel panel = new JPanel(true);

    public photoCaptureFrame(JTextPane textPane) {
        init(textPane);
    }

    private void init(JTextPane textPane) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(dimension);
        this.setUndecorated(true);
//       this.setBackground(Color.gray);
        this.setOpacity(0.1f);
        this.getContentPane().setLayout(null);
        this.setVisible(true);


        this.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                start_x = e.getX();
                start_y = e.getY();
            }

            public void mouseClicked(MouseEvent e) {
                if (e.getButton()== MouseEvent.BUTTON3){
                    System.out.println("退出");
                    photoCaptureFrame.this.getContentPane().removeAll();
                    photoCaptureFrame.this.dispose();
                }
            }

        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                System.out.println(e.getX() + "==" + e.getY());

                end_x = e.getX();
                end_y = e.getY();
//               setNumber();
//                g = getGraphics();
//                g.clearRect(0, 0, dimension.width, dimension.height);
//                g.setColor(Color.BLUE);
//                try {
//                    int width=Math.abs((end_x-start_x));
//                    int height=Math.abs((end_y-start_y));
//                    int min_x=Math.min(start_x,end_x);
//                    int min_y=Math.min(start_y,end_y);
//                    g.fillRect(min_x,min_y,width,height);
//                } catch (Exception exception) {
//                    exception.printStackTrace();
//                }
                int width = Math.abs((end_x - start_x));
                int height = Math.abs((end_y - start_y));
                int min_x = Math.min(start_x, end_x);
                int min_y = Math.min(start_y, end_y);
                rectangle = new Rectangle(min_x, min_y, width, height);
                panel.setBounds(rectangle);
                panel.setBackground(Color.blue);
                photoCaptureFrame.this.getContentPane().add(panel);
                panel.repaint();
            }
        });

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
                    try {
                        System.out.println(start_x+"=="+start_y+"=="+end_x+"=="+end_y);
                        robot = new Robot();
                        setNumber();
                        BufferedImage bi = robot.createScreenCapture(new Rectangle(start_x,start_y,(end_x-start_x),(end_y-start_y)));
                        File file = new File("resources\\image\\captureIcon\\capture.jpg");
                        ImageIO.write(bi,"jpg",file);
                        ImageIcon imageIcon = new ImageIcon(file.getAbsolutePath());
                        textPane.insertIcon(imageIcon);
                        robot=null;
                        photoCaptureFrame.this.dispose();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
        });
    }

    public void setNumber() {
        int min_x = Math.min(start_x, end_x);
        int min_y = Math.min(start_y, end_y);
        int max_x = Math.max(start_x, end_x);
        int max_y = Math.max(start_y, end_y);
        start_x = min_x;
        start_y = min_y;
        end_x = max_x;
        end_y = max_y;
        System.out.println("setNumber" + start_x + "==" + start_y + "==" + end_x + "==" + end_y);
    }

}
