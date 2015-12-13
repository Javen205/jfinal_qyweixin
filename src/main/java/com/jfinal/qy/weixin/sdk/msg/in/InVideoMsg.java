
package com.jfinal.qy.weixin.sdk.msg.in;

/**
	接收小视频消息
	<xml>
	   <ToUserName><![CDATA[toUser]]></ToUserName>
	   <FromUserName><![CDATA[fromUser]]></FromUserName>
	   <CreateTime>1357290913</CreateTime>
	   <MsgType><![CDATA[video]]></MsgType>
	   <MediaId><![CDATA[media_id]]></MediaId>
	   <ThumbMediaId><![CDATA[thumb_media_id]]></ThumbMediaId>
	   <MsgId>1234567890123456</MsgId>
	   <AgentID>1</AgentID>
	</xml>
*/
public class InVideoMsg extends InMsg {
	
	private String mediaId;
	private String thumbMediaId;
	private String msgId;
	
	public InVideoMsg(String toUserName, String fromUserName, Integer createTime, String msgType,String agentId) {
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



