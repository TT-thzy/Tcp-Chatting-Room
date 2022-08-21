package com.qq.pojo;

import java.io.Serializable;

/**
 * @program: qq_server
 * @description:
 * @author: Mr.Wang
 * @create: 2021-09-09 16:03
 **/
public class MyFile implements Serializable {

    private static final long serialVersionUID = 8L;

    //文件名字
    String name;
    //文件数据
    byte[] data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
