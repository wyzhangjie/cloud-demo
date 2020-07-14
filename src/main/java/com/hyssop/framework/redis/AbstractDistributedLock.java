package com.hyssop.framework.redis;

/**
 * @Author jie.zhang
 * @create_time 2020/7/9 20:30
 * @updater
 * @update_time
 **/
public abstract class AbstractDistributedLock implements DistributedLock {
    public AbstractDistributedLock() {
    }

    public boolean lock(String key) {
        return this.lock(key, this.getExpire());
    }

    public boolean lock(String key, long expire) {
        return this.lock(key, this.getRetryInterval(), this.getRetryTimes());
    }

    public boolean lock(String key, long retryInterval, int retryTimes) {
        return this.lock(key, this.getExpire(), retryInterval, retryTimes);
    }
}
