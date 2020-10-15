package com.hyssop.framework.redis;

/**
 * @Author jie.zhang
 * @create_time 2020/7/9 20:30
 * @updater
 * @update_time
 **/
public interface DistributedLock {
    long LOCK_EXPIRE = 60000L;
    long LOCK_RETRY_TIME_INTERVAL = 500L;
    int LOCK_RETRY_TIMES = 120;

    boolean lock(String key);

    boolean lock(String key, long expire);

    boolean lock(String key, long retryInterval, int retryTimes);

    boolean lock(String key, long expire, long retryInterval, int retryTimes);

    boolean unlock(String key);

    default long getExpire() {
        return 60000L;
    }

    default long getRetryInterval() {
        return 500L;
    }

    default int getRetryTimes() {
        return 120;
    }
}
