/**
 * Copyright (c) 2011-2015, Javen  (javenlife@126.com).
 *
 * Licensed under the Apache License, Version 1.0 (the "License");
 */
package com.jfinal.qy.weixin.sdk.msg.send;

/**
 * @author Javen
 * 2015年12月12日
 * 
 * {
	   "touser": "UserID1|UserID2|UserID3",
	   "toparty": " PartyID1 | PartyID2 ",
	   "totag": " TagID1 | TagID2 ",
	   "msgtype": "text",
	   "agentid": 1,
	   "text": {
	       "content": "Holiday Request For Pony(http://xxxxx)"
	   },
	   "safe":"0"
	}
 * 
 */
public abstract class SendMsg {
	/**
	 * 成员ID列表（消息接收者，多个接收者用‘|’分隔，最多支持1000个）。
	 * 特殊情况：指定为@all，则向关注该企业应用的全部成员发送
	 */
	protected String toUser;
	/**
	 * 部门ID列表，多个接收者用‘|’分隔，最多支持100个。当touser为@all时忽略本参数
	 */
	protected String toParty;
	/**
	 * 标签ID列表，多个接收者用‘|’分隔。当touser为@all时忽略本参数
	 */
	protected String toTag;
	/**
	 *  发送消息类型
	 *  1 text消息
		2 image消息
		3 voice消息
		4 video消息
		5 file消息
		6 news消息
		7 mpnews消息
	 */
	protected String msgType;
	/**
	 * 表示是否是保密消息，0表示否，1表示是，默认0
	 */
	protected String safe;
	
	
}
