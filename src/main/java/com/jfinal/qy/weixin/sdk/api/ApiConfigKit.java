package com.jfinal.qy.weixin.sdk.api;

import com.jfinal.qy.weixin.sdk.cache.DefaultAccessTokenCache;
import com.jfinal.qy.weixin.sdk.cache.IAccessTokenCache;

/**
 * 将 ApiConfig 绑定到 ThreadLocal 的工具类，以方便在当前线程的各个地方获取 ApiConfig 对象：
 * 1：如果控制器继承自 MsgController 该过程是自动的，详细可查看 MsgInterceptor 与之的配合
 * 2：如果控制器继承自 ApiController 该过程是自动的，详细可查看 ApiInterceptor 与之的配合
 * 3：如果控制器没有继承自 MsgController、ApiController，则需要先手动调用 
 *    ApiConfigKit.setThreadLocalApiConfig(ApiConfig) 来绑定 apiConfig 到线程之上
 */
public class ApiConfigKit {
	
	private static final ThreadLocal<ApiConfig> tl = new ThreadLocal<ApiConfig>();
	
	// 开发模式将输出消息交互 xml 到控制台
	private static boolean devMode = false;
	
	public static void setDevMode(boolean devMode) {
		ApiConfigKit.devMode = devMode;
	}
	
	public static boolean isDevMode() {
		return devMode;
	}
	
	public static void setThreadLocalApiConfig(ApiConfig apiConfig) {
		tl.set(apiConfig);
	}
	
	public static void removeThreadLocalApiConfig() {
		tl.remove();
	}
	
	public static ApiConfig getApiConfig() {
		ApiConfig result = tl.get();
		if (result == null)
			throw new IllegalStateException("需要事先使用 ApiConfigKit.setThreadLocalApiConfig(apiConfig) 将 ApiConfig对象存入，才可以调用 ApiConfigKit.getApiConfig() 方法");
		return result;
	}
	
	static IAccessTokenCache accessTokenCache = new DefaultAccessTokenCache();
	
	public static void setAccessTokenCache(IAccessTokenCache accessTokenCache) {
		ApiConfigKit.accessTokenCache = accessTokenCache;
	}
	
	public static IAccessTokenCache getAccessTokenCache() {
		return ApiConfigKit.accessTokenCache;
	}
	
}