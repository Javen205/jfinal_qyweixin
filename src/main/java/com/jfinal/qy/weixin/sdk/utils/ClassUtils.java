package com.jfinal.qy.weixin.sdk.utils;

/**
 * 类工具
 * @author L.cm
 *
 */
public abstract class ClassUtils {
	
	/**
	 * 确定class是否可以被加载
	 * @param className
	 * @param classLoader
	 * @return
	 */
	public static boolean isPresent(String className, ClassLoader classLoader) {
		try {
			Class.forName(className, true, classLoader);
			return true;
		}
		catch (Throwable ex) {
			// Class or one of its dependencies is not present...
			return false;
		}
	}
	
}
