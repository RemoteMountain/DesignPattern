package com.dp.pattern.creative.singleton;

/**
 * @author LiWang
 * @description: 静态内部类单例
 * @date 2024/9/26 20:09
 */
public class HolderSingleton {

    private HolderSingleton() {
    }

    public static HolderSingleton getInstance() {
        return InnerClass.instance;
    }

    private static class InnerClass {
        private static final HolderSingleton instance = new HolderSingleton();
    }
}
