package com.jfinal.qy.weixin.sdk.menu;

/**
 * @author Javen
 * @Email javenlife@126.com
 * 
 */
public class ComButton extends Button {
	private Button[] sub_button;

	public Button[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(Button[] sub_button) {
		this.sub_button = sub_button;
	}
	
}
