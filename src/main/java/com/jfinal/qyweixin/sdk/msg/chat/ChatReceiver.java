/**
 * Copyright (c) 2015-2016, Javen  (javenlife@126.com).
 *
 * Licensed under the Apache License, Version 1.0 (the "License");
 */
package com.jfinal.qyweixin.sdk.msg.chat;

/**
 * @author Javen
 * 2016年1月9日
 * 企业会话接受人
 */
public class ChatReceiver {
	//接收人类型：single|group，分别表示：群聊|单聊
	private String type;
	//接收人的值，为userid|chatid，分别表示：成员id|会话id
	private String id;
	public String getType() {
		return type;
	}
	public void setType(ChatType type) {
		this.type = type.name();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 接收人类型：single|group，分别表示：群聊|单聊
	 * @author Javen
	 * 2016年1月9日
	 */
	public enum ChatType{
		single,group
	}
}
