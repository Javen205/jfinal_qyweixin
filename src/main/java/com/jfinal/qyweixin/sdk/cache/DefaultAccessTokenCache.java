package com.jfinal.qyweixin.sdk.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认存储与内存中
 */
public class DefaultAccessTokenCache implements IAccessTokenCache {

    private Map<String, String> map = new ConcurrentHashMap<String, String>();

    @Override
    public String get(String key) {
        return map.get(key);
    }

    @Override
    public void set(String key, String jsonValue) {
        map.put(key, jsonValue);
    }

    @Override
    public void remove(String key) {
        map.remove(key);
    }

}
