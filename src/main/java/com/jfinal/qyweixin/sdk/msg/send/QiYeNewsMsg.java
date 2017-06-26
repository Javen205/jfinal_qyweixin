package com.jfinal.qyweixin.sdk.msg.send;

/**
 * 图文混排的消息
 * 
 * @author Javen
 * 
 */
public class QiYeNewsMsg extends QiYeBaseMsg {
	/**
	 * 图文消息个数，限制为10条以内
	 */
	private int ArticleCount;
	/**
	 * 多条图文消息信息，默认第一个item为大图
	 */
	private News news;


	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}


	public QiYeNewsMsg(int articleCount,News news) {
		this.msgtype=MessageType.news.name();
		ArticleCount = articleCount;
		this.news = news;
	}

	

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public QiYeNewsMsg() {
		this.msgtype=MessageType.news.name();
	}
}
