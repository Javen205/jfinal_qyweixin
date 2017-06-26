/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.qyweixin.sdk.msg.out;

import com.jfinal.qyweixin.sdk.msg.in.InMsg;

/**
 * <pre>
    回复音乐消息
 &lt;xml&gt;
 &lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;
 &lt;FromUserName&gt;&lt;![CDATA[fromUser]]&gt;&lt;/FromUserName&gt;
 &lt;CreateTime&gt;12345678&lt;/CreateTime&gt;
 &lt;MsgType&gt;&lt;![CDATA[music]]&gt;&lt;/MsgType&gt;
 &lt;Music&gt;
 &lt;Title&gt;&lt;![CDATA[TITLE]]&gt;&lt;/Title&gt;
 &lt;Description&gt;&lt;![CDATA[DESCRIPTION]]&gt;&lt;/Description&gt;
 &lt;MusicUrl&gt;&lt;![CDATA[MUSIC_Url]]&gt;&lt;/MusicUrl&gt;
 &lt;HQMusicUrl&gt;&lt;![CDATA[HQ_MUSIC_Url]]&gt;&lt;/HQMusicUrl&gt;
 // 官司方文档错误，无此标记: "&lt;ThumbMediaId&gt;&lt;![CDATA[${__msg.thumbMediaId}]]&gt;&lt;/ThumbMediaId&gt;\n" +
 "&lt;FuncFlag&gt;${__msg.funcFlag}&lt;/FuncFlag&gt;\n" +
 &lt;/Music&gt;
 &lt;/xml&gt;
 </pre>
*/
@SuppressWarnings("serial")
public class OutMusicMsg extends OutMsg {

    private String title;        // 不是必须
    private String description;    // 不是必须
    private String musicUrl;    // 不是必须
    private String hqMusicUrl;    // 不是必须
    // private String thumbMediaId;    // 官方文档有误，无此属性
    private String funcFlag = "0";

    public OutMusicMsg() {
        this.msgType = "music";
    }

    public OutMusicMsg(InMsg inMsg) {
        super(inMsg);
        this.msgType = "music";
    }

    @Override
    protected void subXml(StringBuilder sb) {
        sb.append("<Music>\n");
        sb.append("<Title><![CDATA[").append(nullToBlank(title)).append("]]></Title>\n");
        sb.append("<Description><![CDATA[").append(nullToBlank(description)).append("]]></Description>\n");
        sb.append("<MusicUrl><![CDATA[").append(nullToBlank(musicUrl)).append("]]></MusicUrl>\n");
        sb.append("<HQMusicUrl><![CDATA[").append(nullToBlank(hqMusicUrl)).append("]]></HQMusicUrl>\n");
        sb.append("<FuncFlag>").append(funcFlag).append("</FuncFlag>\n");
        sb.append("</Music>\n");
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

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public String getHqMusicUrl() {
        return hqMusicUrl;
    }

    public void setHqMusicUrl(String hqMusicUrl) {
        this.hqMusicUrl = hqMusicUrl;
    }

    public String getFuncFlag() {
        return funcFlag;
    }

    // 设置为星标
    public void setFuncFlag(boolean funcFlag) {
        this.funcFlag = funcFlag ? "1" : "0";
    }

    /* 官方文档有误，无此属性
    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }*/
}






