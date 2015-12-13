package com.jfinal.qy.weixin.sdk.api.media;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.jfinal.kit.HttpKit;
import com.jfinal.qy.weixin.sdk.api.AccessTokenApi;
import com.jfinal.qy.weixin.sdk.api.ApiResult;
import com.jfinal.qy.weixin.sdk.utils.HttpUtils;
import com.jfinal.qy.weixin.sdk.utils.JsonUtils;

/**
 * 素材管理
 * @author Javen
 * 文档：http://qydev.weixin.qq.com/wiki/index.php?title=%E4%B8%8A%E4%BC%A0%E4%B8%B4%E6%97%B6%E7%B4%A0%E6%9D%90%E6%96%87%E4%BB%B6
 */
public class MediaApi {
	
	/**
	 * 上传的临时多媒体文件有格式
	 * 用于上传图片、语音、视频等媒体资源文件以及普通文件（如doc，ppt）
	 */
	public static enum MediaType {
		IMAGE, VOICE, VIDEO, FILE;
		
		// 转化成小写形式
		public String get() {
			return this.name().toLowerCase();
		}
	}
	
	// 新增临时素材
	private static String upload_url = "https://qyapi.weixin.qq.com/cgi-bin/media/upload?access_token=";
	
	/**
	 * 上传临时素材
	 * @param mediaType 上传的临时多媒体文件有格式
	 * @param file 需要上传的文件
	 * @return ApiResult 
	 */
	public static ApiResult uploadMedia(MediaType mediaType, File file) {
		String url = upload_url + AccessTokenApi.getAccessTokenStr() + "&type=" + mediaType.get();
		String jsonStr = HttpUtils.upload(url, file, null);
		return new ApiResult(jsonStr);
	}
	
	
	private static String get_url = "https://qyapi.weixin.qq.com/cgi-bin/media/get?access_token=";
	
	/**
	 * 获取临时素材
	 * @param media_id 素材Id
	 * @return MediaFile
	 */
	public static MediaFile getMedia(String media_id) {
		String url = get_url + AccessTokenApi.getAccessTokenStr() + "&media_id=" + media_id;
		return HttpUtils.download(url);
	}
	
	private static String add_news_url = "https://qyapi.weixin.qq.com/cgi-bin/material/add_mpnews?access_token=";
	
	/**
	 * 上传永久图文素材
	 * @param mediaMpNews 图文列表
	 * @return ApiResult
	 */
	public static ApiResult addNews(MediaMpNews mediaMpNews) {
		String url = add_news_url + AccessTokenApi.getAccessTokenStr();
		
		String jsonResult = HttpKit.post(url, JsonUtils.toJson(mediaMpNews));
		return new ApiResult(jsonResult);
	}
	
	private static String addMaterialUrl = "https://qyapi.weixin.qq.com/cgi-bin/material/add_material?agentid=AGENTID&type=TYPE&access_token=ACCESS_TOKEN";
	/**
	 * 上传其他类型永久素材
	 * @return ApiResult
	 */
	public static ApiResult addMaterial(File file,String agentid,MediaType type) {
		String url = addMaterialUrl.replace("AGENTID", agentid).replace("TYPE", type.get()).replace("ACCESS_TOKEN",AccessTokenApi.getAccessTokenStr());
		String jsonResult = HttpUtils.upload(url, file, null);
		return new ApiResult(jsonResult);
	}
	
	
	// 删除永久素材
	private static String del_material_url = "https://qyapi.weixin.qq.com/cgi-bin/material/del?access_token=ACCESS_TOKEN&agentid=AGENTID&media_id=MEDIA_ID";
	/**
	 * 删除永久素材
	 * @param media_id 要获取的素材的media_id
	 * @return ApiResult 返回信息
	 */
	public static ApiResult delMaterial(String media_id,String agentid) {
		String url = del_material_url.replace("AGENTID", agentid).replace("MEDIA_ID", media_id).replace("ACCESS_TOKEN",AccessTokenApi.getAccessTokenStr());
		
		String jsonResult = HttpKit.get(url);
		return new ApiResult(jsonResult);
	}
	
	private static String update_news_url = "https://api.weixin.qq.com/cgi-bin/material/update_news?access_token=";
	
	/**
	 * 修改永久图文素材
	 * @param media_id 要修改的图文消息的id
	 * @param index 要更新的文章在图文消息中的位置（多图文消息时，此字段才有意义），第一篇为0
	 * @param mediaArticles 图文素材
	 * @return ApiResult 返回信息
	 */
	public static ApiResult updateNews(MediaMpNews mediaMpNews) {
		String url = update_news_url + AccessTokenApi.getAccessTokenStr();
		String jsonResult = HttpKit.post(url, JsonUtils.toJson(mediaMpNews));
		return new ApiResult(jsonResult);
	}
	
	// 获取素材总数
	private static String get_materialcount_url = "https://qyapi.weixin.qq.com/cgi-bin/material/get_count?access_token=ACCESS_TOKEN&agentid=AGENTID";

	/**
	 * 获取素材总数
	 * @return ApiResult 返回信息
	 */
	public static ApiResult getMaterialCount(String agentid) {
		String url = get_materialcount_url.replace("ACCESS_TOKEN", AccessTokenApi.getAccessTokenStr()).replace("AGENTID", agentid) ;
		System.out.println(url);
		String jsonResult = HttpKit.get(url);
		return new ApiResult(jsonResult);
	}
	
	// 获取素材列表
	private static String batchget_material_url = "https://qyapi.weixin.qq.com/cgi-bin/material/batchget_material?access_token=";
	
	/**
	 * 获取素材列表
	 * @param mediaType 素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）
	 * @param offset 从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
	 * @param count 返回素材的数量，取值在1到20之间
	 * @return ApiResult 返回信息
	 */
	public static ApiResult batchGetMaterial(MediaType mediaType, int offset, int count,int agentid) {
		String url = batchget_material_url + AccessTokenApi.getAccessTokenStr();
		
		if(offset < 0) offset = 0;
		if(count > 20) count = 20;
		if(count < 1) count = 1;
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("type", mediaType.get());
		dataMap.put("offset", offset);
		dataMap.put("count", count);
		dataMap.put("agentid", agentid);
		
		String jsonResult = HttpKit.post(url, JsonUtils.toJson(dataMap));
		return new ApiResult(jsonResult);
	}
	
}
