/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.qyweixin.sdk.msg.in;

/**
 * <pre>
    接收地理位置消息
 &lt;xml&gt;
 &lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;
 &lt;FromUserName&gt;&lt;![CDATA[fromUser]]&gt;&lt;/FromUserName&gt;
 &lt;CreateTime&gt;1351776360&lt;/CreateTime&gt;
 &lt;MsgType&gt;&lt;![CDATA[location]]&gt;&lt;/MsgType&gt;
 &lt;Location_X&gt;23.134521&lt;/Location_X&gt;
 &lt;Location_Y&gt;113.358803&lt;/Location_Y&gt;
 &lt;Scale&gt;20&lt;/Scale&gt;
 &lt;Label&gt;&lt;![CDATA[位置信息]]&gt;&lt;/Label&gt;
 &lt;MsgId&gt;1234567890123456&lt;/MsgId&gt;
 &lt;/xml&gt;
 </pre>
*/
@SuppressWarnings("serial")
public class InLocationMsg extends InMsg {
    private String location_X;
    private String location_Y;
    private String scale;
    private String label;
    private String msgId;

    public InLocationMsg(String toUserName, String fromUserName, Integer createTime, String msgType, String agentId) {
        super(toUserName, fromUserName, createTime, msgType, agentId);
    }

    public String getLocation_X() {
        return location_X;
    }

    public void setLocation_X(String location_X) {
        this.location_X = location_X;
    }

    public String getLocation_Y() {
        return location_Y;
    }

    public void setLocation_Y(String location_Y) {
        this.location_Y = location_Y;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
}




