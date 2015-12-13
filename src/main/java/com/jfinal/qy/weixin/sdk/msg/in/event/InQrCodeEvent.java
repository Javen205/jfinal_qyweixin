
package com.jfinal.qy.weixin.sdk.msg.in.event;

/**
	扫描带参数二维码事件
	1. 用户未关注时，进行关注后的事件推送
	<xml>
		<ToUserName><![CDATA[toUser]]></ToUserName>
		<FromUserName><![CDATA[FromUser]]></FromUserName>
		<CreateTime>123456789</CreateTime>
		<MsgType><![CDATA[event]]></MsgType>
			<Event><![CDATA[subscribe]]></Event>
			<EventKey><![CDATA[qrscene_123123]]></EventKey>
			<Ticket><![CDATA[TICKET]]></Ticket>
	</xml>
	
	2. 用户已关注时的事件推送
	<xml>
		<ToUserName><![CDATA[toUser]]></ToUserName>
		<FromUserName><![CDATA[FromUser]]></FromUserName>
		<CreateTime>123456789</CreateTime>
		<MsgType><![CDATA[event]]></MsgType>
			<Event><![CDATA[SCAN]]></Event>
			<EventKey><![CDATA[SCENE_VALUE]]></EventKey>
			<Ticket><![CDATA[TICKET]]></Ticket>
	</xml>
	
	
	
	<xml>
		<ToUserName><![CDATA[toUser]]></ToUserName>
		<FromUserName><![CDATA[FromUser]]></FromUserName>
		<CreateTime>1408090502</CreateTime>
		<MsgType><![CDATA[event]]></MsgType>
		<Event><![CDATA[scancode_push]]></Event>
		<EventKey><![CDATA[6]]></EventKey>
		<ScanCodeInfo>
			<ScanType><![CDATA[qrcode]]></ScanType>
			<ScanResult><![CDATA[1]]></ScanResult>
		</ScanCodeInfo>
		<AgentID>1</AgentID>
	</xml>
 */
public class InQrCodeEvent extends EventInMsg {
	private String eventKey;
	private ScanCodeInfo scanCodeInfo;
	
	public InQrCodeEvent(String toUserName, String fromUserName, Integer createTime, String msgType, String event,String agentId) {
		super(toUserName, fromUserName, createTime, msgType, event,agentId);
	}

	public String getEventKey() {
		return eventKey;
	}
	
	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public ScanCodeInfo getScanCodeInfo() {
		return scanCodeInfo;
	}

	public void setScanCodeInfo(ScanCodeInfo scanCodeInfo) {
		this.scanCodeInfo = scanCodeInfo;
	}
	
	
}




