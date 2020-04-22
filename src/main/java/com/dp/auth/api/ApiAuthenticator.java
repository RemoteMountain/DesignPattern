package com.dp.auth.api;

/**
 * @Description : 接口鉴权入口  //描述
 * @Author : Liwang  //作者
 * @Date: 2020-03-30 10:30  //时间
 *
 * 需求描述--> 功能点
 *
 * 1、把 URL、AppID、密码、时间戳拼接为一个字符串；
 * 2、对字符串通过加密算法加密生成 token；
 * 3、将 token、AppID、时间戳拼接到 URL 中，形成新的 URL；
 * 4、解析 URL，得到 token、AppID、时间戳等信息；
 * 5、从存储中取出 AppID 和对应的密码；
 * 6、根据时间戳判断 token 是否过期失效；
 * 7、验证两个 token 是否匹配；
 */
public interface ApiAuthenticator {

    void auth(String url);
    void auth(ApiRequest apiRequest);
}
