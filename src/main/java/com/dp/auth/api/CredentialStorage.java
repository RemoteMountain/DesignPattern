package com.dp.auth.api;

/**
 * @Description :   //描述
 * @Author : Liwang  //作者
 * @Date: 2020-03-30 10:59  //时间
 */
public interface CredentialStorage {

    String getPasswordByAppId(String appId);
}
