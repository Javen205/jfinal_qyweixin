package com.jfinal.qy.weixin.sdk.api;

import java.io.Serializable;
import java.util.Map;

import com.jfinal.qy.weixin.sdk.utils.JsonUtils;
import com.jfinal.qy.weixin.sdk.utils.RetryUtils.ResultCheck;
import com.jfinal.qy.weixin.sdk.utils.ReturnCode;

/**
 * JsTicket返回封装
 */
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
			@SuppressWarnings("unchecked")
			Map<String, Object> temp = JsonUtils.decode(jsonStr, Map.class);
			ticket = (String) temp.get("ticket");
			expires_in = getInt(temp, "expires_in");
			errcode = getInt(temp, "errcode");
			errmsg = (String) temp.get("errmsg");

			if (expires_in != null)
				expiredTime = System.currentTimeMillis() + ((expires_in - 5) * 1000);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String toString() {
		return getJson();
	}
	
	private Integer getInt(Map<String, Object> temp, String key) {
		Number number = (Number) temp.get(key);
		return number == null ? null : number.intValue();
	}
	
	public String getJson() {
		return json;
	}

	public boolean isAvailable() {
		if (expiredTime == null)
			return false;
		if (errcode != null)
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

	/**
	 * APi 请求是否成功返回
	 */
	public boolean isSucceed() {
		Integer errorCode = getErrorCode();
		// errorCode 为 0
		// 时也可以表示为成功，详见：http://mp.weixin.qq.com/wiki/index.php?title=%E5%85%A8%E5%B1%80%E8%BF%94%E5%9B%9E%E7%A0%81%E8%AF%B4%E6%98%8E
		return (errorCode == null || errorCode == 0);
	}

	public boolean matching() {
		return isAvailable();
	}

}
