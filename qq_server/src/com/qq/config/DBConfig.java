package com.qq.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @program: HomeWork
 * @description:
 * @author: Mr.Wang
 * @create: 2021-08-11 20:21
 **/
public class DBConfig {

    private static final Properties PROPERTIES=new Properties();

    static {
        try {
            PROPERTIES.load(new FileInputStream("resources\\jdbc.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getDriverClass(){
        return PROPERTIES.getProperty("jdbc.driverClass");
    }

    public static String getUrl(){
        return PROPERTIES.getProperty("jdbc.url");
    }

    public static String getUsername(){
        return PROPERTIES.getProperty("jdbc.user");
    }

    public static String getPassword(){
        return PROPERTIES.getProperty("jdbc.password");
    }


}
