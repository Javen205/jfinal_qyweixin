/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.qyweixin.sdk.msg.out;

import java.io.Serializable;

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
public class News implements Serializable {
    private String title;        // 不是必须
    private String description;    // 不是必须
    private String picUrl;        // 不是必须
    private String url;            // 不是必须

    public News(String title, String description, String picUrl, String url) {
        this.title = title;
        this.description = description;
        this.picUrl = picUrl;
        this.url = url;
    }

    public News() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
