package com.jfinal.qyweixin.sdk.msg.send;

/**
 * 语音消息
 * @author Javen
 *
 */
public class QiYeVoiceMsg extends QiYeBaseMsg {
	/**
	 * 媒体资源文件 ID
	 */
	private String media_id;

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String mediaId) {
		media_id = mediaId;
	}

	public QiYeVoiceMsg(String mediaId) {
		media_id = mediaId;
		this.msgtype=MessageType.voice.name();
	}
	
}
