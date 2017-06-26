package com.jfinal.qyweixin.sdk.jfinal;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.qyweixin.sdk.api.ApiConfigKit;


/**
 * ApiController 为 ApiController 绑定 ApiConfig 对象到当前线程，
 * 以便在后续的操作中可以使用 ApiConfigKit.getApiConfig() 获取到该对象
 */
public class ApiInterceptor implements Interceptor {
    private static CorpIdParser _parser = new CorpIdParser.DefaultParameterCorpIdParser();

    public static void setCorpIdParser(CorpIdParser parser) {
        _parser = parser;
    }

    public void intercept(Invocation inv) {
        Controller controller = inv.getController();
        if (!(controller instanceof ApiController))
            throw new RuntimeException("控制器需要继承 ApiController");

        try {
            String corpId = _parser.getCorpId(controller);
            // 将 corpId 与当前线程绑定，以便在后续操作中方便获取ApiConfig对象： ApiConfigKit.getApiConfig();
            ApiConfigKit.setThreadLocalCorpId(corpId);
            inv.invoke();
        } finally {
            ApiConfigKit.removeThreadLocalCorpId();
        }
    }
}

