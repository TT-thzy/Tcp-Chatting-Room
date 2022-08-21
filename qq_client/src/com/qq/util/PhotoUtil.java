package com.qq.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @program: qq_client
 * @description:
 * @author: Mr.Wang
 * @create: 2021-09-10 09:24
 **/
public class PhotoUtil {
    /**
     * 文件对象转字节数组
     * @param file
     * @return
     */
    public static byte[] transform(File file){
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int len = -1;
            byte[] b = new byte[1024];
            while( (len = fis.read(b)) !=-1){
                baos.write(b, 0, len);
                baos.flush();
            }
            fis.close();
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //字节数组转图片
    public static Image byteToPhoto(byte[] data){
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(bis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bi;
    }
}
