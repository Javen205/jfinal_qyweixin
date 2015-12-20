
package com.jfinal.qy.weixin.sdk.msg;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.jfinal.kit.StrKit;
import com.jfinal.qy.weixin.sdk.msg.in.InImageMsg;
import com.jfinal.qy.weixin.sdk.msg.in.InLocationMsg;
import com.jfinal.qy.weixin.sdk.msg.in.InMsg;
import com.jfinal.qy.weixin.sdk.msg.in.InShortVideoMsg;
import com.jfinal.qy.weixin.sdk.msg.in.InTextMsg;
import com.jfinal.qy.weixin.sdk.msg.in.InVideoMsg;
import com.jfinal.qy.weixin.sdk.msg.in.InVoiceMsg;
import com.jfinal.qy.weixin.sdk.msg.in.event.BatchJob;
import com.jfinal.qy.weixin.sdk.msg.in.event.InEnterAgentEvent;
import com.jfinal.qy.weixin.sdk.msg.in.event.InFollowEvent;
import com.jfinal.qy.weixin.sdk.msg.in.event.InJobEvent;
import com.jfinal.qy.weixin.sdk.msg.in.event.InLocationEvent;
import com.jfinal.qy.weixin.sdk.msg.in.event.InMenuEvent;
import com.jfinal.qy.weixin.sdk.msg.in.event.InQrCodeEvent;
import com.jfinal.qy.weixin.sdk.msg.in.event.ScanCodeInfo;

public class InMsgParser {
	
	private InMsgParser() {}
	
	/**
	 * 从 xml 中解析出各类消息与事件
	 */
	public static InMsg parse(String xml) {
		try {
			return doParse(xml);
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 消息类型
	 * 1：text 文本消息
	 * 2：image 图片消息
	 * 3：voice 语音消息
	 * 4：video 视频消息
	 *	shortvideo 小视频消息
	 * 5：location 地址位置消息
	 * 6：link 链接消息
	 * 7：event 事件
	 */
	private static InMsg doParse(String xml) throws DocumentException {
		Document doc = DocumentHelper.parseText(xml);
		Element root = doc.getRootElement();
		String toUserName = root.elementText("ToUserName");
		String fromUserName = root.elementText("FromUserName");
		Integer createTime = Integer.parseInt(root.elementText("CreateTime"));
		String msgType = root.elementText("MsgType");
		String agentId = root.elementText("AgentID");
		if ("text".equals(msgType))
			return parseInTextMsg(root, toUserName, fromUserName, createTime, msgType,agentId);
		if ("image".equals(msgType))
			return parseInImageMsg(root, toUserName, fromUserName, createTime, msgType,agentId);
		if ("voice".equals(msgType))
			return parseInVoiceMsgAndInSpeechRecognitionResults(root, toUserName, fromUserName, createTime, msgType,agentId);
		if ("video".equals(msgType))
			return parseInVideoMsg(root, toUserName, fromUserName, createTime, msgType,agentId);
		if ("shortvideo".equals(msgType))	 //支持小视频
			return parseInShortVideoMsg(root, toUserName, fromUserName, createTime, msgType,agentId);
		if ("location".equals(msgType))
			return parseInLocationMsg(root, toUserName, fromUserName, createTime, msgType,agentId);
		if ("event".equals(msgType))
			return parseInEvent(root, toUserName, fromUserName, createTime, msgType,agentId);
		throw new RuntimeException("无法识别的消息类型 " + msgType + "，请查阅微信公众平台开发文档");
	}
	
	private static InMsg parseInTextMsg(Element root, String toUserName, String fromUserName, Integer createTime, String msgType,String agentId) {
		InTextMsg msg = new InTextMsg(toUserName, fromUserName, createTime, msgType,agentId);
		msg.setContent(root.elementText("Content"));
		msg.setMsgId(root.elementText("MsgId"));
		return msg;
	}
	
	private static InMsg parseInImageMsg(Element root, String toUserName, String fromUserName, Integer createTime, String msgType,String agentId) {
		InImageMsg msg = new InImageMsg(toUserName, fromUserName, createTime, msgType,agentId);
		msg.setPicUrl(root.elementText("PicUrl"));
		msg.setMediaId(root.elementText("MediaId"));
		msg.setMsgId(root.elementText("MsgId"));
		return msg;
	}
	
	private static InMsg parseInVoiceMsgAndInSpeechRecognitionResults(Element root, String toUserName, String fromUserName, Integer createTime, String msgType,String agentId) {
		String recognition = root.elementText("Recognition");
		if (StrKit.isBlank(recognition)) {
			InVoiceMsg msg = new InVoiceMsg(toUserName, fromUserName, createTime, msgType,agentId);
			msg.setMediaId(root.elementText("MediaId"));
			msg.setFormat(root.elementText("Format"));
			msg.setMsgId(root.elementText("MsgId"));
			return msg;
		}
		return null;
	}
	
	private static InMsg parseInVideoMsg(Element root, String toUserName, String fromUserName, Integer createTime, String msgType,String agentId) {
		InVideoMsg msg = new InVideoMsg(toUserName, fromUserName, createTime, msgType,agentId);
		msg.setMediaId(root.elementText("MediaId"));
		msg.setThumbMediaId(root.elementText("ThumbMediaId"));
		msg.setMsgId(root.elementText("MsgId"));
		return msg;
	}

	private static InMsg parseInShortVideoMsg(Element root, String toUserName, String fromUserName, Integer createTime, String msgType,String agentId) {
		InShortVideoMsg msg = new InShortVideoMsg(toUserName, fromUserName, createTime, msgType,agentId);
		msg.setMediaId(root.elementText("MediaId"));
		msg.setThumbMediaId(root.elementText("ThumbMediaId"));
		msg.setMsgId(root.elementText("MsgId"));
		return msg;
	}

	private static InMsg parseInLocationMsg(Element root, String toUserName, String fromUserName, Integer createTime, String msgType,String agentId) {
		InLocationMsg msg = new InLocationMsg(toUserName, fromUserName, createTime, msgType,agentId);
		msg.setLocation_X(root.elementText("Location_X"));
		msg.setLocation_Y(root.elementText("Location_Y"));
		msg.setScale(root.elementText("Scale"));
		msg.setLabel(root.elementText("Label"));
		msg.setMsgId(root.elementText("MsgId"));
		return msg;
	}
	
	// 解析各种事件
	private static InMsg parseInEvent(Element root, String toUserName, String fromUserName, Integer createTime, String msgType ,String agentId) {
		String event = root.elementText("Event");
		String eventKey = root.elementText("EventKey");
		
		// 关注/取消关注事件（包括二维码扫描关注，二维码扫描关注事件与扫描带参数二维码事件是两回事）
		if (("subscribe".equalsIgnoreCase(event) || "unsubscribe".equals(event)) && StrKit.isBlank(eventKey)) {
			return new InFollowEvent(toUserName, fromUserName, createTime, msgType, event,agentId);
		}
		// 扫描带参数二维码事件之一		1: 用户未关注时，进行关注后的事件推送
		if ("subscribe".equalsIgnoreCase(event) && StrKit.notBlank(eventKey) && eventKey.startsWith("qrscene_")) {
			InQrCodeEvent e = new InQrCodeEvent(toUserName, fromUserName, createTime, msgType, event,agentId);
			e.setEventKey(eventKey);
			return e;
		}
		// 上报地理位置事件
		if ("location".equalsIgnoreCase(event)) {
			InLocationEvent e = new InLocationEvent(toUserName, fromUserName, createTime, msgType, event,agentId);
			e.setLatitude(root.elementText("Latitude"));
			e.setLongitude(root.elementText("Longitude"));
			e.setPrecision(root.elementText("Precision"));
			return e;
		}
		// 自定义菜单事件之一			1：点击菜单拉取消息时的事件推送
		if ("click".equalsIgnoreCase(event)) {
			InMenuEvent e = new InMenuEvent(toUserName, fromUserName, createTime, msgType, event,agentId);
			e.setEventKey(eventKey);
			return e;
		}
		// 自定义菜单事件之二			2：点击菜单跳转链接时的事件推送
		if ("view".equalsIgnoreCase(event)) {
			InMenuEvent e = new InMenuEvent(toUserName, fromUserName, createTime, msgType, event,agentId);
			e.setEventKey(eventKey);
			return e;
		}
		// 扫码推事件
		if ("scancode_push".equalsIgnoreCase(event)) {
			InMenuEvent e = new InMenuEvent(toUserName, fromUserName, createTime, msgType, event,agentId);
			e.setEventKey(eventKey);
			Element scanCodeInfo = root.element("ScanCodeInfo");
			String scanType = scanCodeInfo.elementText("ScanType");
			String scanResult = scanCodeInfo.elementText("ScanResult");
			e.setScanCodeInfo(new ScanCodeInfo(scanType, scanResult));
			return e;
		}
		// 扫码推事件且弹出“消息接收中”提示框
		if ("scancode_waitmsg".equalsIgnoreCase(event)) {
			InMenuEvent e = new InMenuEvent(toUserName, fromUserName, createTime, msgType, event,agentId);
			e.setEventKey(eventKey);
			Element scanCodeInfo = root.element("ScanCodeInfo");
			String scanType = scanCodeInfo.elementText("ScanType");
			String scanResult = scanCodeInfo.elementText("ScanResult");
			e.setScanCodeInfo(new ScanCodeInfo(scanType, scanResult));
			return e;
		}
		// 5. pic_sysphoto：弹出系统拍照发图，这个后台其实收不到该菜单的消息，点击它后，调用的是手机里面的照相机功能，而照相以后再发过来时，就收到的是一个图片消息了
		if ("pic_sysphoto".equalsIgnoreCase(event)) {
			InMenuEvent e = new InMenuEvent(toUserName, fromUserName, createTime, msgType, event,agentId);
			e.setEventKey(eventKey);
			return e;
		}
		// pic_photo_or_album：弹出拍照或者相册发图
		if ("pic_photo_or_album".equalsIgnoreCase(event)) {
			InMenuEvent e = new InMenuEvent(toUserName, fromUserName, createTime, msgType, event,agentId);
			e.setEventKey(eventKey);
			return e;
		}
		// pic_weixin：弹出微信相册发图器
		if ("pic_weixin".equalsIgnoreCase(event)) {
			InMenuEvent e = new InMenuEvent(toUserName, fromUserName, createTime, msgType, event,agentId);
			e.setEventKey(eventKey);
			return e;
		}
		// location_select：弹出地理位置选择器
		if ("location_select".equalsIgnoreCase(event)) {
			InMenuEvent e = new InMenuEvent(toUserName, fromUserName, createTime, msgType, event,agentId);
			e.setEventKey(eventKey);
			return e;
		}
		//enter_agent:成员进入应用的事件推送
		if ("enter_agent".equalsIgnoreCase(event)) {
			InEnterAgentEvent e=new InEnterAgentEvent(toUserName, fromUserName, createTime, msgType, eventKey, agentId);
			e.setEventKey(eventKey);
			return e;
		}
		//batch_job_result :异步任务完成事件推送
		if ("batch_job_result".equalsIgnoreCase(event)) {
			InJobEvent e=new InJobEvent(toUserName, fromUserName, createTime, msgType, eventKey, agentId);
			Element batchJob = root.element("BatchJob");
			String jobId = batchJob.elementText("JobId");
			String jobType = batchJob.elementText("JobType");
			String errCode = batchJob.elementText("ErrCode");
			String errMsg = batchJob.elementText("ErrMsg");
			e.setBatchJob(new BatchJob(jobId, jobType, errCode, errMsg));
			return e;
		}
		

		throw new RuntimeException("无法识别的事件类型" + event + "，请查阅微信公众平台开发文档");
		}
}