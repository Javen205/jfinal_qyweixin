/**
 * Copyright (c) 2011-2015, Javen  (javenlife@126.com).
 *
 * Licensed under the Apache License, Version 1.0 (the "License");
 */
package com.jfinal.qyweixin.sdk.api;

import com.jfinal.qyweixin.sdk.kit.ParaMap;
import com.jfinal.qyweixin.sdk.utils.HttpUtils;

/**
 * @author Javen
 * 2015年12月19日
 * 管理通讯录>>管理标签
 */
public class ConTagApi {
	private static String createUrl="https://qyapi.weixin.qq.com/cgi-bin/tag/create?access_token=";
	private static String uploadUrl="https://qyapi.weixin.qq.com/cgi-bin/tag/update?access_token=";
	private static String deleteUrl="https://qyapi.weixin.qq.com/cgi-bin/tag/delete";
	private static String getUrl="https://qyapi.weixin.qq.com/cgi-bin/tag/get";
	private static String addTagUsersUrl="https://qyapi.weixin.qq.com/cgi-bin/tag/addtagusers?access_token=";
	private static String delTagUsersUrl="https://qyapi.weixin.qq.com/cgi-bin/tag/deltagusers?access_token=";
	private static String getTagListUrl="https://qyapi.weixin.qq.com/cgi-bin/tag/list?access_token=";
	
	
	/**
	 * 创建标签
	 * @param data
	 * {<br/>
		   "tagname": "UI", 标签名称，长度为1~64个字节，标签名不可与其他标签重名。<br/>
		   "tagid": id   标签id，整型，指定此参数时新增的标签会生成对应的标签id，<br/>不指定时则以目前最大的id自增。
		}<br/>
	 * 
	 * @return
	 */
	public static ApiResult createTag(String data){
		String jsonResult=HttpUtils.post(createUrl +  AccessTokenApi.getAccessTokenStr(), data);
		return new ApiResult(jsonResult);
	}
	/**
	 * 更新标签名字
	 * @param data
	 * {<br/>
		   "tagid": "1", 标签ID<br/>
		   "tagname": "UI design" 标签名称，长度为1~64个字节，标签不可与其他标签重名。<br/>
	   }<br/>
	 * @return
	 */
	public static ApiResult updateTag(String data){
		String jsonResult=HttpUtils.post(uploadUrl + AccessTokenApi.getAccessTokenStr(), data);
		return new ApiResult(jsonResult);
	}
	
	/**
	 * 删除标签
	 * @param tagid 
	 * @return
	 */
	public static ApiResult deleteTag(String tagid){
		ParaMap pm=ParaMap.create("access_token", AccessTokenApi.getAccessTokenStr()).put("tagid", tagid);
		String jsonResult=HttpUtils.get(deleteUrl,pm.getData());
		return new ApiResult(jsonResult);
	}
	/**
	 * 获取标签成员
	 * @param tagid 标签ID
	 * @return
	 */
	public static ApiResult getTagUser(String tagid){
		ParaMap pm=ParaMap.create("access_token", AccessTokenApi.getAccessTokenStr()).put("tagid", tagid);
		String jsonResult=HttpUtils.get(getUrl,pm.getData());
		return new ApiResult(jsonResult);
	}
	
	/**
	 * 增加标签成员
	 * @param data
	 * {<br/>
		   "tagid": "1", 标签ID<br/>
		   "userlist":[ "user1","user2"], 企业成员ID列表，注意：userlist、partylist不能同时为空，单次请求长度不超过1000<br/>
		   "partylist": [4] 企业部门ID列表，注意：userlist、partylist不能同时为空，单次请求长度不超过100<br/>
		}<br/>
	 * @return
	 */
	public static ApiResult addTagUsers(String data){
		String jsonResult=HttpUtils.post(addTagUsersUrl + AccessTokenApi.getAccessTokenStr(),data);
		return new ApiResult(jsonResult);
	}
	/**
	 * 删除标签成员
	 * @param data
	 * {<br/>
		   "tagid": "1",  标签ID 企业成员ID列表，注意：userlist、partylist不能同时为空<br/>
		   "userlist":[ "user1","user2"], 企业部门ID列表，注意：userlist、partylist不能同时为空<br/>
		   "partylist":[2,4]<br/>
		}<br/>
	 * @return
	 */
	public static ApiResult deleteTagUsers(String data){
		String jsonResult=HttpUtils.post(delTagUsersUrl + AccessTokenApi.getAccessTokenStr(),data);
		return new ApiResult(jsonResult);
	}
	/**
	 * 获取标签列表
	 * @return
	 */
	public static ApiResult getTagList(){
		String jsonResult=HttpUtils.get(getTagListUrl + AccessTokenApi.getAccessTokenStr());
		return new ApiResult(jsonResult);
	}
	
}
