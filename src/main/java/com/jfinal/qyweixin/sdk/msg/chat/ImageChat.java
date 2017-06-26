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
public class ImageChat extends ChatBase {
	private ImageChatMsg image;

	public ImageChatMsg getImage() {
		return image;
	}

	public void setImage(ImageChatMsg image) {
		this.image = image;
		this.setMsgtype("image");
	}
	
}
