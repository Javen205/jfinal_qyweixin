package com.jfinal.qyweixin.sdk.msg.send;

/**
 *视频消息
 * @author Javen
 *
 */
public class QiYeVideoMsg extends QiYeBaseMsg{
	private String media_id;
	private String title;
	private String description;
	
	public String getMedia_id() {
		return media_id;
	}
	public void setMedia_id(String mediaId) {
		media_id = mediaId;
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
	public QiYeVideoMsg(String mediaId, String title, String description) {
		this.media_id = mediaId;
		this.title = title;
		this.description = description;
		this.msgtype=MessageType.video.name();
	}
}
