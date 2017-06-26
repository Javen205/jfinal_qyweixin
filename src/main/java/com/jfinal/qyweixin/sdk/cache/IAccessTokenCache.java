package com.jfinal.qyweixin.sdk.cache;

public interface IAccessTokenCache {

    // 默认超时时间7200秒 5秒用于程序执行误差
    int DEFAULT_TIME_OUT = 7200 - 5;

    String get(String key);

    void set(String key, String jsonValue);

    void remove(String key);

}
