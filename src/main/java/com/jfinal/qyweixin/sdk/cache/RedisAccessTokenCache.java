package com.jfinal.qyweixin.sdk.cache;

import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;

public class RedisAccessTokenCache implements IAccessTokenCache {
    private final String ACCESS_TOKEN_PREFIX = "jfinal-qyweixin:token:";

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

    @Override
    public String get(String key) {
        return cache.get(ACCESS_TOKEN_PREFIX.concat(key));
    }

    @Override
    public void set(String key, String jsonValue) {
        cache.setex(ACCESS_TOKEN_PREFIX.concat(key), DEFAULT_TIME_OUT, jsonValue);
    }

    @Override
    public void remove(String key) {
        cache.del(ACCESS_TOKEN_PREFIX.concat(key));
    }

}
