package com.jfinal.qy.weixin.sdk.msg.send;

/**
 * 发送消息基类 企业号
 * 
 * @author Javen
 * 
 */
public class QiYeBaseMsg {
	/**
	 * UserID 列表（消息接收者，多个接收者用‘|’分隔）。 特殊情况：指定为@all，则向全部有该应用代理访问权限的成员发送。
	 */
	private String touser;
	/**
	 * PartyID 列表，多个接受者用‘|’分隔。当touser 为@all时忽略本参数。
	 */
	private String toparty;
	/**
	 * 消息类型，支持 text/image/voice/video/news/file类型的消息。
	 */
	protected String msgtype;
	/**
	 * 应用代理 ID
	 */
	private String agentid;

	/**
	 * 表示是否是安全消息，0 表示否，1表示是
	 */
	private String safe;

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getToparty() {
		return toparty;
	}

	public void setToparty(String toparty) {
		this.toparty = toparty;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	public String getAgentid() {
		return agentid;
	}

	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}

	public QiYeBaseMsg(String touser, String toparty, String agentid) {
		super();
		this.touser = touser;
		this.toparty = toparty;
		this.agentid = agentid;
	}

	public QiYeBaseMsg() {
		super();
	}

	public String getSafe() {
		return safe;
	}

	public void setSafe(String safe) {
		this.safe = safe;
	}

}
