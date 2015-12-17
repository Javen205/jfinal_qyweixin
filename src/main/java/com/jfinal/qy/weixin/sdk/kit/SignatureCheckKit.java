
package com.jfinal.qy.weixin.sdk.kit;


import java.util.Arrays;

import com.jfinal.kit.HashKit;
import com.jfinal.qy.weixin.sdk.api.ApiConfigKit;
import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

public class SignatureCheckKit {
	
	public static final SignatureCheckKit me = new SignatureCheckKit();

	public boolean checkSignature(String msgSignature, String timestamp, String nonce) {
		String TOKEN = ApiConfigKit.getApiConfig().getToken();
		String array[] = {TOKEN, timestamp, nonce};
		Arrays.sort(array);
		String tempStr = new StringBuilder().append(array[0] + array[1] + array[2]).toString();
		tempStr = HashKit.sha1(tempStr);
		return tempStr.equalsIgnoreCase(msgSignature);
	}
	public boolean checkSignature(String msgSignature, String timestamp, String nonce,String content) {
		String TOKEN = ApiConfigKit.getApiConfig().getToken();
		String array[] = {TOKEN, timestamp, nonce, content};
		Arrays.sort(array);
		String tempStr = new StringBuilder().append(array[0] + array[1] + array[2] + array[3]).toString();
		tempStr = HashKit.sha1(tempStr);
		return tempStr.equalsIgnoreCase(msgSignature);
	}
	
	
	public String VerifyURL(String msgSignature, String timeStamp, String nonce, String echoStr){
		String result =null;
		try {
			String token = ApiConfigKit.getApiConfig().getToken();
			String corpId = ApiConfigKit.getApiConfig().getCorpId();
			String encodingAesKey = ApiConfigKit.getApiConfig().getEncodingAesKey();
			WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(token,encodingAesKey,corpId);
			result = wxcpt.VerifyURL(msgSignature, timeStamp, nonce,echoStr);
		} catch (AesException e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
}



