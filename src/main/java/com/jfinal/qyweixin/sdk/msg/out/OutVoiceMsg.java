/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.qyweixin.sdk.msg.out;

import com.jfinal.qyweixin.sdk.msg.in.InMsg;

/**
 * <pre>
    回复语音消息
 &lt;xml&gt;
 &lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;
 &lt;FromUserName&gt;&lt;![CDATA[fromUser]]&gt;&lt;/FromUserName&gt;
 &lt;CreateTime&gt;12345678&lt;/CreateTime&gt;
 &lt;MsgType&gt;&lt;![CDATA[voice]]&gt;&lt;/MsgType&gt;
 &lt;Voice&gt;
 &lt;MediaId&gt;&lt;![CDATA[media_id]]&gt;&lt;/MediaId&gt;
 &lt;/Voice&gt;
 &lt;/xml&gt;
 </pre>
 */
@SuppressWarnings("serial")
public class OutVoiceMsg extends OutMsg {

    private String mediaId;

    public OutVoiceMsg() {
        this.msgType = "voice";
    }

    public OutVoiceMsg(InMsg inMsg) {
        super(inMsg);
        this.msgType = "voice";
    }

    @Override
    protected void subXml(StringBuilder sb) {
        if (null == mediaId) {
            throw new NullPointerException("mediaId is null");
        }
        sb.append("<Voice>\n");
        sb.append("<MediaId><![CDATA[").append(mediaId).append("]]></MediaId>\n");
        sb.append("</Voice>\n");
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

}













