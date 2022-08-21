package com.qq.message;

import java.io.Serializable;

/**
 * 消息类型枚举
 *
 */
public enum ChatStatus implements Serializable {
	
	LOGIN(1,"登录请求"),REGISTER(2,"注册请求"),SINGLECHAT(3,"单聊请求"),NOTICE(4,"系统通知"),
	SINGLEDD(5,"单聊抖动"),FLUSHINFO(6,"通知客户端刷新主界面"),ADDFRIEND(7,"添加好友"),ACCEPTADDFRIEND(8,"同意添加好友"),
	CHECKFORQUESTION(9,"检查问题"),CHANGEPASSWORD(10,"修改密码"),CHANGEDATA(11,"修改信息"),CHANGESTATUS(12,"修改状态"),
	FINDFRIEND(13,"根据账号模糊查询好友"),DELETEFRIEND(14,"删除好友"),SINGLEFILE(15,"单聊发送文件"),QUIT(16,"通知服务端关闭当前管道"),
	isExistUser(17,"是否存在该用户"),CHANGEHEADERPHOTO(18,"修改头像"),FLUSHLIST(19,"刷新好友列表")
	;

	
	private Integer status;
	private String desc;
	
	private ChatStatus(int status , String desc) {
		this.status = status;
		this.desc = desc;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
