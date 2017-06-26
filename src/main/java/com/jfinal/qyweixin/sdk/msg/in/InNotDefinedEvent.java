package com.jfinal.qyweixin.sdk.msg.in;

import com.jfinal.qyweixin.sdk.msg.in.event.EventInMsg;
import com.jfinal.qyweixin.sdk.utils.XmlHelper;

/**
 * 没有找到适配类型时的事件
 */
public class InNotDefinedEvent extends EventInMsg {
	private static final long serialVersionUID = -5202339779294364596L;
	/**
     * 新增xmlHelper，用于用户扩展。
     * 对于不支持的事件类型中，获取xml中想要的参数。
     */
    protected transient XmlHelper xmlHelper;
    
    public InNotDefinedEvent(String toUserName, String fromUserName, Integer createTime, String msgType, String event ,String agentId) {
        super(toUserName, fromUserName, createTime,msgType, event ,agentId);
    }

    public XmlHelper getXmlHelper() {
        return xmlHelper;
    }

    public void setXmlHelper(XmlHelper xmlHelper) {
        this.xmlHelper = xmlHelper;
    }

}
