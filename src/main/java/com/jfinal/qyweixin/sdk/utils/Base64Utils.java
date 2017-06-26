package com.jfinal.qyweixin.sdk.utils;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.Charset;

/**
 * JDK6之后 Base64工具
 *
 * 参考：http://www.importnew.com/14961.html
 */
public class Base64Utils {
    private Base64Utils() {}

    /**
     * 编码
     * @param value byte数组
     * @return {String}
     */
    public static String encode(byte[] value) {
        return DatatypeConverter.printBase64Binary(value);
    }

    /**
     * 编码
     * @param value 字符串
     * @return {String}
     */
    public static String encode(String value) {
        byte[] val = value.getBytes(Charsets.UTF_8);
        return Base64Utils.encode(val);
    }

    /**
     * 编码
     * @param value 字符串
     * @param charsetName charSet
     * @return {String}
     */
    public static String encode(String value, String charsetName) {
        byte[] val = value.getBytes(Charset.forName(charsetName));
        return Base64Utils.encode(val);
    }

    /**
     * 解码
     * @param value 字符串
     * @return {byte[]}
     */
    public static byte[] decodeBase64(String value) {
        return DatatypeConverter.parseBase64Binary(value);
    }

    /**
     * 解码
     * @param value 字符串
     * @return {String}
     */
    public static String decode(String value) {
        byte[] decodedValue = Base64Utils.decodeBase64(value);
        return new String(decodedValue, Charsets.UTF_8);
    }

    /**
     * 解码
     * @param value 字符串
     * @param charsetName 字符集
     * @return {String}
     */
    public static String decode(String value, String charsetName) {
        byte[] decodedValue = Base64Utils.decodeBase64(value);
        return new String(decodedValue, Charset.forName(charsetName));
    }

}
