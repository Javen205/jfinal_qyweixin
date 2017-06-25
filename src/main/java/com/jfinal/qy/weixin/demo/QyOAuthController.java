/**
 * Copyright (c) 2011-2015, Javen  (javenlife@126.com).
 *
 * Licensed under the Apache License, Version 1.0 (the "License");
 */
package com.jfinal.qy.weixin.demo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.PropKit;
import com.jfinal.log.Log;
import com.jfinal.qy.weixin.sdk.api.ApiConfig;
import com.jfinal.qy.weixin.sdk.api.ApiConfigKit;
import com.jfinal.qy.weixin.sdk.api.ApiResult;
import com.jfinal.qy.weixin.sdk.api.OAuthApi;
import com.jfinal.qy.weixin.sdk.jfinal.ApiController;

/**
 * @author Javen
 * 2015年12月27日
 */
public class QyOAuthController extends ApiController {
	private Log logger=Log.getLog(QyOAuthController.class);
	/**
	 * 如果要支持多公众账号，只需要在此返回各个公众号对应的  ApiConfig 对象即可
	 * 可以通过在请求 url 中挂参数来动态从数据库中获取 ApiConfig 属性值
	 */
	public ApiConfig getApiConfig() {
		ApiConfig ac = new ApiConfig();
		
		// 配置微信 API 相关常量
		ac.setToken(PropKit.get("token"));
		ac.setCorpId(PropKit.get("corpId"));
		ac.setCorpSecret(PropKit.get("secret"));
		ac.setAgentId(PropKit.get("agentId"));
				
		
		/**
		 *  是否对消息进行加密，对应于微信平台的消息加解密方式：
		 *  1：true进行加密且必须配置 encodingAesKey
		 *  2：false采用明文模式，同时也支持混合模式
		 */
		ac.setEncryptMessage(PropKit.getBoolean("encryptMessage", false));
		ac.setEncodingAesKey(PropKit.get("encodingAesKey", "setting it in config file"));
		return ac;
	}
	public void index(){
		try {
			String redirect_uri=URLEncoder.encode(PropKit.get("domain")+"/qyoauth2/code", "utf-8");
			String codeUrl = OAuthApi.getCodeUrl(redirect_uri, "123",true);
			System.out.println("codeUrl>>>"+codeUrl);
			redirect(codeUrl);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public void code(){
		String userId =null;
		String deviceId=null;
		String openid=null;
		String state = null;
		if (!isParaBlank("code")) {
			String code = getPara("code");
			System.out.println("code>>>"+code);
			logger.info("code:"+code);
			if (!isParaBlank("state")) {
				state = getPara("state");
				logger.info(" state:"+state);
				System.out.println(" state:"+state);
			}
			ApiResult userInfoApiResult = OAuthApi.getUserInfoByCode(code);
			if (userInfoApiResult.isSucceed()) {
				String userInfoJson=userInfoApiResult.getJson();
				JSONObject object = JSON.parseObject(userInfoJson);
				deviceId = object.getString("DeviceId");
				try {
					userId = object.getString("UserId");
					System.out.println("userId:"+userId);
					//如果获取userId为空 说明没有关注
					if (userId!=null && !userId.equals("")) {
						ApiResult toOpenIdApiResult = OAuthApi.ToOpenId("{\"userid\":\""+userId+"\",\"agentid\":"+ApiConfigKit.getApiConfig().getAgentId()+"}");
						System.out.println("toOpenIdApiResult:"+toOpenIdApiResult.getJson());
						if (toOpenIdApiResult.isSucceed()) {
							openid=JSON.parseObject(toOpenIdApiResult.getJson()).getString("openid");
						}
					}else {
						openid = object.getString("OpenId");
						String json="{\"openid\":\""+openid+"\"}";
						System.out.println("json..."+json);
						//如果未关注 openid无法转化为userid
						ApiResult toUserIdApiResult = OAuthApi.ToUserId(json);
						System.out.println("toUserIdApiResult:"+toUserIdApiResult.getJson());
						if (toUserIdApiResult.isSucceed()) {
							userId=JSON.parseObject(toUserIdApiResult.getJson()).getString("userid");
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			setSessionAttr("userId", userId);
			setSessionAttr("openId", openid);
			if (state.equals("123") ) {
				redirect("/");
			}
//			renderText(userInfoApiResult.getJson()+">>>userId:"+userId+" deviceId:"+deviceId+" openid:"+openid);
		}
	}
}
