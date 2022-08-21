package com.qq.collection;

import com.qq.ui.main.ListFrame;

import java.util.HashMap;

/**
 * @program: qq_client
 * @description:
 * @author: Mr.Wang
 * @create: 2021-09-12 18:10
 **/
public class ManagerListFrameCollection {

    public static HashMap<String, ListFrame> map=new HashMap<>();

    public static ListFrame get(String s){
//        System.out.println(s+"get");
        ListFrame listFrame = map.get(s);
        return listFrame;
    }

    public static void put(String s,ListFrame listFrame){
//        System.out.println(s+"put");
        map.put(s,listFrame);
    }

    public static void remove(String s){
        map.remove(s);
    }
}
