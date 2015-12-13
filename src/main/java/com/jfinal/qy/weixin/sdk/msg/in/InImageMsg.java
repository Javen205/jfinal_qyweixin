
package com.jfinal.qy.weixin.sdk.msg.in;

/**
	接收图片消息
	<xml>
	   <ToUserName><![CDATA[toUser]]></ToUserName>
	   <FromUserName><![CDATA[fromUser]]></FromUserName>
	   <CreateTime>1348831860</CreateTime>
	   <MsgType><![CDATA[image]]></MsgType>
	   <PicUrl><![CDATA[this is a url]]></PicUrl>
	   <MediaId><![CDATA[media_id]]></MediaId>
	   <MsgId>1234567890123456</MsgId>
	   <AgentID>1</AgentID>
	</xml>
*/
public class InImageMsg extends InMsg {
	
	private String picUrl;
	private String mediaId;
	private String msgId;
	
	public InImageMsg(String toUserName, String fromUserName, Integer createTime, String msgType,String agentId) {
		super(toUserName, fromUserName, createTime, msgType,agentId);
	}
	
	public String getPicUrl() {
		return picUrl;
	}
	
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	
	public String getMediaId() {
		return mediaId;
	}
	
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	
	public String getMsgId() {
		return msgId;
	}
	
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
}





