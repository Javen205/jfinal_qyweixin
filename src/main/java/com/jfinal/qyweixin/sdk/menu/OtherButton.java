/**
 * Copyright (c) 2011-2015, Javen  (javenlife@126.com).
 *
 * Licensed under the Apache License, Version 1.0 (the "License");
 */
package com.jfinal.qyweixin.sdk.menu;

/**
 * @author Javen
 * 2015年12月19日
 *  扫码  发图  发送位置
 *  
 *  实测sub_button可以不设置
 */
public class OtherButton extends Button{
	private String key;
	private Button[] sub_button;
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Button[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(Button[] sub_button) {
		this.sub_button = sub_button;
	}
	
	
}
