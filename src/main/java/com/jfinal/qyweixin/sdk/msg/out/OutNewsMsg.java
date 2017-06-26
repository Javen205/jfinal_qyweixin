/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.qyweixin.sdk.msg.out;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.qyweixin.sdk.msg.in.InMsg;

/**
 * <pre>
    回复图文消息
 &lt;xml&gt;
 &lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;
 &lt;FromUserName&gt;&lt;![CDATA[fromUser]]&gt;&lt;/FromUserName&gt;
 &lt;CreateTime&gt;12345678&lt;/CreateTime&gt;
 &lt;MsgType&gt;&lt;![CDATA[news]]&gt;&lt;/MsgType&gt;
 &lt;ArticleCount&gt;2&lt;/ArticleCount&gt;
 &lt;Articles&gt;
 &lt;item&gt;
 &lt;Title&gt;&lt;![CDATA[title1]]&gt;&lt;/Title&gt;
 &lt;Description&gt;&lt;![CDATA[description1]]&gt;&lt;/Description&gt;
 &lt;PicUrl&gt;&lt;![CDATA[picurl]]&gt;&lt;/PicUrl&gt;
 &lt;Url&gt;&lt;![CDATA[url]]&gt;&lt;/Url&gt;
 &lt;/item&gt;

 &lt;item&gt;
 &lt;Title&gt;&lt;![CDATA[title]]&gt;&lt;/Title&gt;
 &lt;Description&gt;&lt;![CDATA[description]]&gt;&lt;/Description&gt;
 &lt;PicUrl&gt;&lt;![CDATA[picurl]]&gt;&lt;/PicUrl&gt;
 &lt;Url&gt;&lt;![CDATA[url]]&gt;&lt;/Url&gt;
 &lt;/item&gt;
 &lt;/Articles&gt;
 &lt;/xml&gt;
 </pre>
 */
@SuppressWarnings("serial")
public class OutNewsMsg extends OutMsg {

    private List<News> articles = new ArrayList<News>();

    public OutNewsMsg() {
        this.msgType = "news";
    }

    public OutNewsMsg(InMsg inMsg) {
        super(inMsg);
        this.msgType = "news";
    }

    @Override
    protected void subXml(StringBuilder sb) {
        sb.append("<ArticleCount>").append(getArticleCount()).append("</ArticleCount>\n");
        sb.append("<Articles>\n");
        for (News x : articles) {
            sb.append("<item>\n");

            sb.append("<Title><![CDATA[").append(nullToBlank(x.getTitle())).append("]]></Title>\n");
            sb.append("<Description><![CDATA[").append(nullToBlank(x.getDescription())).append("]]></Description>\n");
            sb.append("<PicUrl><![CDATA[").append(nullToBlank(x.getPicUrl())).append("]]></PicUrl>\n");
            sb.append("<Url><![CDATA[").append(nullToBlank(x.getUrl())).append("]]></Url>\n");

            sb.append("</item>\n");
        }
        sb.append("</Articles>\n");
    }

    public Integer getArticleCount() {
        return articles.size();
    }

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








