/**
 * Copyright (c) 2011-2015, Javen  (javenlife@126.com).
 *
 * Licensed under the Apache License, Version 1.0 (the "License");
 */
package com.jfinal.qyweixin.sdk.api;

import com.jfinal.kit.HttpKit;
import com.jfinal.kit.JsonKit;
import com.jfinal.qyweixin.sdk.kit.ParaMap;
import com.jfinal.qyweixin.sdk.msg.send.QiYeFileMsg;
import com.jfinal.qyweixin.sdk.msg.send.QiYeImageMsg;
import com.jfinal.qyweixin.sdk.msg.send.QiYeMpNewsMsg;
import com.jfinal.qyweixin.sdk.msg.send.QiYeNewsMsg;
import com.jfinal.qyweixin.sdk.msg.send.QiYeTextMsg;
import com.jfinal.qyweixin.sdk.msg.send.QiYeVideoMsg;
import com.jfinal.qyweixin.sdk.msg.send.QiYeVoiceMsg;

/**
 * @author Javen
 * 2015年12月12日
 * 发送消息接口
 * Https请求方式: POST
  https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN
 * 
 */
public class SendMessageApi {
	
	private static final String url="https://qyapi.weixin.qq.com/cgi-bin/message/send";
	
	/**
	 * 文本消息
	 * @param text
	 * @return
	 */
	public static ApiResult sendTextMsg(QiYeTextMsg text) {
		String jsonStr = JsonKit.toJson(text);
		return sendMessage(jsonStr);
	}
	
	/**
	 * 图片消息
	 * @param image
	 * @return
	 */
	public static ApiResult sendImageMsg(QiYeImageMsg image) {
		String jsonStr = JsonKit.toJson(image);
		return sendMessage(jsonStr);
	}
	
	/**
	 * 语音消息
	 * @param voice
	 * @return
	 */
	public static ApiResult sendVoiceMsg(QiYeVoiceMsg voice) {
		String jsonStr = JsonKit.toJson(voice);
		return sendMessage(jsonStr);
	}
	
	/**
	 * 视频消息
	 * @param video
	 * @return
	 */
	public static ApiResult sendVideoMsg(QiYeVideoMsg video) {
		String jsonStr = JsonKit.toJson(video);
		return sendMessage(jsonStr);
	}
	
	/**
	 * （图文混排的消息
	 * @param news
	 * @return
	 */
	public static ApiResult sendNewsMsg(QiYeNewsMsg news) {
		String jsonStr = JsonKit.toJson(news);
		return sendMessage(jsonStr);
	}
	
	/**
	 * 多图文混排的消息
	 * @param mpNews
	 * @return
	 */
	public static ApiResult sendMpNewsMsg(QiYeMpNewsMsg mpNews) {
		String jsonStr = JsonKit.toJson(mpNews);
		return sendMessage(jsonStr);
	}
	

	/**
	 * 文件消息
	 * @param file
	 * @return
	 */
	public static ApiResult sendFileMsg(QiYeFileMsg file) {
		String jsonStr = JsonKit.toJson(file);
		return sendMessage(jsonStr);
	}
	
	public static ApiResult sendMessage(String jsonStr) {
		ParaMap pm = ParaMap.create("access_token", AccessTokenApi.getAccessTokenStr());
		return new ApiResult(HttpKit.post(url, pm.getData(), jsonStr));
	}
	
	
}
