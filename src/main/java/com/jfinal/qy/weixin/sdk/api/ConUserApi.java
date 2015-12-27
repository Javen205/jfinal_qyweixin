/**
 * Copyright (c) 2011-2015, Javen  (javenlife@126.com).
 *
 * Licensed under the Apache License, Version 1.0 (the "License");
 */
package com.jfinal.qy.weixin.sdk.api;

import com.jfinal.qy.weixin.sdk.utils.HttpUtils;

/**
 * @author Javen
 * 2015年12月19日
 * 管理通讯录>>管理成员
 */
public class ConUserApi {
	private static String createUrl="https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token=ACCESS_TOKEN";
	private static String uploadUrl="https://qyapi.weixin.qq.com/cgi-bin/user/update?access_token=ACCESS_TOKEN";
	private static String deleteUrl="https://qyapi.weixin.qq.com/cgi-bin/user/delete?access_token=ACCESS_TOKEN&userid=USERID";
	private static String batchDeleteUrl="https://qyapi.weixin.qq.com/cgi-bin/user/batchdelete?access_token=ACCESS_TOKEN";
	private static String getUrl="https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&userid=USERID";
	private static String getDepartmentSimpleListUrl="https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token=ACCESS_TOKEN&department_id=DEPARTMENT_ID&fetch_child=FETCH_CHILD&status=STATUS";
	private static String getDepartmentListUrl="https://qyapi.weixin.qq.com/cgi-bin/user/list?access_token=ACCESS_TOKEN&department_id=DEPARTMENT_ID&fetch_child=FETCH_CHILD&status=STATUS";
	private static String inviteUrl="https://qyapi.weixin.qq.com/cgi-bin/invite/send?access_token=ACCESS_TOKEN";
	
	
	/**
	 * 创建成员
	 * @param data
	 * {<br/>
		   "userid": "zhangsan",<br/>
		   "name": "张三",<br/>
		   "department": [1, 2],<br/>
		   "position": "产品经理",<br/>
		   "mobile": "15913215421",<br/>
		   "gender": "1",<br/>
		   "email": "zhangsan@gzdev.com",<br/>
		   "weixinid": "zhangsan4dev",<br/>
		   "avatar_mediaid": "2-G6nrLmr5EC3MNb_-zL1dDdzkd0p7cNliYu9V5w7o8K0",<br/>
		   "extattr": {"attrs":[{"name":"爱好","value":"旅游"},{"name":"卡号","value":"1234567234"}]}
		}<br/>
	 * 
	 * @return
	 */
	public static ApiResult createUser(String data){
		createUrl=createUrl.replace("ACCESS_TOKEN", AccessTokenApi.getAccessTokenStr());
		String jsonResult=HttpUtils.post(createUrl, data);
		return new ApiResult(jsonResult);
	}
	/**
	 * 更新成员
	 * @param data
	 * {<br/>
		   "userid": "zhangsan",<br/>
		   "name": "李四",<br/>
		   "department": [1],<br/>
		   "position": "后台工程师",<br/>
		   "mobile": "15913215421",<br/>
		   "gender": "1",<br/>
		   "email": "zhangsan@gzdev.com",<br/>
		   "weixinid": "lisifordev",<br/>
		   "enable": 1,<br/>
		   "avatar_mediaid": "2-G6nrLmr5EC3MNb_-zL1dDdzkd0p7cNliYu9V5w7o8K0",<br/>
		   "extattr": {"attrs":[{"name":"爱好","value":"旅游"},{"name":"卡号","value":"1234567234"}]}
		}<br/>
	 * @return
	 */
	public static ApiResult updateUser(String data){
		uploadUrl=uploadUrl.replace("ACCESS_TOKEN", AccessTokenApi.getAccessTokenStr());
		String jsonResult=HttpUtils.post(uploadUrl, data);
		return new ApiResult(jsonResult);
	}
	
	/**
	 * 删除成员
	 * @param userid 成员UserID
	 * @return
	 */
	public static ApiResult deleteUser(String userid){
		deleteUrl=deleteUrl.replace("ACCESS_TOKEN", AccessTokenApi.getAccessTokenStr()).replace("USERID", userid);
		String jsonResult=HttpUtils.get(deleteUrl);
		return new ApiResult(jsonResult);
	}
	/**
	 * 批量删除成员
	 * @param data
	 * {
   		"useridlist": ["zhangsan", "lisi"]
	   }
	 * 
	 * @return
	 */
	public static ApiResult batchDeleteUser(String data){
		batchDeleteUrl=batchDeleteUrl.replace("ACCESS_TOKEN", AccessTokenApi.getAccessTokenStr());
		String jsonResult=HttpUtils.post(batchDeleteUrl,data);
		return new ApiResult(jsonResult);
	}
	/**
	 * 获取成员
	 * @param id 成员UserID。对应管理端的帐号
	 * @return
	 */
	public static ApiResult getUser(String userid){
		getUrl=getUrl.replace("ACCESS_TOKEN", AccessTokenApi.getAccessTokenStr()).replace("USERID", userid);
		String jsonResult=HttpUtils.get(getUrl);
		return new ApiResult(jsonResult);
	}
	/**
	 * 获取部门成员
	 * @param departmentId 获取的部门id
	 * @param fetch_child 1/0：是否递归获取子部门下面的成员
	 * @param status 0获取全部成员，1获取已关注成员列表，2获取禁用成员列表，4获取未关注成员列表。status可叠加，未填写则默认为4
	 * @return
	 */
	public static ApiResult getDepartmentUserSimpleList(String department_id,String fetch_child,String status){
		getDepartmentSimpleListUrl=getDepartmentSimpleListUrl.replace("ACCESS_TOKEN", AccessTokenApi.getAccessTokenStr()).replace("DEPARTMENT_ID", department_id).replace("FETCH_CHILD", fetch_child).replace("STATUS", status);
		String jsonResult=HttpUtils.get(getDepartmentSimpleListUrl);
		return new ApiResult(jsonResult);
	}
	/**
	 * 获取部门成员(详情)
	 * @param department_id
	 * @param fetch_child
	 * @param status
	 * @return
	 */
	public static ApiResult getDepartmentUserList(String department_id,String fetch_child,String status){
		getDepartmentListUrl=getDepartmentListUrl.replace("ACCESS_TOKEN", AccessTokenApi.getAccessTokenStr()).replace("DEPARTMENT_ID", department_id).replace("FETCH_CHILD", fetch_child).replace("STATUS", status);
		String jsonResult=HttpUtils.get(getDepartmentListUrl);
		return new ApiResult(jsonResult);
	}
	/**
	 * 邀请成员关注（单个成员）
	 * @param data 
	 * {
		   "userid":"xxxxx"  成员UserID。对应管理端的帐号
		}
	 * @return
	 */
	public static ApiResult inviteUser(String data){
		inviteUrl=inviteUrl.replace("ACCESS_TOKEN", AccessTokenApi.getAccessTokenStr());
		String jsonResult=HttpUtils.post(inviteUrl,data);
		return new ApiResult(jsonResult);
	}
}
