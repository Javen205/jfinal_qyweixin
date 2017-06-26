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
 * 管理通讯录>>管理部门
 */
public class ConDepartmentApi {
	private static String createUrl="https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=";
	private static String uploadUrl="https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token=";
	private static String deleteUrl="https://qyapi.weixin.qq.com/cgi-bin/department/delete";
	private static String getUrl="https://qyapi.weixin.qq.com/cgi-bin/department/list";
	
	/**
	 * 创建部门
	 * @param data
	 * {<br/>
		   "name": "广州研发中心",部门名称。长度限制为1~64个字节，字符不能包括\:*?"<>｜<br/>
		   "parentid": "1",父亲部门id。根部门id为1<br/>
		   "order": "1",在父部门中的次序值。order值小的排序靠前。<br/>
		   "id": "1" 部门id，整型。指定时必须大于1，不指定时则自动生成<br/>
	   }<br/>
	 * 
	 * @return
	 */
	public static ApiResult createDepartment(String data){
		String jsonResult=HttpUtils.post(createUrl + AccessTokenApi.getAccessTokenStr(), data);
		return new ApiResult(jsonResult);
	}
	/**
	 * 更新部分
	 * @param data
	 * {<br/>
		   "name": "广州研发中心",部门名称。长度限制为1~64个字节，字符不能包括\:*?"<>｜<br/>
		   "parentid": "1",父亲部门id。根部门id为1<br/>
		   "order": "1",在父部门中的次序值。order值小的排序靠前。<br/>
		   "id": "1" 部门id<br/>
	   }<br/>
	 * @return
	 */
	public static ApiResult updateDepartment(String data){
		String jsonResult=HttpUtils.post(uploadUrl + AccessTokenApi.getAccessTokenStr(), data);
		return new ApiResult(jsonResult);
	}
	
	/**
	 * 删除部门
	 * @param id 部门id
	 * @return
	 */
	public static ApiResult deleteDepartment(String id){
		ParaMap pm=ParaMap.create("access_token", AccessTokenApi.getAccessTokenStr()).put("id", id);
		String jsonResult=HttpUtils.get(deleteUrl,pm.getData());
		return new ApiResult(jsonResult);
	}
	/**
	 * 获取部门列表
	 * @param id 部门id。获取指定部门及其下的子部门
	 * @return
	 */
	public static ApiResult getDepartment(String id){
		ParaMap pm=ParaMap.create("access_token", AccessTokenApi.getAccessTokenStr()).put("id", id);
		String jsonResult=HttpUtils.get(getUrl,pm.getData());
		return new ApiResult(jsonResult);
	}
}
