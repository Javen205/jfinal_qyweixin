/**
 * Copyright (c) 2011-2015, Unas 小强哥 (unas@qq.com).
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.qy.weixin.sdk.msg.in;

/**
	接收视频消息
	<xml>
	   <ToUserName><![CDATA[toUser]]></ToUserName>
	   <FromUserName><![CDATA[fromUser]]></FromUserName>
	   <CreateTime>1357290913</CreateTime>
	   <MsgType><![CDATA[shortvideo]]></MsgType>
	   <MediaId><![CDATA[media_id]]></MediaId>
	   <ThumbMediaId><![CDATA[thumb_media_id]]></ThumbMediaId>
	   <MsgId>1234567890123456</MsgId>
	   <AgentID>1</AgentID>
	</xml>
*/
public class InShortVideoMsg extends InMsg {

	private String mediaId;
	private String thumbMediaId;
	private String msgId;

	public InShortVideoMsg(String toUserName, String fromUserName, Integer createTime, String msgType,String agentId) {
		super(toUserName, fromUserName, createTime, msgType,agentId);
	}
	
	public String getMediaId() {
		return mediaId;
	}
	
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	
	public String getThumbMediaId() {
		return thumbMediaId;
	}
	
	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}
	
	public String getMsgId() {
		return msgId;
	}
	
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
}



