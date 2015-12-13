
package com.jfinal.qy.weixin.sdk.msg;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.jfinal.qy.weixin.sdk.msg.out.OutImageMsg;
import com.jfinal.qy.weixin.sdk.msg.out.OutMsg;
import com.jfinal.qy.weixin.sdk.msg.out.OutNewsMsg;
import com.jfinal.qy.weixin.sdk.msg.out.OutTextMsg;
import com.jfinal.qy.weixin.sdk.msg.out.OutVideoMsg;
import com.jfinal.qy.weixin.sdk.msg.out.OutVoiceMsg;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.ObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

/**
 * 利用 FreeMarker 动态生成 OutMsg xml 内容 
 */
public class OutMsgXmlBuilder {
	
	private static String encoding = "utf-8";
	private static Configuration config = initFreeMarkerConfiguration();
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	public static String build(OutMsg outMsg) {
		if (outMsg == null)
			throw new IllegalArgumentException("参数 OutMsg 不能为 null");
		
		Map root = new HashMap();
		// 供 OutMsg 里的 TEMPLATE 使用
		root.put("__msg", outMsg);
		
		try {
			Template template = config.getTemplate(outMsg.getClass().getSimpleName(), encoding);
			StringWriter sw = new StringWriter();
			template.process(root, sw);
			return sw.toString();
		} catch (freemarker.core.InvalidReferenceException e) {
			throw new RuntimeException("可能是 " + outMsg.getClass().getSimpleName()+  " 对象中的某些属性未赋值，请仔细检查", e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private static Configuration initFreeMarkerConfiguration() {
		Configuration config = new Configuration();
		StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
		initStringTemplateLoader(stringTemplateLoader);
		config.setTemplateLoader(stringTemplateLoader);
		
		// 模板缓存更新时间，对于OutMsg xml 在类文件中的模板来说已有热加载保障了更新
        config.setTemplateUpdateDelay(999999);
        // - Set an error handler that prints errors so they are readable with
        //   a HTML browser.
        // config.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        
        // - Use beans wrapper (recommmended for most applications)
        config.setObjectWrapper(ObjectWrapper.BEANS_WRAPPER);
        // - Set the default charset of the template files
        config.setDefaultEncoding(encoding);		// config.setDefaultEncoding("ISO-8859-1");
        // - Set the charset of the output. This is actually just a hint, that
        //   templates may require for URL encoding and for generating META element
        //   that uses http-equiv="Content-type".
        config.setOutputEncoding(encoding);			// config.setOutputEncoding("UTF-8");
        // - Set the default locale
        config.setLocale(Locale.getDefault() /* Locale.CHINA */ );		// config.setLocale(Locale.US);
        config.setLocalizedLookup(false);
        
        // 去掉int型输出时的逗号, 例如: 123,456
        // config.setNumberFormat("#");		// config.setNumberFormat("0"); 也可以
        config.setNumberFormat("#0.#####");
        config.setDateFormat("yyyy-MM-dd");
        config.setTimeFormat("HH:mm:ss");
        config.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
		return config;
	}
	
	private static void initStringTemplateLoader(StringTemplateLoader loader) {
		// text 文本消息
		loader.putTemplate(OutTextMsg.class.getSimpleName(), OutTextMsg.TEMPLATE);
		// news 图文消息
		loader.putTemplate(OutNewsMsg.class.getSimpleName(), OutNewsMsg.TEMPLATE);
		// image 图片消息
		loader.putTemplate(OutImageMsg.class.getSimpleName(), OutImageMsg.TEMPLATE);
		//voice 语音消息
		loader.putTemplate(OutVoiceMsg.class.getSimpleName(), OutVoiceMsg.TEMPLATE);
		// video 视频消息
		loader.putTemplate(OutVideoMsg.class.getSimpleName(), OutVideoMsg.TEMPLATE);
	}
	
	public static void setEncoding(String encoding) {
		OutMsgXmlBuilder.encoding = encoding;
	}
	
	public static String getEncoding() {
		return encoding;
	}

}






