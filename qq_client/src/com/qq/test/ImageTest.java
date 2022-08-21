package com.qq.test;

import com.qq.util.PhotoUtil;
import org.junit.Test;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;

/**
 * @program: qq_client
 * @description:
 * @author: Mr.Wang
 * @create: 2021-09-10 20:46
 **/
public class ImageTest {

    @Test
    public void test() throws Exception{
        byte[] data = PhotoUtil.transform(new File("resources\\image\\uicon\\ls.png"));
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        BufferedImage bi = ImageIO.read(bis);
        ImageIcon imageIcon = new ImageIcon(bi);
    }
}
