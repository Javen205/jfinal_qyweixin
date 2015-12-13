/**
 * Copyright (c) 2011-2015, Javen  (javenlife@126.com).
 *
 * Licensed under the Apache License, Version 1.0 (the "License");
 */
package com.jfinal.qy.weixin.sdk.msg.in.event;

/**
	上报地理位置事件
	<xml>
	   <ToUserName><![CDATA[toUser]]></ToUserName>
	   <FromUserName><![CDATA[FromUser]]></FromUserName>
	   <CreateTime>123456789</CreateTime>
	   <MsgType><![CDATA[event]]></MsgType>
	   <Event><![CDATA[LOCATION]]></Event>
	   <Latitude>23.104105</Latitude>
	   <Longitude>113.320107</Longitude>
	   <Precision>65.000000</Precision>
	   <AgentID>1</AgentID>
	</xml>
 */
public class InLocationEvent extends EventInMsg {
	
	private String latitude;
	private String longitude;
	private String precision;
	
	public InLocationEvent(String toUserName, String fromUserName, Integer createTime, String msgType, String event
			,String agentId) {
		super(toUserName, fromUserName, createTime, msgType, event,agentId);
	}

	public String getLatitude() {
		return latitude;
	}
	
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	public String getLongitude() {
		return longitude;
	}
	
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public String getPrecision() {
		return precision;
	}
	
	public void setPrecision(String precision) {
		this.precision = precision;
	}
}




