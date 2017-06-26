/**
 * Copyright (c) 2015-2016, Javen  (javenlife@126.com).
 *
 * Licensed under the Apache License, Version 1.0 (the "License");
 */
package com.jfinal.qyweixin.sdk.msg.chat;

/**
 * @author Javen
 * 2016年1月9日
 * 企业会话基类
 */
public abstract class ChatBase {
	//接收人
	private ChatReceiver receiver;
	//发送人
	private String sender;
	//消息类型，此时固定为：text
	private String msgtype;
	
	
	public ChatReceiver getReceiver() {
		return receiver;
	}
	public void setReceiver(ChatReceiver receiver) {
		this.receiver = receiver;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	
}
