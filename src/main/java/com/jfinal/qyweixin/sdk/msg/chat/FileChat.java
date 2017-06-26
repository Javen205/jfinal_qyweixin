/**
 * Copyright (c) 2015-2016, Javen  (javenlife@126.com).
 *
 * Licensed under the Apache License, Version 1.0 (the "License");
 */
package com.jfinal.qyweixin.sdk.msg.chat;

/**
 * @author Javen
 * 2016年1月9日
 */
public class FileChat extends ChatBase {
	private FileChatMsg file;

	public FileChatMsg getFile() {
		return file;
	}

	public void setFile(FileChatMsg file) {
		this.file = file;
		this.setMsgtype("file");
	}
	
	
}
