package com.jfinal.qyweixin.demo;

import com.jfinal.core.Controller;

public class QyIndexController extends Controller{
	public void index() {
		Object userId = getSession().getAttribute("userId");
		Object openId = getSession().getAttribute("openId");
		setAttr("test", "这里是测试...openId>"+openId+" userId>"+userId);
		render("index.jsp");
	}
}
