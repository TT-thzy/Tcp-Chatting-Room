package com.qq.message;

import com.qq.pojo.FontStyle;
import com.qq.pojo.MyFile;
import com.qq.pojo.User;

import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.Serializable;
import java.util.List;

/**
 * @program: qq_server
 * @description:
 * @author: Mr.Wang
 * @create: 2021-09-07 10:33
 **/
public class Message implements Serializable {

    private static final long serialVersionUID = 2L;

    //用户信息
    User user;
    //系统消息
    String notice;
    //消息类型
    private ChatStatus chatStatus;
    //聊天信息
    List<FontStyle> content;
    //好友列表
    List<User> userList;
    //发送人
    User sender;
    //接收人
    User receive;
    //文件
    MyFile file;
    //找回密码问题答案
    String luckyNUm;
    //成功登陆与否
    boolean isLoginSuccess;
    //成功注册与否
    boolean isRegisterSuccess;
    //问题回答成功与否
    boolean isAnswerSuccess;
    //修改成功与否
    boolean isChangeSuccess;
    //是否同意添加好友请求
    boolean isAcceptAddRequest;
    //是否存在该用户
    boolean isExistUser;
    //模糊查询的账号
    String id;
    //模糊查询到的好友
    List<User> FindUserList=null;
    //要添加的好友账号
    String friendId;
    //是否已经是好友
    boolean isFriendShip;
    //删除好友成功与否
    boolean isDeleteFriend;

    public boolean isDeleteFriend() {
        return isDeleteFriend;
    }

    public boolean isFriendShip() {
        return isFriendShip;
    }

    public void setFriendShip(boolean friendShip) {
        isFriendShip = friendShip;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public List<User> getFindUserList() {
        return FindUserList;
    }

    public void setFindUserList(List<User> findUserList) {
        FindUserList = findUserList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isExistUser() {
        return isExistUser;
    }

    public void setExistUser(boolean existUser) {
        isExistUser = existUser;
    }

    public boolean isAcceptAddRequest() {
        return isAcceptAddRequest;
    }

    public void setAcceptAddRequest(boolean acceptAddRequest) {
        isAcceptAddRequest = acceptAddRequest;
    }

    public boolean isChangeSuccess() {
        return isChangeSuccess;
    }

    public void setChangeSuccess(boolean changeSuccess) {
        isChangeSuccess = changeSuccess;
    }

    public boolean isAnswerSuccess() {
        return isAnswerSuccess;
    }

    public void setAnswerSuccess(boolean answerSuccess) {
        isAnswerSuccess = answerSuccess;
    }

    public MyFile getFile() {
        return file;
    }

    public void setFile(MyFile file) {
        this.file = file;
    }

    public String getLuckyNUm() {
        return luckyNUm;
    }

    public void setLuckyNUm(String luckyNUm) {
        this.luckyNUm = luckyNUm;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public boolean isLoginSuccess() {
        return isLoginSuccess;
    }

    public void setLoginSuccess(boolean loginSuccess) {
        isLoginSuccess = loginSuccess;
    }

    public boolean isRegisterSuccess() {
        return isRegisterSuccess;
    }

    public void setRegisterSuccess(boolean registerSuccess) {
        isRegisterSuccess = registerSuccess;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceive() {
        return receive;
    }

    public void setReceive(User receive) {
        this.receive = receive;
    }

    public List<FontStyle> getContent() {
        return content;
    }

    public void setContent(List<FontStyle> content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public ChatStatus getChatStatus() {
        return chatStatus;
    }

    public void setChatStatus(ChatStatus chatStatus) {
        this.chatStatus = chatStatus;
    }

}
