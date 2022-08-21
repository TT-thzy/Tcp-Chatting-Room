package com.qq.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * @program: qq_client
 * @description:
 * @author: Mr.Wang
 * @create: 2021-09-10 09:24
 **/
public class PhotoUtil {
    /**
     * 你给我一个文件对象，我就转成字节数组
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
}
