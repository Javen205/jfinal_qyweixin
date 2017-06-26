package com.jfinal.qyweixin.sdk.jfinal;

import com.jfinal.qyweixin.sdk.msg.in.InImageMsg;
import com.jfinal.qyweixin.sdk.msg.in.InLocationMsg;
import com.jfinal.qyweixin.sdk.msg.in.InNotDefinedEvent;
import com.jfinal.qyweixin.sdk.msg.in.InNotDefinedMsg;
import com.jfinal.qyweixin.sdk.msg.in.InShortVideoMsg;
import com.jfinal.qyweixin.sdk.msg.in.InTextMsg;
import com.jfinal.qyweixin.sdk.msg.in.InVideoMsg;
import com.jfinal.qyweixin.sdk.msg.in.InVoiceMsg;
import com.jfinal.qyweixin.sdk.msg.in.event.InEnterAgentEvent;
import com.jfinal.qyweixin.sdk.msg.in.event.InFollowEvent;
import com.jfinal.qyweixin.sdk.msg.in.event.InJobEvent;
import com.jfinal.qyweixin.sdk.msg.in.event.InLocationEvent;
import com.jfinal.qyweixin.sdk.msg.in.event.InMenuEvent;
import com.jfinal.qyweixin.sdk.msg.in.event.InQrCodeEvent;

/**
 * MsgControllerAdapter 对 MsgController 部分方法提供了默认实现，
 * 以便开发者不去关注 MsgController 中不需要处理的抽象方法，节省代码量
 */
public abstract  class MsgControllerAdapter extends MsgController {

	 // 接收文本消息事件
    @Override
    protected abstract void processInTextMsg(InTextMsg inTextMsg);

    // 接收图片消息事件
    @Override
    protected void processInImageMsg(InImageMsg inImageMsg) {
        renderDefault();
    }

    // 接收语音消息事件
    @Override
    protected void processInVoiceMsg(InVoiceMsg inVoiceMsg) {
        renderDefault();
    }

    // 接收视频消息
    @Override
    protected void processInVideoMsg(InVideoMsg inVideoMsg) {
        renderDefault();
    }
    // 接收短视频消息
	@Override
	protected void processInShortVideoMsg(InShortVideoMsg inShortVideoMsg) {
        renderDefault();		
	}

	// 接收地理位置消息事件
    @Override
    protected void processInLocationMsg(InLocationMsg inLocationMsg) {
        renderDefault();
    }
    // 上报地理位置事件
    @Override
    protected void processInLocationEvent(InLocationEvent inLocationEvent) {
        renderDefault();
    }

	//  关注/取消关注事件
    @Override
    protected abstract void processInFollowEvent(InFollowEvent inFollowEvent);

	 // 自定义菜单事件
    @Override
    protected abstract void processInMenuEvent(InMenuEvent inMenuEvent);

    // 扫描带参数二维码事件
    @Override
    protected void processInQrCodeEvent(InQrCodeEvent inQrCodeEvent) {
        renderDefault();
    }

    // 处理接收到的成员进入应用的事件
	@Override
	protected void processInEnterAgentEvent(InEnterAgentEvent inAgentEvent) {
		 renderDefault();
	}
	// 处理接收到的异步任务完成事件事件
	@Override
	protected void processInJobEvent(InJobEvent inJobEvent) {
		renderDefault();
	}

	 // 没有找到对应的消息
    @Override
    protected void processIsNotDefinedMsg(InNotDefinedMsg inNotDefinedMsg) {
        renderDefault();
    }
    // 没有找到对应的事件
	@Override
	protected void processIsNotDefinedEvent(InNotDefinedEvent inNotDefinedEvent) {
		renderDefault();
	}
	 /**
     * 方便没有使用的api返回“”避免出现，该公众号暂时不能提供服务
     *
     * 可重写该方法
     */
    protected void renderDefault() {
        renderText("");
    }
}
