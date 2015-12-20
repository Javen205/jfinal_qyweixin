
package com.jfinal.qy.weixin.sdk.api;

import com.jfinal.qy.weixin.sdk.utils.HttpUtils;

/**
 * menu api
 */
public class MenuApi {
	
	private static String getMenu = "https://qyapi.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN&agentid=AGENTID";
	private static String createMenu = "https://qyapi.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN&agentid=AGENTID";
	private static String deleteMenu ="https://qyapi.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN&agentid=AGENTID";
	/**
	 * 查询菜单
	 */
	public static ApiResult getMenu(String agentid) {
		getMenu=getMenu.replace("ACCESS_TOKEN", AccessTokenApi.getAccessTokenStr()).replace("AGENTID", agentid);
		String jsonResult = HttpUtils.get(getMenu + AccessTokenApi.getAccessTokenStr());
		return new ApiResult(jsonResult);
	}
	
	/**
	 * 创建菜单
	 */
	public static ApiResult createMenu(String jsonStr,String agentid) {
		createMenu=createMenu.replace("ACCESS_TOKEN", AccessTokenApi.getAccessTokenStr()).replace("AGENTID", agentid);
		String jsonResult = HttpUtils.post(createMenu + AccessTokenApi.getAccessTokenStr(), jsonStr);
		return new ApiResult(jsonResult);
	}
	
	public static ApiResult deleteMenu(String agentid){
		deleteMenu=deleteMenu.replace("ACCESS_TOKEN", AccessTokenApi.getAccessTokenStr()).replace("AGENTID", agentid);
		String jsonResult = HttpUtils.get(deleteMenu + AccessTokenApi.getAccessTokenStr());
		return new ApiResult(jsonResult);
	}
}


