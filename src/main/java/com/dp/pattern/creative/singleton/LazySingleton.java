package com.dp.pattern.creative.singleton;

/**
 * @author LiWang
 * @description: TODO
 * @date 2024/9/26 19:38
 */
public class LazySingleton {

    private static LazySingleton instance;

    private LazySingleton() {
    }

    public synchronized static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }


}
