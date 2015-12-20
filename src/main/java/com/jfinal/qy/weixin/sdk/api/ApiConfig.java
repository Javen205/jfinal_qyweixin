/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.qy.weixin.sdk.api;

/**
 * 存放 Weixin 服务号需要用到的各个参数
 */
public class ApiConfig {
	
	private String token = null;
	private String corpId = null;
	private String corpSecret=null;
	private String encodingAesKey = null;
	private boolean messageEncrypt = false;	// 消息加密与否
	
	public ApiConfig() {
		
	}
	
	public ApiConfig(String token) {
		setToken(token);
	}
	
	public ApiConfig(String token, String corpId) {
		setToken(token);
		setCorpId(corpId);
	}
	
	public ApiConfig(String token, String corpId, boolean messageEncrypt, String encodingAesKey) {
		setToken(token);
		setCorpId(corpId);
		setEncryptMessage(messageEncrypt);
		setEncodingAesKey(encodingAesKey);
	}
	
	public String getToken() {
		if (token == null)
			throw new IllegalStateException("token 未被赋值");
		return token;
	}
	
	public void setToken(String token) {
		if (token == null)
			throw new IllegalArgumentException("token 值不能为 null");
		this.token = token;
	}
	
	public String getCorpId() {
		if (corpId == null)
			throw new IllegalStateException("corpId 未被赋值");
		return corpId;
	}
	
	public void setCorpId(String corpId) {
		if (corpId == null)
			throw new IllegalArgumentException("corpId 值不能为 null");
		this.corpId = corpId;
	}
	
	
	
	
	public String getCorpSecret() {
		return corpSecret;
	}

	public void setCorpSecret(String secret) {
		if (secret == null)
			throw new IllegalArgumentException("secret 值不能为 null");
		this.corpSecret = secret;
	}

	public String getEncodingAesKey() {
		if (encodingAesKey == null)
			throw new IllegalStateException("encodingAesKey 未被赋值");
		return encodingAesKey;
	}
	
	public void setEncodingAesKey(String encodingAesKey) {
		if (encodingAesKey == null)
			throw new IllegalArgumentException("encodingAesKey 值不能为 null");
		this.encodingAesKey = encodingAesKey;
	}
	
	public boolean isEncryptMessage() {
		return messageEncrypt;
	}
	
	/**
	 *  是否对消息进行加密，对应于微信平台的消息加解密方式：
	 *  1：true进行加密且必须配置 encodingAesKey
	 *  2：false采用明文模式，同时也支持混合模式
	 */
	public void setEncryptMessage(boolean messageEncrypt) {
		this.messageEncrypt = messageEncrypt;
	}
}


