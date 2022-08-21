package com.qq.config;

import java.net.InetAddress;

/**
 * @program: testqq
 * @description:
 * @author: Mr.Wang
 * @create: 2021-08-27 17:09
 **/
public class ServerConfig {

    public static final int PORT = 9090;

    public static String IP = null;

    public static String HOST = null;

    static {
        try {
            IP = InetAddress.getLocalHost().getHostAddress();
            HOST = InetAddress.getLocalHost().getHostName();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
