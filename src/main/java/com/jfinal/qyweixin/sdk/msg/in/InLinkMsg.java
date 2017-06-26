/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.qyweixin.sdk.msg.in;

/**
 * <pre>
    接收链接消息
 &lt;xml&gt;
 &lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;
 &lt;FromUserName&gt;&lt;![CDATA[fromUser]]&gt;&lt;/FromUserName&gt;
 &lt;CreateTime&gt;1351776360&lt;/CreateTime&gt;
 &lt;MsgType&gt;&lt;![CDATA[link]]&gt;&lt;/MsgType&gt;
 &lt;Title&gt;&lt;![CDATA[公众平台官网链接]]&gt;&lt;/Title&gt;
 &lt;Description&gt;&lt;![CDATA[公众平台官网链接]]&gt;&lt;/Description&gt;
 &lt;Url&gt;&lt;![CDATA[url]]&gt;&lt;/Url&gt;
 &lt;MsgId&gt;1234567890123456&lt;/MsgId&gt;
 &lt;/xml&gt;
 </pre>
*/
@SuppressWarnings("serial")
public class InLinkMsg extends InMsg {

    private String title;
    private String description;
    private String url;
    private String msgId;

    public InLinkMsg(String toUserName, String fromUserName, Integer createTime, String msgType, String agentId) {
        super(toUserName, fromUserName, createTime, msgType, agentId);
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
}



