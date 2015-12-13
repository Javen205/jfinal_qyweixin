# jfinal_qyweixin 企业微信号极速开发

jfinal_qyweixin是基于 JFinal 的微信公众号极速开发 SDK，只需浏览 Demo 代码即可进行极速开发，此项目是由 JFinal Weixin 1.5 版本修改而来。

#1、QyWeiXinConfig配置

```java
public class QyWeiXinConfig extends JFinalConfig{

	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		// 加载少量必要配置，随后可用PropKit.get(...)获取值
		PropKit.use("a_little_config.txt");
		me.setDevMode(PropKit.getBoolean("devMode", false));
	}
	
	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
		me.add("/", IndexController.class,"index");	// 第三个参数为该Controller的视图存放路径
		me.add("/qymsg", WeixinMsgController.class);
		me.add("/api", WeixinApiController.class);
	}
	
	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		/*// 配置C3p0数据库连接池插件
		C3p0Plugin c3p0Plugin = new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
		me.add(c3p0Plugin);
		
		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		me.add(arp);
		arp.addMapping("blog", Blog.class);	// 映射blog 表到 Blog模型*/
	}
	
	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
		
	}
	
	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {
		
	}
	
	/**
	 * 建议使用 JFinal 手册推荐的方式启动项目
	 * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
	 */
	public static void main(String[] args) {
		JFinal.start("src/main/webapp", 8080, "/", 5);//启动配置项
	}

}

```
#2、WeixinMsgController
通过继承自 MsgController 便拥有了接收消息和发送消息的便利方法
```Java

package com.javen.demo.common;

import com.jfinal.kit.PropKit;
import com.jfinal.log.Logger;
import com.jfinal.qy.weixin.sdk.api.ApiConfig;
import com.jfinal.qy.weixin.sdk.jfinal.MsgController;
import com.jfinal.qy.weixin.sdk.msg.in.InImageMsg;
import com.jfinal.qy.weixin.sdk.msg.in.InLocationMsg;
import com.jfinal.qy.weixin.sdk.msg.in.InShortVideoMsg;
import com.jfinal.qy.weixin.sdk.msg.in.InTextMsg;
import com.jfinal.qy.weixin.sdk.msg.in.InVideoMsg;
import com.jfinal.qy.weixin.sdk.msg.in.InVoiceMsg;
import com.jfinal.qy.weixin.sdk.msg.in.event.InEnterAgentEvent;
import com.jfinal.qy.weixin.sdk.msg.in.event.InFollowEvent;
import com.jfinal.qy.weixin.sdk.msg.in.event.InJobEvent;
import com.jfinal.qy.weixin.sdk.msg.in.event.InLocationEvent;
import com.jfinal.qy.weixin.sdk.msg.in.event.InMenuEvent;
import com.jfinal.qy.weixin.sdk.msg.in.event.InQrCodeEvent;
import com.jfinal.qy.weixin.sdk.msg.in.event.ScanCodeInfo;
import com.jfinal.qy.weixin.sdk.msg.out.OutImageMsg;
import com.jfinal.qy.weixin.sdk.msg.out.OutNewsMsg;
import com.jfinal.qy.weixin.sdk.msg.out.OutTextMsg;
import com.jfinal.qy.weixin.sdk.msg.out.OutVoiceMsg;

/**
 * 将此 DemoController 在YourJFinalConfig 中注册路由，
 * 并设置好weixin开发者中心的 URL 与 token ，使 URL 指向该
 * DemoController 继承自父类 WeixinController 的 index
 * 方法即可直接运行看效果，在此基础之上修改相关的方法即可进行实际项目开发
 */
public class WeixinMsgController extends MsgController {

	static Logger logger = Logger.getLogger(WeixinMsgController.class);
	private static final String helpStr = "\t发送 help 可获得帮助，发送\"视频\" 可获取视频教程，发送 \"美女\" 可看美女，发送 music 可听音乐 ，发送新闻可看JFinal新版本消息。公众号功能持续完善中";

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
		ac.setEncryptMessage(PropKit.getBoolean("encryptMessage", true));
		ac.setEncodingAesKey(PropKit.get("encodingAesKey", "setting it in config file"));
		return ac;
	}

	/**
	 * 实现父类抽方法，处理文本消息
	 * 本例子中根据消息中的不同文本内容分别做出了不同的响应，同时也是为了测试 jfinal weixin sdk的基本功能：
	 *     本方法仅测试了 OutTextMsg、OutNewsMsg、OutMusicMsg 三种类型的OutMsg，
	 *     其它类型的消息会在随后的方法中进行测试
	 */
	@Override
	protected void processInTextMsg(InTextMsg inTextMsg) {
		String msgContent = inTextMsg.getContent().trim();
		System.out.println("收到的信息："+msgContent);
		// 帮助提示
		if ("help".equalsIgnoreCase(msgContent) || "帮助".equals(msgContent)) {
			OutTextMsg outMsg = new OutTextMsg(inTextMsg);
			outMsg.setContent(helpStr);
			render(outMsg);
		}
		// 图文消息测试
		else if ("news".equalsIgnoreCase(msgContent) || "新闻".equalsIgnoreCase(msgContent)) {
			OutNewsMsg outMsg = new OutNewsMsg(inTextMsg);
			outMsg.addNews("JFinal 2.0 发布,JAVA 极速 WEB+ORM 框架", "本星球第一个极速开发框架", "https://mmbiz.qlogo.cn/mmbiz/KJoUl0sqZFS0fRW68poHoU3v9ulTWV8MgKIduxmzHiamkb3yHia8pCicWVMCaFRuGGMnVOPrrj2qM13u9oTahfQ9A/0?wx_fmt=png", "http://mp.weixin.qq.com/s?__biz=MzA4NjM4Mjk2Mw==&mid=211063163&idx=1&sn=87d54e2992237a3f791f08b5cdab7990#rd");
			outMsg.addNews("JFinal 1.8 发布,JAVA 极速 WEB+ORM 框架", "现在就加入 JFinal 极速开发世界，节省更多时间去跟女友游山玩水 ^_^", "http://mmbiz.qpic.cn/mmbiz/zz3Q6WSrzq1ibBkhSA1BibMuMxLuHIvUfiaGsK7CC4kIzeh178IYSHbYQ5eg9tVxgEcbegAu22Qhwgl5IhZFWWXUw/0", "http://mp.weixin.qq.com/s?__biz=MjM5ODAwOTU3Mg==&mid=200313981&idx=1&sn=3bc5547ba4beae12a3e8762ababc8175#rd");
			outMsg.addNews("JFinal 1.6 发布,JAVA 极速 WEB+ORM 框架", "JFinal 1.6 主要升级了 ActiveRecord 插件，本次升级全面支持多数源、多方言、多缓", "http://mmbiz.qpic.cn/mmbiz/zz3Q6WSrzq0fcR8VmNCgugHXv7gVlxI6w95RBlKLdKUTjhOZIHGSWsGvjvHqnBnjIWHsicfcXmXlwOWE6sb39kA/0", "http://mp.weixin.qq.com/s?__biz=MjM5ODAwOTU3Mg==&mid=200121522&idx=1&sn=ee24f352e299b2859673b26ffa4a81f6#rd");
			render(outMsg);
		}
		
		else if ("美女".equalsIgnoreCase(msgContent)) {
			OutNewsMsg outMsg = new OutNewsMsg(inTextMsg);
			outMsg.addNews(
					"JFinal 宝贝更新喽",
					"jfinal 宝贝更新喽，我们只看美女 ^_^",
					"https://mmbiz.qlogo.cn/mmbiz/KJoUl0sqZFRHa3VrmibqAXRfYPNdiamFnpPTOvXoxsFlRoOHbVibGhmHOEUQiboD3qXWszKuzWpibFxsVW1RmNB9hPw/0?wx_fmt=jpeg",
					"http://mp.weixin.qq.com/s?__biz=MzA4NjM4Mjk2Mw==&mid=211356950&idx=1&sn=6315a1a2848aa8cb0694bf1f4accfb07#rd");
			// outMsg.addNews("秀色可餐", "JFinal Weixin 极速开发就是这么爽，有木有 ^_^", "http://mmbiz.qpic.cn/mmbiz/zz3Q6WSrzq2GJLC60ECD7rE7n1cvKWRNFvOyib4KGdic3N5APUWf4ia3LLPxJrtyIYRx93aPNkDtib3ADvdaBXmZJg/0", "http://mp.weixin.qq.com/s?__biz=MjM5ODAwOTU3Mg==&mid=200987822&idx=1&sn=7eb2918275fb0fa7b520768854fb7b80#rd");

			render(outMsg);
		}
		else if ("视频教程".equalsIgnoreCase(msgContent) || "视频".equalsIgnoreCase(msgContent)) {
			renderOutTextMsg("\thttp://pan.baidu.com/s/1nt2zAT7  \t密码:824r");
		}
		// 其它文本消息直接返回原值 + 帮助提示
		else {
			renderOutTextMsg("\t文本消息已成功接收，内容为： " + inTextMsg.getContent() + "\n\n" + helpStr);
		}
	}

	/**
	 * 实现父类抽方法，处理图片消息
	 */
	@Override
	protected void processInImageMsg(InImageMsg inImageMsg) {
		OutImageMsg outMsg = new OutImageMsg(inImageMsg);
		// 将刚发过来的图片再发回去
		outMsg.setMediaId(inImageMsg.getMediaId());
		render(outMsg);
		
	}

	/**
	 * 实现父类抽方法，处理语音消息
	 */
	protected void processInVoiceMsg(InVoiceMsg inVoiceMsg) {
		OutVoiceMsg outMsg = new OutVoiceMsg(inVoiceMsg);
		// 将刚发过来的语音再发回去
		outMsg.setMediaId(inVoiceMsg.getMediaId());
		render(outMsg);
	}

	/**
	 * 实现父类抽方法，处理视频消息
	 */
	protected void processInVideoMsg(InVideoMsg inVideoMsg) {
		/* 腾讯 api 有 bug，无法回复视频消息，暂时回复文本消息代码测试
		OutVideoMsg outMsg = new OutVideoMsg(inVideoMsg);
		outMsg.setTitle("OutVideoMsg 发送");
		outMsg.setDescription("刚刚发来的视频再发回去");
		// 将刚发过来的视频再发回去，经测试证明是腾讯官方的 api 有 bug，待 api bug 却除后再试
		outMsg.setMediaId(inVideoMsg.getMediaId());
		render(outMsg);
		*/
		OutTextMsg outMsg = new OutTextMsg(inVideoMsg);
		outMsg.setContent("\t视频消息已成功接收，该视频的 mediaId 为: " + inVideoMsg.getMediaId());
		render(outMsg);
	}

	@Override
	protected void processInShortVideoMsg(InShortVideoMsg inShortVideoMsg)
	{
		OutTextMsg outMsg = new OutTextMsg(inShortVideoMsg);
		outMsg.setContent("\t视频消息已成功接收，该视频的 mediaId 为: " + inShortVideoMsg.getMediaId());
		render(outMsg);
	}

	/**
	 * 实现父类抽方法，处理地址位置消息
	 */
	protected void processInLocationMsg(InLocationMsg inLocationMsg) {
		OutTextMsg outMsg = new OutTextMsg(inLocationMsg);
		outMsg.setContent("已收到地理位置消息:" +
							"\nlocation_X = " + inLocationMsg.getLocation_X() +
							"\nlocation_Y = " + inLocationMsg.getLocation_Y() +
							"\nscale = " + inLocationMsg.getScale() +
							"\nlabel = " + inLocationMsg.getLabel());
		render(outMsg);
	}

	/**
	 * 实现父类抽方法，处理关注/取消关注消息
	 */
	protected void processInFollowEvent(InFollowEvent inFollowEvent) {
		if (InFollowEvent.EVENT_INFOLLOW_SUBSCRIBE.equals(inFollowEvent.getEvent()))
		{
			OutTextMsg outMsg = new OutTextMsg(inFollowEvent);
			outMsg.setContent("感谢关注 JFinal Weixin 极速开发企业号，为您节约更多时间，去陪恋人、家人和朋友 :) \n\n\n " + helpStr);
			render(outMsg);
		}// 如果为取消关注事件，将无法接收到传回的信息
		if (InFollowEvent.EVENT_INFOLLOW_UNSUBSCRIBE.equals(inFollowEvent.getEvent()))
		{
			logger.debug("取消关注：" + inFollowEvent.getFromUserName());
		}
	}
	/**
	 * 实现父类抽方法，处理上报地理位置事件
	 */
	protected void processInLocationEvent(InLocationEvent inLocationEvent) {
		OutTextMsg outMsg = new OutTextMsg(inLocationEvent);
		outMsg.setContent("processInLocationEvent() 方法测试成功\nLatitude:"
		+inLocationEvent.getLatitude()+"\n Longitude:"
		+inLocationEvent.getLongitude()+"\n Precision:"
		+inLocationEvent.getPrecision());
		render(outMsg);
	}

	/**
	 * 实现父类抽方法，处理自定义菜单事件
	 */
	protected void processInMenuEvent(InMenuEvent inMenuEvent) {
		OutTextMsg outMsg = new OutTextMsg(inMenuEvent);
		outMsg.setContent("菜单事件内容是：" + inMenuEvent.getEventKey());
		render(outMsg);
		if (InMenuEvent.EVENT_INMENU_CLICK.equals(inMenuEvent.getEvent())) {
			
		}
		
	}
	/**
	 * 实现父类抽方法，处理扫码推事件
	 */
	@Override
	protected void processInQrCodeEvent(InQrCodeEvent inQrCodeEvent) {
		ScanCodeInfo scanCodeInfo = inQrCodeEvent.getScanCodeInfo();
		String scanResult=scanCodeInfo.getScanResult();
		String scantype=scanCodeInfo.getScanType();
		OutTextMsg outMsg = new OutTextMsg(inQrCodeEvent);
		outMsg.setContent("处理扫码推事件\n 扫描结果:" + scanResult+"扫描类型:"+scantype);
		render(outMsg);
		
	}
	/**
	 * 实现父类抽方法，处理成员进入应用的事件
	 */
	@Override
	protected void processInEnterAgentEvent(InEnterAgentEvent inAgentEvent) {
		OutTextMsg outMsg = new OutTextMsg(inAgentEvent);
		outMsg.setContent("欢迎:"+inAgentEvent.getFromUserName()+"再次进入");
		render(outMsg);
	}

	/**
	 * 实现父类抽方法，处理异步任务完成事件
	 */
	@Override
	protected void processInJobEvent(InJobEvent inJobEvent) {
		OutTextMsg outMsg = new OutTextMsg(inJobEvent);
		outMsg.setContent("异步任务完成");
		render(outMsg);
	}
	

	
}
```

#3、WeixinApiController
通过调用 SendMessageApi、MediaApi 等 Api 的相关方法即可获取封装成 ApiResult 对象的结果，使用 render 系列方法即可快捷输出结果
```java
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


```
#4、帮助文档持续更新当中

#5、更多支持
* 微信开发交流群：148540125
* 关注微信公众号：javenlife

[![javenlife]](http://www.cnblogs.com/zyw-205520/)  
[javenlife]:http://7j1wfp.com1.z0.glb.clouddn.com/javenlife.jpg "关注微信公众号获得更多支持"


