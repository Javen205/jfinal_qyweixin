/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.qy.weixin.sdk.msg.in.event;

/**
	自定义菜单事件
	1： 点击菜单拉取消息时的事件推送
	<xml>
		<ToUserName><![CDATA[toUser]]></ToUserName>
		<FromUserName><![CDATA[FromUser]]></FromUserName>
		<CreateTime>123456789</CreateTime>
		<MsgType><![CDATA[event]]></MsgType>
		<Event><![CDATA[CLICK]]></Event>
		<EventKey><![CDATA[EVENTKEY]]></EventKey>
		<AgentID>1</AgentID>
	</xml>
	
	2： 点击菜单跳转链接时的事件推送
	<xml>
		<ToUserName><![CDATA[toUser]]></ToUserName>
		<FromUserName><![CDATA[FromUser]]></FromUserName>
		<CreateTime>123456789</CreateTime>
		<MsgType><![CDATA[event]]></MsgType>
		<Event><![CDATA[VIEW]]></Event>
		<EventKey><![CDATA[www.jfinal.com]]></EventKey>
		<AgentID>1</AgentID>
	</xml>
	
	3. scancode_push：扫码推事件
	<xml>
		<ToUserName><![CDATA[toUser]]></ToUserName>
		<FromUserName><![CDATA[FromUser]]></FromUserName>
		<CreateTime>1412075451</CreateTime>
		<MsgType><![CDATA[event]]></MsgType>
		<Event><![CDATA[scancode_push]]></Event>
		<EventKey><![CDATA[rselfmenu_0_1]]></EventKey>
		<ScanCodeInfo>
			<ScanType><![CDATA[qrcode]]></ScanType>
			<ScanResult><![CDATA[http://www.jfinal.com]]></ScanResult>
		</ScanCodeInfo>
		<AgentID>1</AgentID>
	</xml>
	
	4. scancode_waitmsg：扫码推事件且弹出“消息接收中”提示框
	<xml>
		<ToUserName><![CDATA[toUser]]></ToUserName>
		<FromUserName><![CDATA[FromUser]]></FromUserName>
		<CreateTime>1446526359</CreateTime>
		<MsgType><![CDATA[event]]></MsgType>
		<Event><![CDATA[scancode_waitmsg]]></Event>
		<EventKey><![CDATA[2_1]]></EventKey>
		<ScanCodeInfo>
			<ScanType><![CDATA[qrcode]]></ScanType>
			<ScanResult><![CDATA[http://www.jfinal.com]]></ScanResult>
		</ScanCodeInfo>
		<AgentID>1</AgentID>
	</xml>
	
	5. pic_sysphoto：弹出系统拍照发图，这个后台其实收不到该菜单的消息，点击它后，调用的是手机里面的照相机功能，而照相以后再发过来时，就收到的是一个图片消息了
	<xml>
		<ToUserName><![CDATA[toUser]]></ToUserName>
		<FromUserName><![CDATA[FromUser]]></FromUserName>
		<CreateTime>1408090651</CreateTime>
		<MsgType><![CDATA[event]]></MsgType>
		<Event><![CDATA[pic_sysphoto]]></Event>
		<EventKey><![CDATA[6]]></EventKey>
		<SendPicsInfo><Count>1</Count>
		<PicList><item><PicMd5Sum><![CDATA[1b5f7c23b5bf75682a53e7b6d163e185]]></PicMd5Sum>
		</item>
		</PicList>
		</SendPicsInfo>
		<AgentID>1</AgentID>
	</xml>
	
	6. pic_photo_or_album方式，先推送菜单事件，再推送图片消息
	<xml>
		<ToUserName><![CDATA[toUser]]></ToUserName>
		<FromUserName><![CDATA[FromUser]]></FromUserName>
		<CreateTime>1412075614</CreateTime>
		<MsgType><![CDATA[event]]></MsgType>
		<Event><![CDATA[pic_photo_or_album]]></Event>
		<EventKey><![CDATA[rselfmenu_1_1]]></EventKey>
		<SendPicsInfo>
			<Count>1</Count>
			<PicList>
				<item>
					<PicMd5Sum><![CDATA[md5]]></PicMd5Sum>
				</item>
			</PicList>
		</SendPicsInfo>
		<AgentID>1</AgentID>
	</xml>
	
	<xml>
		<ToUserName><![CDATA[toUser]]></ToUserName>
		<FromUserName><![CDATA[FromUser]]></FromUserName>
		<CreateTime>1412075618</CreateTime>
		<MsgType><![CDATA[image]]></MsgType>
		<PicUrl><![CDATA[http://www.jfinal.com]]></PicUrl>
		<MsgId>6064818598989675467</MsgId>
		<MediaId><![CDATA[mediaId]]></MediaId>
	</xml>
	
	7. pic_weixin ,下面是一次推送3张相片时的数据，再推送图片消息
	<xml>
		<ToUserName><![CDATA[toUser]]></ToUserName>
		<FromUserName><![CDATA[FromUser]]></FromUserName>
		<CreateTime>1412075552</CreateTime>
		<MsgType><![CDATA[event]]></MsgType>
		<Event><![CDATA[pic_weixin]]></Event>
		<EventKey><![CDATA[rselfmenu_1_2]]></EventKey>
		<SendPicsInfo>
			<Count>3</Count>
			<PicList>
				<item>
					<PicMd5Sum><![CDATA[md5]]></PicMd5Sum>
				</item>
				<item>
					<PicMd5Sum><![CDATA[md5]]></PicMd5Sum>
				</item>
				<item>
					<PicMd5Sum><![CDATA[md5]]></PicMd5Sum>
				</item>
			</PicList>
		</SendPicsInfo>
		<AgentID>1</AgentID>
	</xml>
	回应上述消息，用户收不到，但微信会继续推送3个图片消息给接口
	<xml>
		<ToUserName><![CDATA[toUser]]></ToUserName>
		<FromUserName><![CDATA[FromUser]]></FromUserName>
		<CreateTime>1412075562</CreateTime>
		<MsgType><![CDATA[image]]></MsgType>
		<PicUrl><![CDATA[http://www.jfinal.com]]></PicUrl>
		<MsgId>6064818358471506877</MsgId>
		<MediaId><![CDATA[mediaId]]></MediaId>
	</xml>
	
	8. location_select：弹出地理位置选择器，菜单的响应用户收不到，在用户发送位置之后，会再推送一个地理位置消息功能给用户
	<xml>
		<ToUserName><![CDATA[toUser]]></ToUserName>
		<FromUserName><![CDATA[FromUser]]></FromUserName>
		<CreateTime>1412075681</CreateTime>
		<MsgType><![CDATA[event]]></MsgType>
		<Event><![CDATA[location_select]]></Event>
		<EventKey><![CDATA[rselfmenu_2_0]]></EventKey>
		<SendLocationInfo>
			<Location_X><![CDATA[22.538145]]></Location_X>
			<Location_Y><![CDATA[113.952298]]></Location_Y>
			<Scale><![CDATA[13]]></Scale>
			<Label><![CDATA[label]]></Label>
			<Poiname><![CDATA[]]></Poiname>
		</SendLocationInfo>
		<AgentID>1</AgentID>
	</xml>

	<xml>
		<ToUserName><![CDATA[toUser]]></ToUserName>
		<FromUserName><![CDATA[FromUser]]></FromUserName>
		<CreateTime>1412075681</CreateTime>
		<MsgType><![CDATA[location]]></MsgType>
		<Location_X>22.538145</Location_X>
		<Location_Y>113.952298</Location_Y>
		<Scale>13</Scale>
		<Label><![CDATA[label]]></Label>
		<MsgId>6064818869572615123</MsgId>
	</xml>
	
 */
public class InMenuEvent extends EventInMsg {
	// 1. 点击菜单拉取消息时的事件推送： CLICK
	public static final String EVENT_INMENU_CLICK = "click";
	// 2. 点击菜单跳转链接时的事件推送： VIEW
	public static final String EVENT_INMENU_VIEW = "view";
	// 3. scancode_push：扫码推事件
	public static final String EVENT_INMENU_SCANCODE_PUSH = "scancode_push";
	// 4. scancode_waitmsg：扫码推事件且弹出“消息接收中”提示框
	public static final String EVENT_INMENU_scancode_waitmsg = "scancode_waitmsg";
	// 5. pic_sysphoto：弹出系统拍照发图
	public static final String EVENT_INMENU_PIC_SYSPHOTO = "pic_sysphoto";
	// 6. pic_photo_or_album：弹出拍照或者相册发图，先推送菜单事件，再推送图片消息
	public static final String EVENT_INMENU_PIC_PHOTO_OR_ALBUM = "pic_photo_or_album";
	// 7. pic_weixin：弹出微信相册发图器
	public static final String EVENT_INMENU_PIC_WEIXIN = "pic_weixin";
	// 8. location_select：弹出地理位置选择器
	public static final String EVENT_INMENU_LOCATION_SELECT = "location_select";

	private String eventKey;
	private ScanCodeInfo scanCodeInfo;
	
	public InMenuEvent(String toUserName, String fromUserName, Integer createTime, String msgType,String event
			,String agentId) {
		super(toUserName, fromUserName, createTime, msgType,event, agentId);
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



