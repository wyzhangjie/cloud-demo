package com.hyssop.framework.redis;

/**
 * @Author jie.zhang
 * @create_time 2020/7/9 20:32
 * @updater
 * @update_time
 **/
public class DistributedLockException extends RuntimeException {
    private String key;

    public DistributedLockException() {
    }

    public DistributedLockException(String key, String message) {
        super(message);
        this.key = key;
    }

    public DistributedLockException(String message) {
        super(message);
    }

    public DistributedLockException(Throwable cause) {
        super(cause);
    }

    public DistributedLockException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getKey() {
        return this.key;
    }
}
