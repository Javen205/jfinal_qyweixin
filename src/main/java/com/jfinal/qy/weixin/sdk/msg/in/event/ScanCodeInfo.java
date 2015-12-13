package com.jfinal.qy.weixin.sdk.msg.in.event;

/**
 * 菜单二维码扫描的结果实体
 * @author L.cm
 */
public class ScanCodeInfo {
	
	private String ScanType;
	private String ScanResult;
	
	public ScanCodeInfo(String scanType, String scanResult) {
		super();
		ScanType = scanType;
		ScanResult = scanResult;
	}
	public String getScanType() {
		return ScanType;
	}
	public void setScanType(String scanType) {
		ScanType = scanType;
	}
	public String getScanResult() {
		return ScanResult;
	}
	public void setScanResult(String scanResult) {
		ScanResult = scanResult;
	}
	
}
