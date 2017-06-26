package com.jfinal.qyweixin.sdk.msg.send;


/**
 * 企业文本消息
 * 
 * @author Javen
 * 
 */
public class QiYeTextMsg extends QiYeBaseMsg {
	/**
	 * 消息内容
	 */
	
	private Text text;
	

	public QiYeTextMsg() {
		this.msgtype=MessageType.text.name();
	}


	public QiYeTextMsg(Text text) {
		this.text = text;
        this.msgtype=MessageType.text.name();
	}

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}
	
	
}