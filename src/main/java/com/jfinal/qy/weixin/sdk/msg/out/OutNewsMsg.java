
package com.jfinal.qy.weixin.sdk.msg.out;

import java.util.ArrayList;
import java.util.List;
import com.jfinal.qy.weixin.sdk.msg.in.InMsg;

/**
	回复图文消息
	<xml>
		<ToUserName><![CDATA[toUser]]></ToUserName>
		<FromUserName><![CDATA[fromUser]]></FromUserName>
		<CreateTime>12345678</CreateTime>
		<MsgType><![CDATA[news]]></MsgType>
			<ArticleCount>2</ArticleCount>
			<Articles>
				<item>
					<Title><![CDATA[title1]]></Title> 
					<Description><![CDATA[description1]]></Description>
					<PicUrl><![CDATA[picurl]]></PicUrl>
					<Url><![CDATA[url]]></Url>
				</item>
				
				<item>
					<Title><![CDATA[title]]></Title>
					<Description><![CDATA[description]]></Description>
					<PicUrl><![CDATA[picurl]]></PicUrl>
					<Url><![CDATA[url]]></Url>
				</item>
			</Articles>
	</xml> 
 */
public class OutNewsMsg extends OutMsg {
	public static final String TEMPLATE =
			"<xml>\n" +
			"<ToUserName><![CDATA[${__msg.toUserName}]]></ToUserName>\n" +
			"<FromUserName><![CDATA[${__msg.fromUserName}]]></FromUserName>\n" +
			"<CreateTime>${__msg.createTime}</CreateTime>\n" +
			"<MsgType><![CDATA[${__msg.msgType}]]></MsgType>\n" +
				"<ArticleCount>${__msg.getArticleCount()}</ArticleCount>\n" +
				"<Articles>\n" +
					"<#list __msg.getArticles() as x>\n"+
						"<item>\n" +
							"<Title><![CDATA[${(x.title)!}]]></Title>\n" + 
							"<Description><![CDATA[${(x.description)!}]]></Description>\n" +
							"<PicUrl><![CDATA[${(x.picUrl)!}]]></PicUrl>\n" +
							"<Url><![CDATA[${(x.url)!}]]></Url>\n" +
						"</item>\n" +
					"</#list>\n" +
				"</Articles>\n" +
			"</xml>";
	
	// private Integer articleCount;
	private List<News> articles = new ArrayList<News>();
	
	public OutNewsMsg() {
		this.msgType = "news";
	}
	
	public OutNewsMsg(InMsg inMsg) {
		super(inMsg);
		this.msgType = "news";
	}
	
	public Integer getArticleCount() {
		// return articleCount;
		return articles.size();
	}
	
//	public void setArticleCount(Integer articleCount) {
//		this.articleCount = articleCount;
//	}
	
	public List<News> getArticles() {
		return articles;
	}
	
	public void setArticles(List<News> articles) {
		if (articles != null)
			this.articles = articles;
	}
	
	public OutNewsMsg addNews(List<News> articles) {
		if (articles != null)
			this.articles.addAll(articles);
		return this;
	}
	
	public OutNewsMsg addNews(String title, String description, String picUrl, String url) {
		this.articles.add(new News(title, description, picUrl, url));
		return this;
	}
	
	public OutNewsMsg addNews(News news) {
		this.articles.add(news);
		return this;
	}
}








