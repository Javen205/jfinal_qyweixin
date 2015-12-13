package com.jfinal.qy.weixin.sdk.kit;

import java.util.HashMap;
import java.util.Map;

public class ParaMap {
	
	private Map<String, String> data = new HashMap<String, String>();
	private ParaMap() {}
	
	public static ParaMap create() {
		return new ParaMap();
	}
	
	public static ParaMap create(String key, String value) {
		return create().put(key, value);
	}
	
	public ParaMap put(String key, String value) {
		data.put(key, value);
		return this;
	}
	
	public Map<String, String> getData() {
		return data;
	}
}
