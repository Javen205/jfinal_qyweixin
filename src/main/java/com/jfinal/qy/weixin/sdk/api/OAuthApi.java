/**
 * Copyright (c) 2011-2015, Javen  (javenlife@126.com).
 *
 * Licensed under the Apache License, Version 1.0 (the "License");
 */
package com.jfinal.qy.weixin.sdk.api;

import com.jfinal.qy.weixin.sdk.utils.HttpUtils;

/**
 * @author Javen
 * 2015年12月27日
 */
public class OAuthApi {
	private static String getCodeUrl="https://open.weixin.qq.com/connect/oauth2/authorize?appid=CORPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
	private static String getUserInfoUrl="https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=ACCESS_TOKEN&code=CODE";
	/**
	 * userid转换成openid接口
	 */
	private static String toOpenIdUrl="https://qyapi.weixin.qq.com/cgi-bin/user/convert_to_openid?access_token=ACCESS_TOKEN";
	/**
	 * openid转换成userid接口
	 */
	private static String toUserIdUrl="https://qyapi.weixin.qq.com/cgi-bin/user/convert_to_userid?access_token=ACCESS_TOKEN";
	/**
	 * 获取企业授权codeUrl
	 * @param redirectUri
	 * @param state
	 * @return
	 */
	public static String getCodeUrl(String redirectUri,String state){
		getCodeUrl=getCodeUrl.replace("CORPID", ApiConfigKit.getApiConfig().getCorpId())
				.replace("REDIRECT_URI", redirectUri);
		if (state!=null && !state.equals("")) {
			getCodeUrl=getCodeUrl.replace("STATE", state);
		}else {
			getCodeUrl=getCodeUrl.replace("&state=STATE", "");
		}
				
		return getCodeUrl;
	}
	/**
	 * 根据code获取成员信息
	 * @param code
	 * @return
	 */
	public static ApiResult getUserInfoByCode(String code){
		getUserInfoUrl=getUserInfoUrl.replace("ACCESS_TOKEN", AccessTokenApi.getAccessTokenStr())
				.replace("CODE", code);
		String jsonResult = HttpUtils.get(getUserInfoUrl);
		return new ApiResult(jsonResult);
	}
	/**
	 * userid转换成openid接口
	 * @param data
	 *  {<br/>
		   "userid": "zhangsan",<br/>
		   "agentid": 1<br/>
		}<br/>
	 * @return
	 */
	public static ApiResult ToOpenId(String data){
		toOpenIdUrl=toOpenIdUrl.replace("ACCESS_TOKEN", AccessTokenApi.getAccessTokenStr());
		String jsonResult=HttpUtils.post(toOpenIdUrl, data);
		return new ApiResult(jsonResult);
	}
	/**
	 * 
	 * openid转换成userid接口
	 * @param data
	 {
   		"openid": "oDOGms-6yCnGrRovBj2yHij5JL6E"
	 }
	 * @return
	 */
	public static ApiResult ToUserId(String data){
		toUserIdUrl=toUserIdUrl.replace("ACCESS_TOKEN", AccessTokenApi.getAccessTokenStr());
		String jsonResult=HttpUtils.post(toUserIdUrl, data);
		return new ApiResult(jsonResult);
	}
}
