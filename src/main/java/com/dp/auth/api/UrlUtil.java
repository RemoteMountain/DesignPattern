package com.dp.auth.api;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description :   //描述
 * @Author : Liwang  //作者
 * @Date: 2020-03-30 11:32  //时间
 */
public class UrlUtil {

    public static class UrlEntity {
        /**
         * 基础url
         */
        public String baseUrl;
        /**
         * url参数
         */
        public Map<String, String> params;

        public String getParam(String key) {
            return params.get(key);
        }
    }

    /**
     * 解析url
     *
     * @param url
     * @return
     */
    public static UrlEntity parse(String url) {
        UrlEntity entity = new UrlEntity();
        if (url == null) {
            return entity;
        }
        url = url.trim();
        if (url.equals("")) {
            return entity;
        }
        String[] urlParts = url.split("\\?");
        //entity.baseUrl = urlParts[0];
        //没有参数
        if (urlParts.length == 1) {
            entity.baseUrl = urlParts[0];
            return entity;
        }
        //有参数
        String[] params = urlParts[1].split("&");
        entity.params = new HashMap<>();
        String urlPart = urlParts[0];
        String urlParms = "";
        for (String param : params) {
            String[] keyValue = param.split("=");
            String key = keyValue[0];
            String value = keyValue[1];
            if (!"appId".equals(key) && !"token".equals(key) && !"timestamp".equals(key)) {
                urlParms += key + "=" + value + "&";
            }
            entity.params.put(keyValue[0], keyValue[1]);
        }
        String baseUrl =  urlPart+"?"+urlParms;
        baseUrl =  baseUrl.substring(0,baseUrl.length()-1);
        entity.baseUrl = baseUrl;
        return entity;
    }

    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {
        UrlEntity entity = parse("http://www.123.com?id=1&name=小明&token=1&appId=1&timestamp=1");
        System.out.println(entity.baseUrl + "\n" + entity.params);
    }
}