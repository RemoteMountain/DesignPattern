package com.dp.auth.api;

/**
 * @Description :   //描述
 * @Author : Liwang  //作者
 * @Date: 2020-03-30 11:10  //时间
 */
public class DefaultApiAuthenticatorImpl implements ApiAuthenticator {

    private CredentialStorage credentialStorage;

    public DefaultApiAuthenticatorImpl() {
        this.credentialStorage = new MysqlCredentialStorage();
    }

    public DefaultApiAuthenticatorImpl(CredentialStorage credentialStorage) {
        this.credentialStorage = credentialStorage;
    }

    @Override
    public void auth(String url) {
        ApiRequest apiRequest = ApiRequest.buildFromUrl(url);
        auth(apiRequest);
    }

    @Override
    public void auth(ApiRequest apiRequest) {
        String baseUrl = apiRequest.getBaseUrl();
        String appId = apiRequest.getAppId();
        String token = apiRequest.getToken();
        long timestamp = apiRequest.getTimestamp();
        AuthToken clientAuthToken = new AuthToken(token, timestamp);
        if (clientAuthToken.isExpired()) {
            throw new RuntimeException("Token is expired.");
        }
        String password = credentialStorage.getPasswordByAppId(appId);
        AuthToken serverAuthToken = AuthToken.generate(baseUrl, appId, password, timestamp);
        if (!serverAuthToken.match(clientAuthToken)) {
            throw new RuntimeException("Token verfication failed.");
        }
    }
}
