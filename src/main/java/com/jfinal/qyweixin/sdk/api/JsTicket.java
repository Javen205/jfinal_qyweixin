package com.jfinal.qyweixin.sdk.api;

import java.io.Serializable;
import java.util.Map;

import com.jfinal.qyweixin.sdk.utils.JsonUtils;
import com.jfinal.qyweixin.sdk.utils.RetryUtils.ResultCheck;



/**
 * JsTicket返回封装
 */
@SuppressWarnings("unchecked")
public class JsTicket implements ResultCheck, Serializable {
    private static final long serialVersionUID = 6600179487477942329L;

    private String ticket; // 正确获取到 ticket 时有值
    private Integer expires_in; // 正确获取到 access_token 时有值
    private Integer errcode; // 出错时有值
    private String errmsg; // 出错时有值

    private Long expiredTime; // 正确获取到 ticket 时有值，存放过期时间
    private String json;

    public JsTicket(String jsonStr) {
        this.json = jsonStr;

        try {
            Map<String, Object> temp = JsonUtils.parse(jsonStr, Map.class);
            ticket = (String) temp.get("ticket");
            expires_in = (Integer) temp.get("expires_in");
            errcode = (Integer) temp.get("errcode");
            errmsg = (String) temp.get("errmsg");

            if (expires_in != null)
                expiredTime = System.currentTimeMillis() + ((expires_in - 5) * 1000);
            // 用户缓存时还原
            if (temp.containsKey("expiredTime")) {
                 expiredTime = (Long) temp.get("expiredTime");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getJson() {
        return json;
    }

    public String getCacheJson() {
        Map<String, Object> temp = JsonUtils.parse(json, Map.class);
        temp.put("expiredTime", expiredTime);
        temp.remove("expires_in");
        return JsonUtils.toJson(temp);
    }

    public boolean isAvailable() {
        if (expiredTime == null)
            return false;
        if (!isSucceed())
            return false;
        if (expiredTime < System.currentTimeMillis())
            return false;
        return ticket != null;
    }

    public String getTicket() {
        return ticket;
    }

    public Integer getExpiresIn() {
        return expires_in;
    }

    public Integer getErrorCode() {
        return errcode;
    }

    public String getErrorMsg() {
        if (errcode != null) {
            String result = ReturnCode.get(errcode);
            if (result != null)
                return result;
        }
        return errmsg;
    }

    public Long getExpiredTime() {
        return expiredTime;
    }

    /**
     * APi 请求是否成功返回
     * @return boolean
     */
    public boolean isSucceed() {
        Integer errorCode = getErrorCode();
        // errorCode 为 0
        return (errorCode == null || errorCode == 0);
    }

    @Override
    public boolean matching() {
        return isAvailable();
    }

}
