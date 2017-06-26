/**
 * Copyright (c) 2011-2015, Javen  (javenlife@126.com).
 *
 * Licensed under the Apache License, Version 1.0 (the "License");
 */
package com.jfinal.qyweixin.sdk.api;

import com.jfinal.qyweixin.sdk.utils.HttpUtils;

/**
 * @author Javen
 * 2015年12月20日
 * 管理通讯录>>异步任务接口
 * 
 * 此方法还可以优化 只有url 以及参数（json）不一样
 */
public class ConBatchApi {
	/**
	 * 邀请成员关注
	 */
	private static String batchInviteUsersUrl="https://qyapi.weixin.qq.com/cgi-bin/batch/inviteuser?access_token=ACCESS_TOKEN";
	/**
	 * 增量更新成员
	 */
	private static String batchSyncUserUrl="https://qyapi.weixin.qq.com/cgi-bin/batch/syncuser?access_token=ACCESS_TOKEN";
	
	/**
	 * 全量覆盖成员
	 */
	private static String batchReplaceUserUrl="https://qyapi.weixin.qq.com/cgi-bin/batch/replaceuser?access_token=ACCESS_TOKEN";
	
	/**
	 * 全量覆盖部门
	 */
	private static String batchReplacePartyUrl="https://qyapi.weixin.qq.com/cgi-bin/batch/replaceparty?access_token=ACCESS_TOKEN";
	/**
	 * 获取异步任务结果
	 */
	private static String batchGetResultUrl="https://qyapi.weixin.qq.com/cgi-bin/batch/getresult?access_token=ACCESS_TOKEN&jobid=JOBID";
	
	/**
	 * 邀请成员关注
	 * @param data
	 * {<br/>
			"touser":"xxx|xxx",  成员ID列表，多个接收者用‘|’分隔，最多支持1000个。<br/>
			"toparty":"xxx|xxx", 部门ID列表，多个接收者用‘|’分隔，最多支持100个。<br/>
			"totag":"xxx|xxx",  标签ID列表，多个接收者用‘|’分隔。<br/>
			"callback": 回调信息。如填写该项则任务完成后，通过callback推送事件给企业。具体请参考应用回调模式中的相应选项<br/>
			{<br/>
			 	"url": "xxx",<br/>
			 	"token": "xxx",<br/>
			 	"encodingaeskey": "xxx"<br/>
			}<br/>
		}<br/>
	 * @return
	 */
	public static ApiResult inviteUsers(String data){
		String jsonStr = HttpUtils.post(batchInviteUsersUrl.replace("ACCESS_TOKEN", AccessTokenApi.getAccessTokenStr()), data);
		return new ApiResult(jsonStr);
	}
	/**
	 * 增量更新成员
	 * @param data
	 * {<br/>
			"media_id":"xxxxxx", 上传的csv文件的media_id <br/>
			"callback":<br/>
			{
			 	"url": "xxx",<br/>
			 	"token": "xxx",<br/>
			 	"encodingaeskey": "xxx"<br/>
			}<br/>
		}<br/>
	 * @return
	 */
	public static ApiResult updateSyncUser(String data){
		String jsonStr = HttpUtils.post(batchSyncUserUrl.replace("ACCESS_TOKEN", AccessTokenApi.getAccessTokenStr()), data);
		return new ApiResult(jsonStr);
	}
	/**
	 * 全量覆盖成员
	 * @param data
	 * {
			"media_id":"xxxxxx",
			"callback":
			{
			 	"url": "xxx",
			 	"token": "xxx",
			 	"encodingaeskey": "xxx"
			}
		}
	 * @return
	 */
	public static ApiResult batgReplaceUser(String data){
		String jsonStr = HttpUtils.post(batchReplaceUserUrl.replace("ACCESS_TOKEN", AccessTokenApi.getAccessTokenStr()), data);
		return new ApiResult(jsonStr);
	}
	/**
	 * 全量覆盖部门
	 * @param data
	 * {
			"media_id":"xxxxxx",
			"callback":
			{
			 	"url": "xxx",
			 	"token": "xxx",
			 	"encodingaeskey": "xxx"
			}
		}
	 * @return
	 */
	public static ApiResult batchReplaceParty(String data){
		String jsonStr = HttpUtils.post(batchReplacePartyUrl.replace("ACCESS_TOKEN", AccessTokenApi.getAccessTokenStr()), data);
		return new ApiResult(jsonStr);
	}
	/**
	 * 获取异步任务结果
	 * @param jobId
	 * @return
	 */
	public static ApiResult batchGetResult(String jobId){
		String jsonStr = HttpUtils.get(batchGetResultUrl.replace("ACCESS_TOKEN", AccessTokenApi.getAccessTokenStr()).replace("JOBID", jobId));
		return new ApiResult(jsonStr);
	}
}
