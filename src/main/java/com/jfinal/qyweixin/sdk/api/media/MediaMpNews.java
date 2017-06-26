/**
 * Copyright (c) 2011-2015, Javen  (javenlife@126.com).
 *
 * Licensed under the Apache License, Version 1.0 (the "License");
 */
package com.jfinal.qyweixin.sdk.api.media;


/**
 * @author Javen
 * 2015年12月13日
 * 
 * 上传永久图文素材实体
 */
public class MediaMpNews {
	private String agentid;
	private MpNews mpNews;
	//修改永久图文素材
	private String media_id;
	
	public String getAgentid() {
		return agentid;
	}

	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}

	public MpNews getMpNews() {
		return mpNews;
	}

	public void setMpNews(MpNews mpNews) {
		this.mpNews = mpNews;
	}

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	
	
}
