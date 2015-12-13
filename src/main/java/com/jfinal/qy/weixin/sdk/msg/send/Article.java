package com.jfinal.qy.weixin.sdk.msg.send;

/**
 * 多图文消息对象
 * 
 * @author Javen
 * 
 */
public class Article {
	/**
	 * 标题
	 */
	private String Title;
	/**
	 * 描述
	 */
	private String Description;
	/**
	 * 点击后跳转的链接。企业可根据 url里面带的 code参数校验用户的真实身份。具体参考“9 微信页面跳转用户身份查询”
	 */
	private String url;
	/**
	 * 图文消息的图片链接，支持 JPG、PNG格式，较好的效果为大图640*320，小图80*80。如不填，在客户端不显示图片
	 */
	private String picurl;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

}
