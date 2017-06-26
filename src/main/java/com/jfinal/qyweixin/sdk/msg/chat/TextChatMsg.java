/**
 * Copyright (c) 2015-2016, Javen  (javenlife@126.com).
 *
 * Licensed under the Apache License, Version 1.0 (the "License");
 */
package com.jfinal.qyweixin.sdk.msg.chat;

/**
 * @author Javen
 * 2016年1月9日
 * 
 */
public class TextChatMsg {
	//消息内容
	private String content;

	public TextChatMsg(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
