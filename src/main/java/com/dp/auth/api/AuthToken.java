package com.dp.auth.api;

import sun.security.provider.SHA;

import java.util.Map;

/**
 * @Description :   //描述
 * @Author : Liwang  //作者
 * @Date: 2020-03-30 10:42  //时间
 *
 * 功能列表
 *
 * 1、把 URL、AppID、密码、时间戳拼接为一个字符串；
 * 2、对字符串通过加密算法加密生成 token；
 * 3、根据时间戳判断 token 是否过期失效；
 * 4、验证两个 token 是否匹配。
 *
 * 识别动词-方法候选
 * 识别名称-属性候选 ：URL、AppID、密码、时间戳
 *
 */
public class AuthToken {

    private static final long DEFAULT_EXPIRED_TIME_INTERVAL = 1*60*1000;
    private String token;
    private long createTime;
    private long expiredTimeInterval = DEFAULT_EXPIRED_TIME_INTERVAL;

    public AuthToken(String token, long createTime) {
        this.token = token;
        this.createTime = createTime;
    }

    public AuthToken(String token, long createTime, long expiredTimeInterval) {
        this.token = token;
        this.createTime = createTime;
        this.expiredTimeInterval = expiredTimeInterval;
    }



    public static AuthToken generate(String baseUrl, String appId, String password, long timestamp) {
        String fullUrl = AuthToken.buildFullUrl(baseUrl,appId,password,timestamp);
        String token = SHAUtil.sha(fullUrl);
        return new AuthToken(token, timestamp);
    }

    public static String buildFullUrl(String baseUrl, String appId, String password, long timestamp){
        StringBuilder builder = new StringBuilder(baseUrl);
        builder.append("?");
        builder.append("appId="+appId);
        builder.append("&");
        builder.append("password="+password);
        builder.append("&");
        builder.append("timestamp="+timestamp);
        return builder.toString();
    }

    public String getToken(){
        return this.token;
    }

    public boolean isExpired(){
        return System.currentTimeMillis()>(createTime+DEFAULT_EXPIRED_TIME_INTERVAL);
    }

    public boolean match(AuthToken authToken){
        return this.token.equals(authToken.getToken());
    }

}
