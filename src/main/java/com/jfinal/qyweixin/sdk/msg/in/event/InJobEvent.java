/**
 * Copyright (c) 2011-2015, Javen  (javenlife@126.com).
 *
 * Licensed under the Apache License, Version 1.0 (the "License");
 */
package com.jfinal.qyweixin.sdk.msg.in.event;

/**
 * @author Javen
 * 2015年12月12日
 * 
 * <xml>
	    <ToUserName><![CDATA[wx28dbb14e37208abe]]></ToUserName>
		<FromUserName><![CDATA[FromUser]]></FromUserName>
		<CreateTime>1425284517</CreateTime>
		<MsgType><![CDATA[event]]></MsgType>
		<Event><![CDATA[batch_job_result]]></Event>
		<BatchJob>
			<JobId><![CDATA[S0MrnndvRG5fadSlLwiBqiDDbM143UqTmKP3152FZk4]]></JobId>
			<JobType><![CDATA[sync_user]]></JobType>
			<ErrCode>0</ErrCode>
			<ErrMsg><![CDATA[ok]]></ErrMsg>
		</BatchJob>
	</xml>
 */
public class InJobEvent extends EventInMsg {
	private BatchJob batchJob;
	public InJobEvent(String toUserName, String fromUserName, Integer createTime, String msgType, String event,
			String agentId) {
		super(toUserName, fromUserName, createTime, msgType, event, agentId);
	}
	public BatchJob getBatchJob() {
		return batchJob;
	}
	public void setBatchJob(BatchJob batchJob) {
		this.batchJob = batchJob;
	}

}
