package com.qq.util;

import java.io.*;
import java.net.Socket;

/**
 * @program: testqq
 * @description:
 * @author: Mr.Wang
 * @create: 2021-08-26 22:58
 **/
public class IOUtil {

    //读
    public static Object readMessage(Socket socket) {
        Object o = null;
        try {
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            o = objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return o;
    }

    //写
    public static void writeMessage(Socket socket, Object message) {
        try {
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
