
package com.jfinal.qy.weixin.sdk.kit;


import java.util.Arrays;


import com.jfinal.core.Controller;
import com.jfinal.kit.HashKit;
import com.jfinal.kit.StrKit;
import com.jfinal.log.Log;
import com.jfinal.qy.weixin.sdk.api.ApiConfigKit;
import com.jfinal.qy.weixin.sdk.utils.XmlHelper;
import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

public class SignatureCheckKit {
	private static final Log log =  Log.getLog(SignatureCheckKit.class);
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
			result = wxcpt.verifyUrl(msgSignature, timeStamp, nonce,echoStr);
		} catch (AesException e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	
	/**
	 * 检测签名
	 */
	public boolean checkSignature(Controller controller,String xml) {
		String signature = controller.getPara("msg_signature");
		String timestamp = controller.getPara("timestamp");
		String nonce = controller.getPara("nonce");
		String content=getEncrypt(xml);
		
		
		if (StrKit.isBlank(signature) || StrKit.isBlank(timestamp) || StrKit.isBlank(nonce)) {
			controller.renderText("check signature failure");
			return false;
		}
		
		if (SignatureCheckKit.me.checkSignature(signature, timestamp, nonce ,content)) {
			return true;
		}
		else {
			log.error("check signature failure: " +
					" signature = " + controller.getPara("msg_signature") +
					" timestamp = " + controller.getPara("timestamp") +
					" nonce = " + controller.getPara("nonce")+
					" content = " + getEncrypt(xml));
			
			return false;
		}
	}
	
	private String getEncrypt(String xml){
		XmlHelper xmlHelper = XmlHelper.of(xml);
		String content=xmlHelper.getString("//Encrypt");
		return content;
	}
}



