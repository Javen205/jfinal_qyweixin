
package com.jfinal.qy.weixin.sdk.msg.in.event;

import com.jfinal.qy.weixin.sdk.msg.in.InMsg;
/**
 * @author Javen
 */
public abstract class EventInMsg extends InMsg
{
    protected String event;

    public EventInMsg(String toUserName, String fromUserName, Integer createTime, String msgType, String event,String agentId)
    {
        super(toUserName, fromUserName, createTime, msgType,agentId);
        this.event = event;
    }

    public String getEvent()
    {
        return event;
    }

    public void setEvent(String event)
    {
        this.event = event;
    }
}
