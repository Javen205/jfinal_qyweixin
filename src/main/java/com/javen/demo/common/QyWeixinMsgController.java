
package com.javen.demo.common;

import com.jfinal.kit.PropKit;
import com.jfinal.log.Log;
import com.jfinal.qy.weixin.sdk.api.ApiConfig;
import com.jfinal.qy.weixin.sdk.api.ApiResult;
import com.jfinal.qy.weixin.sdk.api.ConBatchApi;
import com.jfinal.qy.weixin.sdk.api.SendMessageApi;
import com.jfinal.qy.weixin.sdk.jfinal.MsgController;
import com.jfinal.qy.weixin.sdk.msg.in.InImageMsg;
import com.jfinal.qy.weixin.sdk.msg.in.InLocationMsg;
import com.jfinal.qy.weixin.sdk.msg.in.InShortVideoMsg;
import com.jfinal.qy.weixin.sdk.msg.in.InTextMsg;
import com.jfinal.qy.weixin.sdk.msg.in.InVideoMsg;
import com.jfinal.qy.weixin.sdk.msg.in.InVoiceMsg;
import com.jfinal.qy.weixin.sdk.msg.in.event.BatchJob;
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
import com.jfinal.qy.weixin.sdk.msg.send.QiYeTextMsg;
import com.jfinal.qy.weixin.sdk.msg.send.Text;

/**
 * 将此 QyWeixinMsgController 在YourJFinalConfig 中注册路由，
 * 并设置好应用中心回调模式中的 URL 与 token 以及EncodingAESKey，使 URL 指向该
 * QyWeixinMsgController 继承自父类 MsgController 的 index
 * 方法即可直接运行看效果，在此基础之上修改相关的方法即可进行实际项目开发
 */
public class QyWeixinMsgController extends MsgController {

	static Log log =  Log.getLog(QyWeixinMsgController.class);
	private static final String helpStr = "\t发送 help 可获得帮助，发送 \"美女\" 可看美女 ，发送新闻可看新版本消息。公众号功能持续完善中";

	public ApiConfig getApiConfig() {
		ApiConfig ac = new ApiConfig();
		
		// 配置微信 API 相关常量
		ac.setToken(PropKit.get("token"));
		ac.setCorpId(PropKit.get("corpId"));
		ac.setCorpSecret(PropKit.get("secret"));
		
		/**
		 *  是否对消息进行加密，对应于微信平台的消息加解密方式：
		 *  1：true进行加密且必须配置 encodingAesKey
		 *  2：false采用明文模式，同时也支持混合模式
		 *  
		 *  目前企业号只支持加密且必须配置
		 */
		ac.setEncryptMessage(PropKit.getBoolean("encryptMessage", true));
		ac.setEncodingAesKey(PropKit.get("encodingAesKey", "setting it in config file"));
		return ac;
	}

	/**
	 * 实现父类抽方法，处理文本消息
	 * 本例子中根据消息中的不同文本内容分别做出了不同的响应，同时也是为了测试 jfinal qyweixin sdk的基本功能：
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
			outMsg.addNews("Jfinal qyweixin 发布,JAVA 极速 WEB+ORM 框架", "本星球第一个极速开发框架", "https://mmbiz.qlogo.cn/mmbiz/ibHRiaZ9MRcUpjHhhNQzCl9zGicPBWibh1ndW6Mj27ibCREGGVa9mag0iatwDJ1fSPhsib2LiaBVVenAU8ibqW1kGeka9HQ/0?wx_fmt=png","http://mp.weixin.qq.com/s?__biz=MzA4MDA2OTA0Mg==&mid=400919708&idx=1&sn=c35cf7fe2c77f19f4c3edcdb9607925f#rd");
			outMsg.addNews("微信公众号", "大家一起来学习交流", "http://mmbiz.qpic.cn/mmbiz/zz3Q6WSrzq0fcR8VmNCgugHXv7gVlxI6w95RBlKLdKUTjhOZIHGSWsGvjvHqnBnjIWHsicfcXmXlwOWE6sb39kA/0", "http://mp.weixin.qq.com/s?__biz=MzA4MDA2OTA0Mg==&mid=208184833&idx=1&sn=d9e615e45902c3c72db6c24b65c4af3e#rd");
			render(outMsg);
		}
		else if ("美女".equalsIgnoreCase(msgContent)) {
			OutNewsMsg outMsg = new OutNewsMsg(inTextMsg);
			outMsg.addNews(
					"宝贝更新喽",
					"宝贝更新喽，我们只看美女 ^_^",
					"https://mmbiz.qlogo.cn/mmbiz/ibHRiaZ9MRcUok6KHMCAA0GiaZXjfPq48ZicFhyDGShaK21enPCXoY1DbbdqwQ1JzgC9jsYK0kRicoFzqBZqTM7CPmQ/0?wx_fmt=png",
					"http://mp.weixin.qq.com/s?__biz=MzA4MDA2OTA0Mg==&mid=207820985&idx=1&sn=4ef9361e68495fc3ba1d2f7f2bca0511#rd");

			render(outMsg);
		}
		else if ("OAuth".equalsIgnoreCase(msgContent)) {
			String url="http://javen.ngrok.natapp.cn/qyoauth2";
			String urlStr="<a href=\""+url+"\">点击我授权</a>";
			
			OutTextMsg outMsg = new OutTextMsg(inTextMsg);
			outMsg.setContent(urlStr);
			render(outMsg);
		}else if ("jssdk".equalsIgnoreCase(msgContent)) {
			String url="http://javen.ngrok.natapp.cn/qyjssdk";
			String urlStr="<a href=\""+url+"\">JSSDK</a>";
			renderOutTextMsg("授权地址"+urlStr);
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
			log.debug("取消关注：" + inFollowEvent.getFromUserName());
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
		BatchJob batchJob = inJobEvent.getBatchJob();
		
		ApiResult batchGetResult = ConBatchApi.batchGetResult(batchJob.getJobId());
		
		QiYeTextMsg text=new QiYeTextMsg();
		text.setAgentid("16");
		text.setSafe("0");
		text.setTouser("Javen");
		text.setText(new Text("异步任务完成:\n JobType:"+batchJob.getJobType()
				+"\n JobId:"+batchJob.getJobId()
				+"\n 执行结果："+batchGetResult.getJson()));
		SendMessageApi.sendTextMsg(text);
		renderNull();
	}
	

	
}






