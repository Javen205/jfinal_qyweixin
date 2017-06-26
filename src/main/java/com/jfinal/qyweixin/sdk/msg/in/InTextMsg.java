/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.qyweixin.sdk.msg.in;

/**
 * <pre>
    接收文本消息
 &lt;xml&gt;
 &lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;
 &lt;FromUserName&gt;&lt;![CDATA[fromUser]]&gt;&lt;/FromUserName&gt;
 &lt;CreateTime&gt;1348831860&lt;/CreateTime&gt;
 &lt;MsgType&gt;&lt;![CDATA[text]]&gt;&lt;/MsgType&gt;
 &lt;Content&gt;&lt;![CDATA[this is a test]]&gt;&lt;/Content&gt;
 &lt;MsgId&gt;1234567890123456&lt;/MsgId&gt;
 &lt;/xml&gt;
 </pre>
 */
@SuppressWarnings("serial")
public class InTextMsg extends InMsg {

    private String content;
    private String msgId;

    public InTextMsg(String toUserName, String fromUserName, Integer createTime, String msgType, String agentId) {
        super(toUserName, fromUserName, createTime, msgType, agentId);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

}




