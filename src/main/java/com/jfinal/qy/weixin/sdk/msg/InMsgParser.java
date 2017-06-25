
package com.jfinal.qy.weixin.sdk.msg;

import com.jfinal.kit.LogKit;
import com.jfinal.kit.StrKit;
import com.jfinal.qy.weixin.sdk.msg.in.InImageMsg;
import com.jfinal.qy.weixin.sdk.msg.in.InLocationMsg;
import com.jfinal.qy.weixin.sdk.msg.in.InMsg;
import com.jfinal.qy.weixin.sdk.msg.in.InNotDefinedMsg;
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
import com.jfinal.qy.weixin.sdk.msg.in.speech_recognition.InSpeechRecognitionResults;
import com.jfinal.qy.weixin.sdk.utils.XmlHelper;

public class InMsgParser {

	private InMsgParser() {
	}

	/**
	 * 从 xml 中解析出各类消息与事件
	 * 
	 * @param xml
	 *            xml字符串
	 * @return {InMsg}
	 */
	public static InMsg parse(String xml) {
		XmlHelper xmlHelper = XmlHelper.of(xml);
		return doParse(xmlHelper);
	}

	/**
	 * 消息类型 1：text 文本消息 2：image 图片消息 3：voice 语音消息 4：video 视频消息 shortvideo 小视频消息
	 * 5：location 地址位置消息 6：link 链接消息 7：event 事件
	 */
	private static InMsg doParse(XmlHelper xmlHelper) {
		String toUserName = xmlHelper.getString("//ToUserName");
		String fromUserName = xmlHelper.getString("//FromUserName");
		Integer createTime = xmlHelper.getNumber("//CreateTime").intValue();
		String msgType = xmlHelper.getString("//MsgType");
		String agentId = xmlHelper.getString("//AgentID");
		if ("text".equals(msgType))
			return parseInTextMsg(xmlHelper, toUserName, fromUserName, createTime, msgType, agentId);
		if ("image".equals(msgType))
			return parseInImageMsg(xmlHelper, toUserName, fromUserName, createTime, msgType, agentId);
		if ("voice".equals(msgType))
			return parseInVoiceMsgAndInSpeechRecognitionResults(xmlHelper, toUserName, fromUserName, createTime,
					msgType, agentId);
		if ("video".equals(msgType))
			return parseInVideoMsg(xmlHelper, toUserName, fromUserName, createTime, msgType, agentId);
		if ("shortvideo".equals(msgType)) // 支持小视频
			return parseInShortVideoMsg(xmlHelper, toUserName, fromUserName, createTime, msgType, agentId);
		if ("location".equals(msgType))
			return parseInLocationMsg(xmlHelper, toUserName, fromUserName, createTime, msgType, agentId);
		if ("event".equals(msgType))
			return parseInEvent(xmlHelper, toUserName, fromUserName, createTime, msgType, agentId);
		LogKit.error("无法识别的消息类型 " + msgType + "，请查阅微信公众平台开发文档");
		return parseInNotDefinedMsg(xmlHelper, toUserName, fromUserName, createTime, msgType, agentId);
	}

	private static InMsg parseInTextMsg(XmlHelper xmlHelper, String toUserName, String fromUserName, Integer createTime,
			String msgType, String agentId) {
		InTextMsg msg = new InTextMsg(toUserName, fromUserName, createTime, msgType, agentId);
		msg.setContent(xmlHelper.getString("//Content"));
		msg.setMsgId(xmlHelper.getString("//MsgId"));
		return msg;
	}

	private static InMsg parseInImageMsg(XmlHelper xmlHelper, String toUserName, String fromUserName,
			Integer createTime, String msgType, String agentId) {
		InImageMsg msg = new InImageMsg(toUserName, fromUserName, createTime, msgType, agentId);
		msg.setPicUrl(xmlHelper.getString("//PicUrl"));
		msg.setMediaId(xmlHelper.getString("//MediaId"));
		msg.setMsgId(xmlHelper.getString("//MsgId"));
		return msg;
	}

	private static InMsg parseInVoiceMsgAndInSpeechRecognitionResults(XmlHelper xmlHelper, String toUserName,
			String fromUserName, Integer createTime, String msgType, String agentId) {
		String recognition = xmlHelper.getString("//Recognition");
		String mediaId = xmlHelper.getString("//MediaId");
		String format = xmlHelper.getString("//Format");
		String msgId = xmlHelper.getString("//MsgId");
		if (StrKit.isBlank(recognition)) {
			InVoiceMsg msg = new InVoiceMsg(toUserName, fromUserName, createTime, msgType, agentId);
			msg.setMediaId(mediaId);
			msg.setFormat(format);
			msg.setMsgId(msgId);
			return msg;
		} else {
			InSpeechRecognitionResults msg = new InSpeechRecognitionResults(toUserName, fromUserName, createTime,
					msgType, agentId);
			msg.setMediaId(mediaId);
			msg.setFormat(format);
			msg.setMsgId(msgId);
			// 与 InVoiceMsg 唯一的不同之处
			msg.setRecognition(recognition);
			return msg;
		}
	}

	private static InMsg parseInVideoMsg(XmlHelper xmlHelper, String toUserName, String fromUserName,
			Integer createTime, String msgType, String agentId) {
		InVideoMsg msg = new InVideoMsg(toUserName, fromUserName, createTime, msgType, agentId);
		msg.setMediaId(xmlHelper.getString("//MediaId"));
		msg.setThumbMediaId(xmlHelper.getString("//ThumbMediaId"));
		msg.setMsgId(xmlHelper.getString("//MsgId"));
		return msg;
	}

	private static InMsg parseInShortVideoMsg(XmlHelper xmlHelper, String toUserName, String fromUserName,
			Integer createTime, String msgType, String agentId) {
		InShortVideoMsg msg = new InShortVideoMsg(toUserName, fromUserName, createTime, msgType, agentId);
		msg.setMediaId(xmlHelper.getString("//MediaId"));
		msg.setThumbMediaId(xmlHelper.getString("//ThumbMediaId"));
		msg.setMsgId(xmlHelper.getString("//MsgId"));
		return msg;
	}

	private static InMsg parseInLocationMsg(XmlHelper xmlHelper, String toUserName, String fromUserName,
			Integer createTime, String msgType, String agentId) {
		InLocationMsg msg = new InLocationMsg(toUserName, fromUserName, createTime, msgType, agentId);
		msg.setLocation_X(xmlHelper.getString("//Location_X"));
		msg.setLocation_Y(xmlHelper.getString("//Location_Y"));
		msg.setScale(xmlHelper.getString("//Scale"));
		msg.setLabel(xmlHelper.getString("//Label"));
		msg.setMsgId(xmlHelper.getString("//MsgId"));
		return msg;
	}

	// 解析各种事件
	private static InMsg parseInEvent(XmlHelper xmlHelper, String toUserName, String fromUserName, Integer createTime,
			String msgType, String agentId) {
		String event = xmlHelper.getString("//Event");
		String eventKey = xmlHelper.getString("//EventKey");
		// 关注/取消关注事件（包括二维码扫描关注，二维码扫描关注事件与扫描带参数二维码事件是两回事）
		if (("subscribe".equalsIgnoreCase(event) || "unsubscribe".equals(event)) && StrKit.isBlank(eventKey)) {
			return new InFollowEvent(toUserName, fromUserName, createTime, msgType, event, agentId);
		}
		// 扫描带参数二维码事件之一 1: 用户未关注时，进行关注后的事件推送
		if ("subscribe".equalsIgnoreCase(event) && StrKit.notBlank(eventKey) && eventKey.startsWith("qrscene_")) {
			InQrCodeEvent e = new InQrCodeEvent(toUserName, fromUserName, createTime, msgType, event, agentId);
			e.setEventKey(eventKey);
			return e;
		}
		// 上报地理位置事件
		if ("location".equalsIgnoreCase(event)) {
			InLocationEvent e = new InLocationEvent(toUserName, fromUserName, createTime, msgType, event, agentId);
			e.setLatitude(xmlHelper.getString("//Latitude"));
			e.setLongitude(xmlHelper.getString("//Longitude"));
			e.setPrecision(xmlHelper.getString("//Precision"));
			return e;
		}
		// 自定义菜单事件之一 1：点击菜单拉取消息时的事件推送
		if ("click".equalsIgnoreCase(event)) {
			InMenuEvent e = new InMenuEvent(toUserName, fromUserName, createTime, msgType, event, agentId);
			e.setEventKey(eventKey);
			return e;
		}
		// 自定义菜单事件之二 2：点击菜单跳转链接时的事件推送
		if ("view".equalsIgnoreCase(event)) {
			InMenuEvent e = new InMenuEvent(toUserName, fromUserName, createTime, msgType, event, agentId);
			e.setEventKey(eventKey);
			return e;
		}
		// 扫码推事件 和 扫码推事件且弹出“消息接收中”提示框
		if ("scancode_push".equalsIgnoreCase(event) || "scancode_waitmsg".equalsIgnoreCase(event)) {
			InMenuEvent e = new InMenuEvent(toUserName, fromUserName, createTime, msgType, event, agentId);
			e.setEventKey(eventKey);
			String scanType = xmlHelper.getString("//ScanCodeInfo/ScanType");
			String scanResult = xmlHelper.getString("//ScanCodeInfo/ScanResult");
			e.setScanCodeInfo(new ScanCodeInfo(scanType, scanResult));
			return e;
		}
		// pic_sysphoto：弹出系统拍照发图，这个后台其实收不到该菜单的消息，点击它后，调用的是手机里面的照相机功能，而照相以后再发过来时，就收到的是一个图片消息了
		if ("pic_sysphoto".equalsIgnoreCase(event)) {
			InMenuEvent e = new InMenuEvent(toUserName, fromUserName, createTime, msgType, event, agentId);
			e.setEventKey(eventKey);
			return e;
		}
		// pic_photo_or_album：弹出拍照或者相册发图
		if ("pic_photo_or_album".equalsIgnoreCase(event)) {
			InMenuEvent e = new InMenuEvent(toUserName, fromUserName, createTime, msgType, event, agentId);
			e.setEventKey(eventKey);
			return e;
		}
		// pic_weixin：弹出微信相册发图器
		if ("pic_weixin".equalsIgnoreCase(event)) {
			InMenuEvent e = new InMenuEvent(toUserName, fromUserName, createTime, msgType, event, agentId);
			e.setEventKey(eventKey);
			return e;
		}
		// location_select：弹出地理位置选择器
		if ("location_select".equalsIgnoreCase(event)) {
			InMenuEvent e = new InMenuEvent(toUserName, fromUserName, createTime, msgType, event, agentId);
			e.setEventKey(eventKey);
			return e;
		}
		// enter_agent:成员进入应用的事件推送
		if ("enter_agent".equalsIgnoreCase(event)) {
			InEnterAgentEvent e = new InEnterAgentEvent(toUserName, fromUserName, createTime, msgType, eventKey,
					agentId);
			e.setEventKey(eventKey);
			return e;
		}
		// batch_job_result :异步任务完成事件推送
		if ("batch_job_result".equalsIgnoreCase(event)) {
			InJobEvent e = new InJobEvent(toUserName, fromUserName, createTime, msgType, eventKey, agentId);

			String jobId = xmlHelper.getString("//BatchJob/JobId");
			String jobType = xmlHelper.getString("//BatchJob/JobType");
			String errCode = xmlHelper.getString("//BatchJob/ErrCode");
			String errMsg = xmlHelper.getString("//BatchJob/ErrMsg");
			e.setBatchJob(new BatchJob(jobId, jobType, errCode, errMsg));
			return e;
		}

		throw new RuntimeException("无法识别的事件类型" + event + "，请查阅微信公众平台开发文档");
	}

	private static InMsg parseInNotDefinedMsg(XmlHelper xmlHelper, String toUserName, String fromUserName,
			Integer createTime, String msgType, String agentId) {
		InNotDefinedMsg msg = new InNotDefinedMsg(toUserName, fromUserName, createTime, msgType, agentId);
		msg.setXmlHelper(xmlHelper);
		return msg;
	}
}