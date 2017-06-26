/**
 * Copyright (c) 2011-2015, Javen  (javenlife@126.com).
 *
 * Licensed under the Apache License, Version 1.0 (the "License");
 */
package com.jfinal.qyweixin.sdk.msg.in.event;


/**
 * @author Javen
 * 2015年12月12日
 * 
 * <xml>
 		<ToUserName><![CDATA[toUser]]></ToUserName>
		<FromUserName><![CDATA[FromUser]]></FromUserName>
		<CreateTime>1408091189</CreateTime>
		<MsgType><![CDATA[event]]></MsgType>
		<Event><![CDATA[enter_agent]]></Event>
		<EventKey><![CDATA[]]></EventKey>
		<AgentID>1</AgentID>
	</xml>
 * 
 */
public class InEnterAgentEvent extends EventInMsg {
	public static final String EVENT_ENTER_AGENT="enter_agent";
	
	private String eventKey;
	
	public InEnterAgentEvent(String toUserName, String fromUserName, Integer createTime, String msgType, String event,
			String agentId) {
		super(toUserName, fromUserName, createTime, msgType, event, agentId);
	}

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}
	
	
}
