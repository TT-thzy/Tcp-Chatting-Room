package com.qq.collection;

import com.qq.ui.chat.ChatFrame;

import java.util.HashMap;

/**
 * @program: qq_client
 * @description:存储对应的聊天窗口
 * @author: Mr.Wang
 * @create: 2021-09-11 12:11
 **/
public class ManagerChatFrameCollection {

    public static HashMap<String, ChatFrame> map=new HashMap<>();

    public static ChatFrame get(String s){
        ChatFrame chatFrame = map.get(s);
        return chatFrame;
    }

    public static void put(String s,ChatFrame chatFrame){
        map.put(s,chatFrame);
    }

    public static void remove(String s){
         map.remove(s);
    }
}
