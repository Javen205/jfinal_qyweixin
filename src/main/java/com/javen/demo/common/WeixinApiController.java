package com.javen.demo.common;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.jfinal.kit.PropKit;
import com.jfinal.qy.weixin.sdk.api.ApiConfig;
import com.jfinal.qy.weixin.sdk.api.ApiResult;
import com.jfinal.qy.weixin.sdk.api.SendMessageApi;
import com.jfinal.qy.weixin.sdk.jfinal.ApiController;
import com.jfinal.qy.weixin.sdk.msg.send.Article;
import com.jfinal.qy.weixin.sdk.msg.send.News;
import com.jfinal.qy.weixin.sdk.msg.send.QiYeNewsMsg;
import com.jfinal.qy.weixin.sdk.msg.send.QiYeTextMsg;
import com.jfinal.qy.weixin.sdk.msg.send.Text;

public class WeixinApiController extends ApiController {
	
	/**
	 * 如果要支持多公众账号，只需要在此返回各个公众号对应的  ApiConfig 对象即可
	 * 可以通过在请求 url 中挂参数来动态从数据库中获取 ApiConfig 属性值
	 */
	public ApiConfig getApiConfig() {
		ApiConfig ac = new ApiConfig();
		
		// 配置微信 API 相关常量
		ac.setToken(PropKit.get("token"));
		ac.setCorpId(PropKit.get("corpId"));
		ac.setSecret(PropKit.get("secret"));
				
		
		/**
		 *  是否对消息进行加密，对应于微信平台的消息加解密方式：
		 *  1：true进行加密且必须配置 encodingAesKey
		 *  2：false采用明文模式，同时也支持混合模式
		 */
		ac.setEncryptMessage(PropKit.getBoolean("encryptMessage", false));
		ac.setEncodingAesKey(PropKit.get("encodingAesKey", "setting it in config file"));
		return ac;
	}

	/**
	 * 发送文本消息
	 */
	public void sendTextMssage() {
		
		QiYeTextMsg text=new QiYeTextMsg();
		text.setAgentid("16");
		text.setText(new Text("测试消息"));
		text.setSafe("1");
		text.setTouser("@all");
		ApiResult sendTextMsg = SendMessageApi.sendTextMsg(text);
		renderText(sendTextMsg.getJson());
	}
	/**
	 * 图文混排的消息
	 */
	public void sendNewsMessage(){
		QiYeNewsMsg qiYeNewsMsg=new QiYeNewsMsg();
		qiYeNewsMsg.setAgentid("16");
		qiYeNewsMsg.setArticleCount(1);
		qiYeNewsMsg.setSafe("0");
		qiYeNewsMsg.setTouser("@all");
		qiYeNewsMsg.setMsgtype("news");
		
		News news=new News();
		List<Article> articles=new ArrayList<Article>();
		Article article=new Article();
		article.setTitle("注意有报警消息");
		article.setDescription("描述....");
		article.setPicurl("http://b.zol-img.com.cn/mobile_soft/ms_52/180x180/soXoN525ffVpg.png");
		article.setUrl("http://gb.cri.cn/mmsource/images/2007/06/08/re070608014.jpg");
		articles.add(article);
		news.setArticles(articles);
		qiYeNewsMsg.setNews(news);
		
		ApiResult sendTextMsg = SendMessageApi.sendNewsMsg(qiYeNewsMsg);
		renderText(sendTextMsg.getJson());
	}

	
}

