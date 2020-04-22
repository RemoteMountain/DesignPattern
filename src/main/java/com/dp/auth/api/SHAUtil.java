package com.dp.auth.api;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @Description :   //描述
 * @Author : Liwang  //作者
 * @Date: 2020-03-30 12:46  //时间
 */
public class SHAUtil {

    public static String sha(String msg) {
        MessageDigest sha256 = null;
        try {
            sha256 = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        sha256.update(msg.getBytes());
        byte[] sha256Bin = sha256.digest();
        byte[] base64 = Base64.getEncoder().encode(sha256Bin);
        return new String(base64).replace("=","等号");
    }
}
