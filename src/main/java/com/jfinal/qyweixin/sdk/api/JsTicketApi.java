package com.jfinal.qyweixin.sdk.api;

import com.jfinal.kit.StrKit;
import com.jfinal.qyweixin.sdk.cache.IAccessTokenCache;
import com.jfinal.qyweixin.sdk.kit.ParaMap;
import com.jfinal.qyweixin.sdk.utils.HttpUtils;
import com.jfinal.qyweixin.sdk.utils.RetryUtils;

import java.util.concurrent.Callable;

/**
 * 
 * 生成签名之前必须先了解一下jsapi_ticket，jsapi_ticket是公众号用于调用微信JS接口的临时票据
 * https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket?access_token=ACCESS_TOKEN
 * 
 */
public class JsTicketApi {

    private static String apiUrl = "https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket";

    static IAccessTokenCache accessTokenCache = ApiConfigKit.getAccessTokenCache();

    /**
     * JSApi的类型
     *
     * jsapi: 用于分享等js-api
     *
     * wx_card：用于卡券接口签名凭证api_ticket
     *
     */
    public enum JsApiType {
        jsapi
    }

    /**
     *
     * http GET请求获得jsapi_ticket（有效期7200秒，开发者必须在自己的服务全局缓存jsapi_ticket）
     *
     * @param jsApiType jsApi类型
     * @return JsTicket
     */
    public static JsTicket getTicket(JsApiType jsApiType) {
        String access_token = AccessTokenApi.getAccessTokenStr();
        String appId = ApiConfigKit.getApiConfig().getCorpId();
        String key = appId + ':' + jsApiType.name();
        final ParaMap pm = ParaMap.create("access_token", access_token).put("type", jsApiType.name());

        // 2016.07.21修改By L.cm 为了更加方便扩展
        String jsTicketJson = accessTokenCache.get(key);
        JsTicket jsTicket = null;
        if (StrKit.notBlank(jsTicketJson)) {
            jsTicket = new JsTicket(jsTicketJson);
        }
        if (null == jsTicket || !jsTicket.isAvailable()) {
            // 最多三次请求
            jsTicket = RetryUtils.retryOnException(3, new Callable<JsTicket>() {

                @Override
                public JsTicket call() throws Exception {
                    return new JsTicket(HttpUtils.get(apiUrl, pm.getData()));
                }

            });

            if (null != jsApiType) {
                accessTokenCache.set(key, jsTicket.getCacheJson());
            }

        }
        return jsTicket;
    }

}
