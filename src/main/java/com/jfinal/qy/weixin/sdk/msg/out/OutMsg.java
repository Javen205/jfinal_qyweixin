
package com.jfinal.qy.weixin.sdk.msg.out;

import com.jfinal.qy.weixin.sdk.msg.in.InMsg;

/**
	回复文本消息
	
	<xml>
	   <ToUserName><![CDATA[toUser]]></ToUserName>
	   <FromUserName><![CDATA[fromUser]]></FromUserName> 
	   <CreateTime>1348831860</CreateTime>
	   <MsgType><![CDATA[text]]></MsgType>
	   <Content><![CDATA[this is a test]]></Content>
	</xml>
 */
public abstract class OutMsg {
	
	// 接收方帐号（成员UserID）
	protected String toUserName;
	
	// 企业号CorpID
	protected String fromUserName;
	
	// 消息创建时间 （整型）
	protected Integer createTime;
	
	/**
	 * 被动响应消息类型
	 * 1：text 文本消息
	 * 2：image 图片消息
	 * 3：voice 语音消息
	 * 4：video 视频消息
	 * 5：news 图文消息
	 */
	protected String msgType;
	
	/**
	 * 用接收到的消息初始化要发出去的消息，关键在于两者 toUserName 与 fromUserName 相反
	 */
	public OutMsg(InMsg inMsg) {
		this.toUserName = inMsg.getFromUserName();
		this.fromUserName = inMsg.getToUserName();
		this.createTime = now();
	}
	
	public OutMsg() {
		
	}
	
	public Integer now() {
		return (int)(System.currentTimeMillis() / 1000);
	}
	
	public String getToUserName() {
		return toUserName;
	}
	
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	
	public String getFromUserName() {
		return fromUserName;
	}
	
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	
	public Integer getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}
	
	public String getMsgType() {
		return msgType;
	}
	
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
}
