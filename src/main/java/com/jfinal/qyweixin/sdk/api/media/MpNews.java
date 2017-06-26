/**
 * Copyright (c) 2011-2015, Javen  (javenlife@126.com).
 *
 * Licensed under the Apache License, Version 1.0 (the "License");
 */
package com.jfinal.qyweixin.sdk.api.media;

import java.util.List;

/**
 * @author Javen
 * 2015年12月13日
 * 图文消息，一个图文消息支持1到10个图文
 */
public class MpNews {
	private List<MediaArticles> articles;

	public List<MediaArticles> getArticles() {
		return articles;
	}

	public void setArticles(List<MediaArticles> articles) {
		this.articles = articles;
	}
	
	
}
