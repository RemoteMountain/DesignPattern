package com.dp.pattern.creative.singleton;

/**
 * @author LiWang
 * @description: 饿汉式单例模式
 * @date 2024/9/26 19:35
 */
public class EagerSingleton {

    private static final EagerSingleton instance = new EagerSingleton();

    private EagerSingleton() {
    }

    public static EagerSingleton getInstance() {
        return instance;
    }
}
