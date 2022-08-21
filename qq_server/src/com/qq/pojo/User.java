package com.qq.pojo;

import java.awt.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;

/**
 * @program: qq_server
 * @description:
 * @author: Mr.Wang
 * @create: 2021-09-07 16:45
 **/
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    String uid;         //唯一标识
    String username;    //账号
    String password;    //密码
    String nickname;    //昵称
    String signature;   //个性签名
    String gender;      //性别
    Date birthday;      //生日
    Integer statu;      //状态
    byte[] photo;       //头像
    Date createTime;    //账户创建时间
    String email;       //邮箱
    String phoneNum;    //电话号码
    String luckyNum;    //幸运数组
    String address;     //所属地
    //预留字段
    int test1;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getStatu() {
        return statu;
    }

    public void setStatu(Integer statu) {
        this.statu = statu;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getLuckyNum() {
        return luckyNum;
    }

    public void setLuckyNum(String luckyNum) {
        this.luckyNum = luckyNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTest1() {
        return test1;
    }

    public void setTest1(int test1) {
        this.test1 = test1;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", signature='" + signature + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", statu=" + statu +
                ", photo=" + Arrays.toString(photo) +
                ", createTime=" + createTime +
                ", email='" + email + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", luckyNum='" + luckyNum + '\'' +
                ", test=" + address +
                ", test1=" + test1 +
                '}';
    }

}
