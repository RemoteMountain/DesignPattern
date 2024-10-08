package com.dp.transaction;

/**
 * @author LiWang
 * @description: 事务锁
 * @date 2024/9/27 11:24
 */
public class TransactionLock {

    public boolean lock(String id) {
        return RedisDistributedLock.getSingletonIntance().lockTransction(id);
    }

    public void unlock(String id) {
        RedisDistributedLock.getSingletonIntance().unlockTransction(id);
    }
}
