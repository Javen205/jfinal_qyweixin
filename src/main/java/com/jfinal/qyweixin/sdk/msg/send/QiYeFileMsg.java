package com.jfinal.qyweixin.sdk.msg.send;

/**
 * 文件消息
 * @author Javen
 *
 */
public class QiYeFileMsg extends QiYeBaseMsg {
	private String media_id;

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String mediaId) {
		media_id = mediaId;
	}

	public QiYeFileMsg(String mediaId) {
		media_id = mediaId;
		this.msgtype=MessageType.file.name();
	}
	
	public QiYeFileMsg() {
		this.msgtype=MessageType.file.name();
	}
}
