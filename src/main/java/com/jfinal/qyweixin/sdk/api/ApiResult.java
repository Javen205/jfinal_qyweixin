package com.jfinal.qyweixin.sdk.api;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.jfinal.qyweixin.sdk.utils.JsonUtils;

/**
 * 封装 API 响应结果，将 json 字符串转换成 java 数据类型
 *
 * jackson 中 json 类型与 java 类型对应关系如下：
 *  <pre>
 *  http://wiki.fasterxml.com/JacksonInFiveMinutes
 *  JSON TYPE                JAVA TYPE
 *  object                    LinkedHashMap&lt;String,Object&gt;
 *  array                    ArrayList&lt;Object&gt;
 *  string                    String
 *  number (no fraction)    Integer, Long or BigInteger (smallest applicable)
 *  number (fraction)        Double (configurable to use BigDecimal)
 *  true|false                Boolean
 *  null                    null
 *  </pre>
 */
public class ApiResult {

    private Map<String, Object> attrs;
    private String json;

    /**
     * 通过 json 构造 ApiResult，注意返回结果不为 json 的 api（如果有的话）
     * @param jsonStr json字符串
     */
    @SuppressWarnings("unchecked")
    public ApiResult(String jsonStr) {
        this.json = jsonStr;

        try {
            Map<String, Object> temp = JsonUtils.parse(jsonStr, Map.class);
            this.attrs = temp;

            refreshAccessTokenIfInvalid();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过 json 创建 ApiResult 对象，等价于 new ApiResult(jsonStr)
     * @param jsonStr json字符串
     * @return {ApiResult}
     */
    public static ApiResult create(String jsonStr) {
        return new ApiResult(jsonStr);
    }

    /**
     * 如果 api 调用返回结果表明 access_token 无效，则刷新它
     * 正常情况下不会出现使用本方法刷新 access_token 的操作，除非以下情况发生：
     * 1：另一程序重新获取了该公众号的 access_token
     * 2：使用微信公众平台接口调试工具获取了该公众号的 access_token，此情况本质上与 1 中情况相同
     * 3：微信服务器重新调整了过期时间或者发生其它 access_token 异常情况
     *
     * 2016-06-21 by L.cm 添加 synchronized 锁感谢Git@osc #Lucare
     *
     */
    private synchronized void refreshAccessTokenIfInvalid() {
        if (isAccessTokenInvalid())
            AccessTokenApi.refreshAccessToken();
    }

    public String getJson() {
        return json;
    }

    public String toString() {
        return getJson();
    }

    /**
     * APi 请求是否成功返回
     * @return {boolean}
     */
    public boolean isSucceed() {
        Integer errorCode = getErrorCode();
        // errorCode 为 0 时也可以表示为成功
        return (errorCode == null || errorCode == 0);
    }

    public Integer getErrorCode() {
        return getInt("errcode");
    }

    public String getErrorMsg() {
        Integer errorCode = getErrorCode();
        if (errorCode != null) {
            String result = ReturnCode.get(errorCode);
            if (result != null)
                return result;
        }
        return (String)attrs.get("errmsg");
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String name) {
        return (T)attrs.get(name);
    }

    public String getStr(String name) {
        return (String)attrs.get(name);
    }

    public Integer getInt(String name) {
        Number number = (Number) attrs.get(name);
        return number == null ? null : number.intValue();
    }

    public Long getLong(String name) {
        Number number = (Number) attrs.get(name);
        return number == null ? null : number.longValue();
    }

    public BigInteger getBigInteger(String name) {
        return (BigInteger)attrs.get(name);
    }

    public Double getDouble(String name) {
        return (Double)attrs.get(name);
    }

    public BigDecimal getBigDecimal(String name) {
        return (BigDecimal)attrs.get(name);
    }

    public Boolean getBoolean(String name) {
        return (Boolean)attrs.get(name);
    }

    @SuppressWarnings("rawtypes")
    public List getList(String name) {
        return (List)attrs.get(name);
    }

    @SuppressWarnings("rawtypes")
    public Map getMap(String name) {
        return (Map)attrs.get(name);
    }

    /**
     * 判断 API 请求结果失败是否由于 access_token 无效引起的
     * 无效可能的情况 error_code 值：
     * 40001 = 获取access_token时AppSecret错误，或者access_token无效(刷新后也可以引起老access_token失效)
     * 42001 = access_token超时
     * 42002 = refresh_token超时
     * 40014 = 不合法的access_token
     * @return {boolean}
     */
    public boolean isAccessTokenInvalid() {
        Integer ec = getErrorCode();
        return ec != null && (ec == 40001 || ec == 42001 || ec == 42002 || ec == 40014);
    }
}