/**
 * Copyright (c) 2015-2016, Javen  (javenlife@126.com).
 *
 * Licensed under the Apache License, Version 1.0 (the "License");
 */
package com.jfinal.qyweixin.sdk.api;

import com.jfinal.kit.HttpKit;
import com.jfinal.qyweixin.sdk.kit.ParaMap;
import com.jfinal.qyweixin.sdk.utils.HttpUtils;

/**
 * @author Javen 
 * 2016年1月9日 
 * 
 * 企业会话接口
 */
public class ChatApi {
	// 创建会话
	private static String createUrl = "https://qyapi.weixin.qq.com/cgi-bin/chat/create?access_token=ACCESS_TOKEN";
	// 获取会话
	private static String getUrl = "https://qyapi.weixin.qq.com/cgi-bin/chat/get";
	//修改会话
	private static String updateUrl = "https://qyapi.weixin.qq.com/cgi-bin/chat/update?access_token=ACCESS_TOKEN";
	// 退出会话
	private static String quitUrl = "https://qyapi.weixin.qq.com/cgi-bin/chat/quit?access_token=ACCESS_TOKEN";
	// 消除会话未读状态
	private static String clearNotityUrl = "https://qyapi.weixin.qq.com/cgi-bin/chat/clearnotify?access_token=ACCESS_TOKEN";
	// 发送消息
	private static String sendUrl = "https://qyapi.weixin.qq.com/cgi-bin/chat/send?access_token=ACCESS_TOKEN";
	//设置成员新消息免打扰
	private static String setmuteUrl = "https://qyapi.weixin.qq.com/cgi-bin/chat/setmute?access_token=ACCESS_TOKEN";
	/**
	 * createUrl 创建会话Url <br/>
	 * getUrl    获取会话Url <br/>
	 * updateUrl 更新会话Url <br/>
	 * quitUrl   退出会话URL <br/>
	 * clearNotityUrl 消除会话未读状态URL <br/>
	 * sendUrl 发送消息 <br/>
	 * setmuteUrl 设置成员新消息免打扰 <br/>
	 */
	public enum ChatUrl {
		createUrl,updateUrl, quitUrl, clearNotityUrl, sendUrl,setmuteUrl
	}
	
	public static ApiResult Chat(ChatUrl chatUrl, String data) {
		String url = null;
		String urlName = chatUrl.name();
		if (urlName.equals("createUrl")) {
			url = createUrl;
		} else if (urlName.equals("updateUrl")) {
			url = updateUrl;
		} else if (urlName.equals("quitUrl")) {
			url = quitUrl;
		} else if (urlName.equals("clearNotityUrl")) {
			url = clearNotityUrl;
		} else if (urlName.equals("sendUrl")) {
			url = sendUrl;
		} else if (urlName.equals("setmuteUrl")) {
			url = setmuteUrl;
		}
		String jsonResult = HttpUtils.post(url + AccessTokenApi.getAccessTokenStr(), data);
		return new ApiResult(jsonResult);
	}
	
	public static ApiResult getChat(String chatid,String data){
		ParaMap pm=ParaMap.create("access_token",AccessTokenApi.getAccessTokenStr()).put("chatid", chatid);
		String jsonResult = HttpKit.post(getUrl,pm.getData(),data);
		return new ApiResult(jsonResult);
	}
}
