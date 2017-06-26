package com.jfinal.qyweixin.sdk.utils;

import com.jfinal.json.FastJson;
import com.jfinal.json.JFinalJson;
import com.jfinal.json.Json;
import com.jfinal.plugin.activerecord.CPI;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Json转换
 * 默认使用jackson
 * 再次fastJson
 * 最后使用jsonKit
 *
 * @author L.cm
 * email: 596392912@qq.com
 * site:http://www.dreamlu.net
 * date 2015年5月13日下午4:58:33
 */
public final class JsonUtils {

    private JsonUtils() {}

    /**
     * 将model转为json字符串
     * @param model jfinal model
     * @return JsonString
     */
    public static String toJson(Model<? extends Model<?>> model) {
        return toJson(CPI.getAttrs(model));
    }

    /**
     * 将Collection&lt;Model&gt;转换为json字符串
     * @param models jfinal model
     * @return JsonString
     */
    public static String toJson(Collection<Model<? extends Model<?>>> models) {
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        for (Model<? extends Model<?>> model : models) {
            list.add(CPI.getAttrs(model));
        }
        return toJson(list);
    }

    /**
     * 将 record 转为json字符串
     * @param record jfinal record
     * @return JsonString
     */
    public static String toJson(Record record) {
        return toJson(record.getColumns());
    }

    /**
     * 将List&lt;Record&gt;转换为json字符串
     * @param records jfinal records
     * @return JsonString
     */
    public static String toJson(List<Record> records) {
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        for (Record record : records) {
            list.add(record.getColumns());
        }
        return toJson(list);
    }

    // Json
    private static final Json json;

    static {
        Json jsonToUse = null;
        // com.fasterxml.jackson.databind.ObjectMapper?
        if (ClassUtils.isPresent("com.fasterxml.jackson.databind.ObjectMapper", JsonUtils.class.getClassLoader())) {
            jsonToUse = new JsonUtils.Jackson();
        }
        // com.alibaba.fastjson.JSONObject?
        else if (ClassUtils.isPresent("com.alibaba.fastjson.JSONObject", JsonUtils.class.getClassLoader())) {
            jsonToUse = new FastJson();
        }
        // JFinalJson
        else {
            jsonToUse = new JFinalJson();
        }
        json = jsonToUse;
    }

    /**
     * 解决微信特殊字符的乱码
     */
    private static class Jackson extends Json {
        private final com.fasterxml.jackson.databind.ObjectMapper objectMapper;

        public Jackson() {
            this.objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
            this.objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
            this.objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        }

        @Override
        public String toJson(Object object) {
            try {
                return objectMapper.writeValueAsString(object);
            } catch (Exception e) {
                throw e instanceof RuntimeException ? (RuntimeException) e : new RuntimeException(e);
            }
        }

        @Override
        public <T> T parse(String jsonString, Class<T> type) {
            try {
                return objectMapper.readValue(jsonString, type);
            } catch (Exception e) {
                throw e instanceof RuntimeException ? (RuntimeException) e : new RuntimeException(e);
            }
        }

    }

    /**
     * 将 Object 转为json字符串
     * @param object 对象
     * @return JsonString
     */
    public static String toJson(Object object) {
        if (json == null) {
            throw new RuntimeException("Jackson, Fastjson or JFinalJson not supported");
        }
        return json.toJson(object);
    }

    /**
     * 将 json字符串 转为Object
     * @param jsonString json字符串
     * @param valueType 结果类型
     * @param <T> 泛型标记
     * @return T 结果
     */
    public static <T> T parse(String jsonString, Class<T> valueType) {
        if (json == null) {
            throw new RuntimeException("Jackson, Fastjson not supported");
        }
        return json.parse(jsonString, valueType);
    }

}
