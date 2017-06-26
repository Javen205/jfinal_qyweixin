/**
 * Copyright (c) 2011-2015, Javen  (javenlife@126.com).
 *
 * Licensed under the Apache License, Version 1.0 (the "License");
 */
package com.jfinal.qyweixin.sdk.api;

import com.jfinal.qyweixin.sdk.kit.ParaMap;
import com.jfinal.qyweixin.sdk.utils.HttpUtils;

/**
 * 
 * @author Javen
 * 2015年12月27日
 * 管理企业号应用
 */
public class AgentApi {
	private static String getUrl="https://qyapi.weixin.qq.com/cgi-bin/agent/get";
	private static String setUrl="https://qyapi.weixin.qq.com/cgi-bin/agent/set?access_token=";
	private static String getListUrl="https://qyapi.weixin.qq.com/cgi-bin/agent/list?access_token=";
	
	/**
	 * 获取企业号应用
	 * @param agentid
	 * @return
	 * 该API用于获取企业号某个应用的基本信息，包括头像、<br/>昵称、帐号类型、认证类型、可见范围等信息
	 */
	public static ApiResult getAgent(String agentid){
		ParaMap pm = ParaMap.create("access_token", AccessTokenApi.getAccessTokenStr())
				.put("agentid", agentid);
		String jsonResult=HttpUtils.get(getUrl,pm.getData());
		return new ApiResult(jsonResult);
	}
	/**
	 * 设置企业号应用
	 * @param data
	 * {
		   "agentid": "5",  企业应用的id<br/>
		   "report_location_flag": "0", 企业应用是否打开地理位置上报 0：不上报；1：进入会话上报；2：持续上报<br/>
		   "logo_mediaid": "xxxxx",  企业应用头像的mediaid<br/>
		   "name": "NAME", 企业应用名称 <br/>
		   "description": "DESC", 企业应用详情<br/>
		   "redirect_domain": "xxxxxx", 企业应用可信域名<br/>
		   "isreportuser":0, 是否接收用户变更通知。0：不接收；1：接收<br/>
		   "isreportenter":0 是否上报用户进入应用事件。0：不接收；1：接收<br/>
		}
	 * @return
	 * 该API用于设置企业应用的选项设置信息，<br/>如：地理位置上报等。第三方服务商不能调用该接口设置授权的主页型应用。
	 */
	public static ApiResult setAgent(String data){
		String jsonResult=HttpUtils.post(setUrl +AccessTokenApi.getAccessTokenStr() ,data);
		return new ApiResult(jsonResult);
	}
	
	/**
	 * 获取应用概况列表
	 * @return
	 * 该API 用于获取secret所在管理组内的应用概况，<br/>会返回管理组内应用的id及名称、头像等信息
	 */
	public static ApiResult getListAgent(){
		String jsonResult=HttpUtils.get(getListUrl+ AccessTokenApi.getAccessTokenStr());
		return new ApiResult(jsonResult);
	}
}
