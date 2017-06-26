/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.qyweixin.sdk.msg.in.speech_recognition;

import com.jfinal.qyweixin.sdk.msg.in.InVoiceMsg;

/**
 * <pre>
    接收语音识别结果，与 InVoiceMsg 唯一的不同是多了一个 Recognition 标记
 &lt;xml&gt;
 &lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;
 &lt;FromUserName&gt;&lt;![CDATA[fromUser]]&gt;&lt;/FromUserName&gt;
 &lt;CreateTime&gt;1357290913&lt;/CreateTime&gt;
 &lt;MsgType&gt;&lt;![CDATA[voice]]&gt;&lt;/MsgType&gt;
 &lt;MediaId&gt;&lt;![CDATA[media_id]]&gt;&lt;/MediaId&gt;
 &lt;Format&gt;&lt;![CDATA[Format]]&gt;&lt;/Format&gt;
 &lt;Recognition&gt;&lt;![CDATA[腾讯微信团队]]&gt;&lt;/Recognition&gt;
 &lt;MsgId&gt;1234567890123456&lt;/MsgId&gt;
 &lt;/xml&gt;
 </pre>
 */
public class InSpeechRecognitionResults extends InVoiceMsg {

    private String recognition;

    public InSpeechRecognitionResults(String toUserName, String fromUserName, Integer createTime, String msgType ,String agentId) {
        super(toUserName, fromUserName, createTime, msgType,agentId);
    }

    public String getRecognition() {
        return recognition;
    }

    public void setRecognition(String recognition) {
        this.recognition = recognition;
    }

}
