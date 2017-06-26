package com.jfinal.qyweixin.sdk.jfinal;

import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

/**
 * 从请求中解析 标识Key 并导出 corpId。
 * 开发者可自行实现此接口，并在 JFinalConfig.configInterceptor 或在 JFinalConfig.afterJFinalStart等位置全局注入。
 *
 */
public interface CorpIdParser {

    String getCorpId(Invocation inv);

    String getCorpId(Controller ctl);


    /**
     * 默认corpId解析器，根据设置的标识Key名称，从请求parameterMap中直接取appId值
     *
     * 默认标识Key名称为"corpId"
     */
    class DefaultParameterCorpIdParser implements CorpIdParser {
        private static final String DEFAULT_CORP_ID_KEY = "corpId";

        private final String corpIdKey;

        public DefaultParameterCorpIdParser() {
            this.corpIdKey = DEFAULT_CORP_ID_KEY;
        }

        public DefaultParameterCorpIdParser(String corpIdKey) {
            this.corpIdKey = corpIdKey;
        }

        @Override
        public String getCorpId(Invocation inv) {
            return getCorpId(inv.getController());
        }

        @Override
        public String getCorpId(Controller ctl) {
            return ctl.getPara(corpIdKey);
        }
    }
}
