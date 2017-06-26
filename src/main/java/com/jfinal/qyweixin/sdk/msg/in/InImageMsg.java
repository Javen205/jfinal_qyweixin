/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.qyweixin.sdk.msg.in;

/**
 * <pre>
    接收图片消息
 &lt;xml&gt;
 &lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;
 &lt;FromUserName&gt;&lt;![CDATA[fromUser]]&gt;&lt;/FromUserName&gt;
 &lt;CreateTime&gt;1348831860&lt;/CreateTime&gt;
 &lt;MsgType&gt;&lt;![CDATA[image]]&gt;&lt;/MsgType&gt;
 &lt;PicUrl&gt;&lt;![CDATA[this is a url]]&gt;&lt;/PicUrl&gt;
 &lt;MediaId&gt;&lt;![CDATA[media_id]]&gt;&lt;/MediaId&gt;
 &lt;MsgId&gt;1234567890123456&lt;/MsgId&gt;
 &lt;/xml&gt;
 </pre>
*/
@SuppressWarnings("serial")
public class InImageMsg extends InMsg {

    private String picUrl;
    private String mediaId;
    private String msgId;

    public InImageMsg(String toUserName, String fromUserName, Integer createTime, String msgType, String agentId) {
        super(toUserName, fromUserName, createTime, msgType, agentId);
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
}





