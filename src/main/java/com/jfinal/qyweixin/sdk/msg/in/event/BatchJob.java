/**
 * Copyright (c) 2011-2015, Javen  (javenlife@126.com).
 *
 * Licensed under the Apache License, Version 1.0 (the "License");
 */
package com.jfinal.qyweixin.sdk.msg.in.event;

/**
 * @author Javen
 * 2015年12月12日
 */
public class BatchJob {
	//异步任务id，最大长度为64字符
	private String jobId;
	/**
	 * 操作类型，字符串，目前分别有：
			1. sync_user(增量更新成员)
			2. replace_user(全量覆盖成员)
			3. invite_user(邀请成员关注)
			4. replace_party(全量覆盖部门)
	 */
	private String jobType;
	private String errorCode;
	private String errMsg;
	
	
	
	public BatchJob(String jobId, String jobType, String errorCode, String errMsg) {
		this.jobId = jobId;
		this.jobType = jobType;
		this.errorCode = errorCode;
		this.errMsg = errMsg;
	}
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
}
