package com.dp.auth.api;

/**
 * @Description :   //描述
 * @Author : Liwang  //作者
 * @Date: 2020-03-30 10:54  //时间
 *
 * 将 token、AppID、时间戳拼接到 URL 中，形成新的 URL；
 * 解析 URL，得到 token、AppID、时间戳等信息。
 */
public class ApiRequest {

    private String baseUrl;
    private String token;
    private String appId;
    private long timestamp;

    public ApiRequest(String baseUrl, String token, String appId, long timestamp) {
        this.baseUrl = baseUrl;
        this.token = token;
        this.appId = appId;
        this.timestamp = timestamp;
    }

    public static ApiRequest buildFromUrl(String url) {
        UrlUtil.UrlEntity urlEntity = UrlUtil.parse(url);
        String baseUrl = urlEntity.baseUrl;
        String appId = urlEntity.getParam("appId");
        String token = urlEntity.getParam("token");
        long timestamp = Long.valueOf(urlEntity.getParam("timestamp"));
        ApiRequest apiRequest = new ApiRequest(baseUrl,appId,token,timestamp);
        return apiRequest;
    }

    public String getBaseUrl(){
        return this.baseUrl;
    }

    public String getToken(){
        return this.token;
    }

    public String getAppId(){
        return this.appId;
    }

    public long getTimestamp(){
        return this.timestamp;
    }
}
