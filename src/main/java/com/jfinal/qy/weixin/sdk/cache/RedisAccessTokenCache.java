package com.jfinal.qy.weixin.sdk.cache;

import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;

public class RedisAccessTokenCache implements IAccessTokenCache {
	
	private final String ACCESS_TOKEN_PREFIX = "jfinal_weixin:";
	
	private final Cache cache;
	
	public RedisAccessTokenCache() {
		this.cache = Redis.use();
	}
	
	public RedisAccessTokenCache(String cacheName) {
		this.cache = Redis.use(cacheName);
	}
	
	public RedisAccessTokenCache(Cache cache) {
		this.cache = cache;
	}
	
	public <T> T get(String key) {
		return cache.get(ACCESS_TOKEN_PREFIX + key);
	}
	
	public void set(String key, Object object) {
		cache.setex(ACCESS_TOKEN_PREFIX + key, DEFAULT_TIME_OUT, object);
	}
	
	public void remove(String key) {
		cache.del(ACCESS_TOKEN_PREFIX + key);
	}
	
}
