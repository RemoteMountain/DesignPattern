package com.dp.kiss;


import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validators {

    private static final String IP_REGEXP
            = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
            + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
            + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
            + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";

    private static final Pattern IP_PATTERN
            = Pattern.compile(IP_REGEXP);

    //Matcher 线程不安全，不应该在多线程间共享
    private static final Matcher IP_MATCHER = IP_PATTERN.matcher("");


    public static boolean isValidIpAddressV(String ipAddress) {
        if (ipAddress.length() == 0)
            return false;
        IP_PATTERN.matcher("").reset("");
        return IP_MATCHER.reset(ipAddress).matches();
    }

    // 第一种实现方式: 使用正则表达式
    public static boolean isValidIpAddressV0(String ipAddress) {
        if (ipAddress.length() == 0)
            return false;
        IP_PATTERN.matcher("").reset("");
        return IP_PATTERN.matcher(ipAddress).matches();
    }


    // 第一种实现方式: 使用正则表达式
    public static boolean isValidIpAddressV1(String ipAddress) {
        if (StringUtils.isBlank(ipAddress)) return false;
        String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
        return ipAddress.matches(regex);
    }

    // 第二种实现方式: 使用现成的工具类
    public static boolean isValidIpAddressV2(String ipAddress) {
        if (StringUtils.isBlank(ipAddress)) return false;
        String[] ipUnits = StringUtils.split(ipAddress, '.');
        if (ipUnits.length != 4) {
            return false;
        }
        for (int i = 0; i < 4; ++i) {
            int ipUnitIntValue;
            try {
                ipUnitIntValue = Integer.parseInt(ipUnits[i]);
            } catch (NumberFormatException e) {
                return false;
            }
            if (ipUnitIntValue < 0 || ipUnitIntValue > 255) {
                return false;
            }
            if (i == 0 && ipUnitIntValue == 0) {
                return false;
            }
        }
        return true;
    }

    // 第三种实现方式: 不使用任何工具类
    public static boolean isValidIpAddressV3(String ipAddress) {
        char[] ipChars = ipAddress.toCharArray();
        int length = ipChars.length;
        int ipUnitIntValue = -1;
        boolean isFirstUnit = true;
        int unitsCount = 0;
        for (int i = 0; i < length; ++i) {
            char c = ipChars[i];
            if (c == '.') {
                if (ipUnitIntValue < 0 || ipUnitIntValue > 255) return false;
                if (isFirstUnit && ipUnitIntValue == 0) return false;
                if (isFirstUnit) isFirstUnit = false;
                ipUnitIntValue = -1;
                unitsCount++;
                continue;
            }
            if (c < '0' || c > '9') {
                return false;
            }
            if (ipUnitIntValue == -1) ipUnitIntValue = 0;
            ipUnitIntValue = ipUnitIntValue * 10 + (c - '0');
        }
        if (ipUnitIntValue < 0 || ipUnitIntValue > 255) return false;
        if (unitsCount != 3) return false;
        return true;
    }


    public static void main(String[] args) {
        int num = 1000000;
        long start = System.currentTimeMillis();

        /*for (int i = 0; i < num; i++) {
            isValidIpAddressV("127.0.0.1"); //265
        }*/

        /*for (int i = 0; i < num; i++) {
            isValidIpAddressV0("127.0.0.1"); //317
        }*/

        /*for (int i = 0; i < num; i++) {
            isValidIpAddressV1("127.0.0.1"); //3027
        }*/

        for (int i = 0; i < num; i++) {
            isValidIpAddressV2("127.0.0.1"); //222
        }

        /*for (int i = 0; i < num; i++) {
            isValidIpAddressV3("127.0.0.1"); //35
        }*/



        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }



}
