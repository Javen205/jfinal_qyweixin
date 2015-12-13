package com.jfinal.qy.weixin.sdk.kit;


import com.jfinal.qy.weixin.sdk.api.ApiConfig;
import com.jfinal.qy.weixin.sdk.api.ApiConfigKit;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

/**
 * 对微信平台官方给出的加密解析代码进行再次封装
 * 
 * 异常java.security.InvalidKeyException:illegal Key Size的解决方案：
 * 1：在官方网站下载JCE无限制权限策略文件（JDK7的下载地址：
 *     http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html
 * 2：下载后解压，可以看到local_policy.jar和US_export_policy.jar以及readme.txt
 * 3：如果安装了JRE，将两个jar文件放到%JRE_HOME%\lib\security目录下覆盖原来的文件
 * 4：如果安装了JDK，将两个jar文件放到%JDK_HOME%\jre\lib\security目录下覆盖原来文件
 * 
 * 
 * 设置为消息加密模式后 JFinal Action Report 中有如下参数：
 * timestamp=1417610658
 * encrypt_type=aes
 * nonce=132155339
 * msg_signature=8ed2a14146c924153743162ab2c0d28eaf30a323
 * signature=1a3fad9a528869b1a73faf4c8054b7eeda2463d3
 */
public class MsgEncryptKit {
	
	private static final String format = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><Encrypt><![CDATA[%1$s]]></Encrypt></xml>";
	/**
	 * 将公众平台回复用户的消息加密打包.
	 * @param msg
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static String encrypt(String msg, String timestamp, String nonce) {
		try {
			ApiConfig ac = ApiConfigKit.getApiConfig();
			WXBizMsgCrypt pc = new WXBizMsgCrypt(ac.getToken(), ac.getEncodingAesKey(), ac.getCorpId());
			return pc.EncryptMsg(msg, timestamp, nonce);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 检验消息的真实性，并且获取解密后的明文.
	 * @param encryptedMsg
	 * @param timestamp
	 * @param nonce
	 * @param msgSignature
	 * @return
	 */
	public static String decrypt(String encryptedMsg, String timestamp, String nonce, String msgSignature) {
		try {
			ApiConfig ac = ApiConfigKit.getApiConfig();
			
			String encodingAesKey = ac.getEncodingAesKey();
			if (encodingAesKey == null)
				throw new IllegalStateException("encodingAesKey can not be null, config encodingAesKey first.");
			
			WXBizMsgCrypt pc = new WXBizMsgCrypt(ac.getToken(), encodingAesKey, ac.getCorpId());
			return pc.DecryptMsg(msgSignature, timestamp, nonce, encryptedMsg);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}



