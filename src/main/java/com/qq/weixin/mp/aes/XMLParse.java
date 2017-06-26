/**
 * 对公众平台发送给公众账号的消息加解密示例代码.
 *
 * @copyright Copyright (c) 1998-2014 Tencent Inc.
 */

// ------------------------------------------------------------------------

package com.qq.weixin.mp.aes;

import com.jfinal.kit.LogKit;
import com.jfinal.qyweixin.sdk.utils.XmlHelper;

/**
 * XMLParse class
 *
 * 提供提取消息格式中的密文及生成回复消息格式的接口.
 */
class XMLParse {
    /**
     * 提取出xml数据包中的加密消息
     * @param xmlStr 待提取的xml字符串
     * @return 提取出的加密消息字符串
     * @throws AesException
     */
    public static Object[] extract(String xmlStr) throws AesException     {
        Object[] result = new Object[3];
        try {
            XmlHelper xmlHelper = XmlHelper.of(xmlStr);

            result[0] = 0;
            result[1] = xmlHelper.getString("//Encrypt");
            result[2] = xmlHelper.getString("//ToUserName");
            return result;
        } catch (Exception e) {
            LogKit.error(e.getMessage(), e);
            throw new AesException(AesException.ParseXmlError);
        }
    }

    /**
     * 生成xml消息
     * @param encrypt 加密后的消息密文
     * @param signature 安全签名
     * @param timestamp 时间戳
     * @param nonce 随机字符串
     * @return 生成的xml字符串
     */
    public static String generate(String encrypt, String signature, String timestamp, String nonce) {
        String format = "<xml>\n"
                + "<Encrypt><![CDATA[%1$s]]></Encrypt>\n"
                + "<MsgSignature><![CDATA[%2$s]]></MsgSignature>\n"
                + "<TimeStamp>%3$s</TimeStamp>\n"
                + "<Nonce><![CDATA[%4$s]]></Nonce>\n"
                + "</xml>";
        return String.format(format, encrypt, signature, timestamp, nonce);

    }
}
