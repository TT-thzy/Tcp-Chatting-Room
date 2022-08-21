package com.qq.test;

import com.qq.pojo.User;
import com.qq.ui.chat.ChatFrame;
import org.junit.Test;

import java.net.Socket;

/**
 * @program: qq_client
 * @description:
 * @author: Mr.Wang
 * @create: 2021-09-11 21:53
 **/
public class ChatTest {

    public static void test(){
        new ChatFrame(new User(),new User(),new Socket());
    }

    public static void main(String[] args) {
        test();
    }
}
