package com.dp.ipv4;

/**
 * @author LiWang
 * @description: IPv4地址转换
 * @date 2024/9/26 17:13
 */
public class IPv4Convertor {

    public static int convertIPv4AddressTo32BitString(String ip) {
        if (ip == null || ip.isEmpty()) {
            throw new IllegalArgumentException("IP address can not empty");
        }

        // 清理输入，去掉多余的空格
        String[] parts = ip.split("\\."); // 使用点分割字符串

        // 验证长度
        if (parts.length != 4) {
            throw new IllegalArgumentException("Invalid IP address format.");
        }

        int result = 0;

        for (int i = 0; i < parts.length; i++) {
            String part = parts[i].trim(); // 去掉前后的空格

            // 验证是否为数字，并转换为整数
            try {
                int num = Integer.parseInt(part);

                // 检查数值范围
                if (num < 0 || num > 255) {
                    throw new IllegalArgumentException("Each byte must be in the range 0-255.");
                }

                // 计算 32 位整数
                result += num * Math.pow(256, 3 - i); // 使用幂运算来计算位移
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid number in IP address.");
            }
        }

        return result;


    }

}
