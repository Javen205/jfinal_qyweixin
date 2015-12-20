package com.javen.demo.common;

import com.jfinal.core.Controller;

public class QyIndexController extends Controller{
	public void index() {
		setAttr("test", "这里是测试...");
		render("index.jsp");
	}
}
