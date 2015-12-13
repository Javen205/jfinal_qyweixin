package com.jfinal.qy.weixin.sdk.msg.send;

/**
 * 多图文混排对象
 * 
 * @author Javen
 * 
 */
public class MpArticle {
	/**
	 * 图文消息缩略图的media_id, 可以在上传多媒体文件接口（6.1）中获得。此处thumb_media_id 即上传接口返回的
	 */
	private String thumb_media_id;
	/**
	 * 图文消息的作者
	 */
	private String author;
	/**
	 * 图文消息点击“阅读原文”之后的页面链接
	 */
	private String content_source_url;
	/**
	 * 图文消息的标题
	 */
	private String title;
	/**
	 * 图文消息的内容，支持 html标签
	 */
	private String content;

	/**
	 * 图文消息的描述
	 */
	private String digest;
	/**
	 * 是否显示封面，1 为显示，0 为不显示
	 */
	private int show_cover_pic;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}


	public String getThumb_media_id() {
		return thumb_media_id;
	}

	public void setThumb_media_id(String thumbMediaId) {
		thumb_media_id = thumbMediaId;
	}

	public String getContent_source_url() {
		return content_source_url;
	}

	public void setContent_source_url(String contentSourceUrl) {
		content_source_url = contentSourceUrl;
	}

	public int getShow_cover_pic() {
		return show_cover_pic;
	}

	public void setShow_cover_pic(int showCoverPic) {
		show_cover_pic = showCoverPic;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
