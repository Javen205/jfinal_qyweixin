/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.qyweixin.sdk.msg.out;

import com.jfinal.qyweixin.sdk.msg.in.InMsg;

/**
 * <pre>
    回复图片消息
 &lt;xml&gt;
 &lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;
 &lt;FromUserName&gt;&lt;![CDATA[fromUser]]&gt;&lt;/FromUserName&gt;
 &lt;CreateTime&gt;12345678&lt;/CreateTime&gt;
 &lt;MsgType&gt;&lt;![CDATA[image]]&gt;&lt;/MsgType&gt;
 &lt;Image&gt;
 &lt;MediaId&gt;&lt;![CDATA[media_id]]&gt;&lt;/MediaId&gt;
 &lt;/Image&gt;
 &lt;/xml&gt;
 </pre>
 */
@SuppressWarnings("serial")
public class OutImageMsg extends OutMsg {

    private String mediaId;

    public OutImageMsg() {
        this.msgType = "image";
    }

    public OutImageMsg(InMsg inMsg) {
        super(inMsg);
        this.msgType = "image";
    }

    @Override
    protected void subXml(StringBuilder sb) {
        if (null == mediaId) {
            throw new NullPointerException("mediaId is null");
        }
        sb.append("<Image>\n");
        sb.append("<MediaId><![CDATA[").append(mediaId).append("]]></MediaId>\n");
        sb.append("</Image>\n");
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

}



