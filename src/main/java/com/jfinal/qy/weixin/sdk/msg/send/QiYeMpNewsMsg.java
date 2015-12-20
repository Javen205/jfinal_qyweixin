package com.jfinal.qy.weixin.sdk.msg.send;

import java.util.List;

/**
 * 多图文混排消息
 * 
 * @author Javen
 *
 */
public class QiYeMpNewsMsg extends QiYeBaseMsg {

	/**
	 * 图文消息个数，限制为10条以内
	 */
	private int mpArticleCount;
	/**
	 * 多条图文消息信息，默认第一个item为大图
	 */
	private List<MpArticle> articles;

	public int getMpArticleCount() {
		return mpArticleCount;
	}

	public void setMpArticleCount(int mpArticleCount) {
		this.mpArticleCount = mpArticleCount;
	}

	public List<MpArticle> getArticles() {
		return articles;
	}

	public void setArticles(List<MpArticle> articles) {
		this.articles = articles;
	}

	public QiYeMpNewsMsg() {
		this.msgtype=MessageType.mpnews.name();
	}

}
