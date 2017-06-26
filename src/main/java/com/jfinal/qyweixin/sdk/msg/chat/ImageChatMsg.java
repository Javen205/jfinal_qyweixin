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
public class ImageChatMsg {
	//图片media_id，可以调用上传素材文件接口获取
	private String media_id;
	

	public ImageChatMsg(String media_id) {
		this.media_id = media_id;
	}

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}
	
}
