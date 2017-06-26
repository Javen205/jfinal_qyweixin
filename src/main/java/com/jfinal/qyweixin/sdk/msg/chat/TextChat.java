/**
 * Copyright (c) 2015-2016, Javen  (javenlife@126.com).
 *
 * Licensed under the Apache License, Version 1.0 (the "License");
 */
package com.jfinal.qyweixin.sdk.msg.chat;

/**
 * @author Javen
 * 2016年1月9日
 * text消息请求
 */
public class TextChat extends ChatBase {
	private TextChatMsg text;
	
	public TextChatMsg getText() {
		return text;
	}

	public void setText(TextChatMsg text) {
		this.text = text;
		this.setMsgtype("text");
	}

	
}
