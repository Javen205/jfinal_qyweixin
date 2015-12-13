/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.qy.weixin.sdk.msg.in;

/**
	接收文本消息
	<xml>
	   <ToUserName><![CDATA[toUser]]></ToUserName>
	   <FromUserName><![CDATA[fromUser]]></FromUserName> 
	   <CreateTime>1348831860</CreateTime>
	   <MsgType><![CDATA[text]]></MsgType>
	   <Content><![CDATA[this is a test]]></Content>
	   <MsgId>1234567890123456</MsgId>
	   <AgentID>1</AgentID>
	</xml>
 */
public class InTextMsg extends InMsg {
	
	private String content;
	private String msgId;
	
	public InTextMsg(String toUserName, String fromUserName, Integer createTime, String msgType,String agentId) {
		super(toUserName, fromUserName, createTime, msgType,agentId);
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getMsgId() {
		return msgId;
	}
	
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	
}




