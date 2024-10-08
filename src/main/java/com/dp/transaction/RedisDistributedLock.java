package com.dp.transaction;


/**
 * @author LiWang
 * @description: redis 分布式锁
 * @date 2024/9/27 10:39
 */
public class RedisDistributedLock {

    private RedisDistributedLock() {

    }

    public static RedisDistributedLock getSingletonIntance() {
        return InnerClass.instance;
    }

    public boolean lockTransction(String id) {
        return false;
    }

    public void unlockTransction(String id) {
    }


    private static class InnerClass {
        private static final RedisDistributedLock instance = new RedisDistributedLock();
    }
}
