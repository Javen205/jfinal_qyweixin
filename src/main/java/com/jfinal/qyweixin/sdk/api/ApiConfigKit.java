package com.jfinal.qyweixin.sdk.api;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.jfinal.kit.StrKit;
import com.jfinal.log.Log;
import com.jfinal.qyweixin.sdk.cache.DefaultAccessTokenCache;
import com.jfinal.qyweixin.sdk.cache.IAccessTokenCache;

/**
 * 将 ApiConfig 绑定到 ThreadLocal 的工具类，以方便在当前线程的各个地方获取 ApiConfig 对象：
 * 1：如果控制器继承自 MsgController 该过程是自动的，详细可查看 MsgInterceptor 与之的配合
 * 2：如果控制器继承自 ApiController 该过程是自动的，详细可查看 ApiInterceptor 与之的配合
 * 3：如果控制器没有继承自 MsgController、ApiController，则需要先手动调用
 *    ApiConfigKit.setThreadLocalCorpId(getCorpId) 来绑定 corpId 到线程之上
 */
public class ApiConfigKit {
    private static final Log log = Log.getLog(ApiConfigKit.class);

    private static final ThreadLocal<String> TL = new ThreadLocal<String>();

    private static final Map<String, ApiConfig> CFG_MAP = new ConcurrentHashMap<String, ApiConfig>();
    private static final String DEFAULT_CFG_KEY = "_default_cfg_key_";

    // 开发模式将输出消息交互 xml 到控制台
    private static boolean devMode = false;

    public static void setDevMode(boolean devMode) {
        ApiConfigKit.devMode = devMode;
    }

    public static boolean isDevMode() {
        return devMode;
    }

    /**
     * 添加公众号配置，每个corpId只需添加一次，相同corpId将被覆盖。
     * 第一个添加的将作为默认公众号配置
     * @param apiConfig 公众号配置
     * @return ApiConfig 公众号配置
     */
    public static ApiConfig putApiConfig(ApiConfig apiConfig) {
        if (CFG_MAP.size() == 0) {
            CFG_MAP.put(DEFAULT_CFG_KEY, apiConfig);
        }
        return CFG_MAP.put(apiConfig.getCorpId(), apiConfig);
    }

    public static ApiConfig removeApiConfig(ApiConfig apiConfig) {
        return removeApiConfig(apiConfig.getCorpId());
    }

    public static ApiConfig removeApiConfig(String corpId) {
        return CFG_MAP.remove(corpId);
    }

    public static void setThreadLocalCorpId(String corpId){
        if (StrKit.isBlank(corpId)) {
            corpId = CFG_MAP.get(DEFAULT_CFG_KEY).getCorpId();
        }
        TL.set(corpId);
    }

    public static void removeThreadLocalCorpId() {
        TL.remove();
    }

    public static String getCorpId() {
        String corpId = TL.get();
        if (StrKit.isBlank(corpId)) {
            corpId = CFG_MAP.get(DEFAULT_CFG_KEY).getCorpId();
        }
        return corpId;
    }

    public static ApiConfig getApiConfig() {
        String corpId = getCorpId();
        return getApiConfig(corpId);
    }

    public static ApiConfig getApiConfig(String corpId) {
        log.debug("corpId: " + corpId);
        ApiConfig cfg = CFG_MAP.get(corpId);
        if (cfg == null)
            throw new IllegalStateException("需事先调用 ApiConfigKit.putApiConfig(apiConfig) 将 corpId对应的 ApiConfig 对象存入，" +
                    "如JFinalConfig.afterJFinalStart()中调用, 才可以使用 ApiConfigKit.getApiConfig() 系列方法");
        return cfg;
    }

    static IAccessTokenCache accessTokenCache = new DefaultAccessTokenCache();

    public static void setAccessTokenCache(IAccessTokenCache accessTokenCache) {
        ApiConfigKit.accessTokenCache = accessTokenCache;
    }

    public static IAccessTokenCache getAccessTokenCache() {
        return ApiConfigKit.accessTokenCache;
    }
}
