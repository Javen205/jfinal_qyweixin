/**
 * Copyright (c) 2015-2016, Javen  (javenlife@126.com).
 *
 * Licensed under the Apache License, Version 1.0 (the "License");
 */
package com.jfinal.qyweixin.sdk.msg.chat;

/**
 * @author Javen
 * 2016年1月9日
 */
public class VocieChat extends ChatBase {
	private VoiceChatMsg voice;

	public VoiceChatMsg getVoice() {
		return voice;
	}

	public void setVoice(VoiceChatMsg voice) {
		this.voice = voice;
		this.setMsgtype("voice");
	}
	
}
