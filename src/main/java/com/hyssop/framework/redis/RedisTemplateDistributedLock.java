package com.hyssop.framework.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author jie.zhang
 * @create_time 2020/7/9 20:29
 * @updater
 * @update_time
 **/
@Component("distributedLock")
public class RedisTemplateDistributedLock extends AbstractDistributedLock {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisTemplateDistributedLock.class);
    private static final Charset CHARSET_UTF8 = Charset.forName("UTF-8");
    private static final ThreadLocal<String> LOCK_VALUE = new ThreadLocal();
    private static final String UNLOCK_LUA = "if redis.call(\"get\", KEYS[1]) == ARGV[1] then     return redis.call(\"del\", KEYS[1]) else     return 0 end ";
    @Autowired
    private RedisTemplate redisTemplate;

    public RedisTemplateDistributedLock() {
    }

    public boolean lock(String key, long expire, long interval, int retry) {
        LOGGER.info("try lock key:{}, expire:{}, interval:{}, retry:{}", new Object[]{key, expire, interval, retry});
        boolean locked = this.doLockInRedis(key, expire);
        LOGGER.info("try lock key:{}, locked:{}", key, locked);

        for(; !locked && retry-- > 0; locked = this.doLockInRedis(key, expire)) {
            try {
                LOGGER.info("retry lock key:{}, interval:{}, retry:{}", new Object[]{key, interval, retry});
                LockSupport.parkUntil(key, System.currentTimeMillis() + interval);
            } catch (Exception var9) {
                LOGGER.info("retry lock failed, key:{}, expire:{}, interval:{}, retry:{}, exception:{}", new Object[]{key, expire, interval, retry, var9});
                return false;
            }
        }

        return locked;
    }

    private boolean doLockInRedis(final String key, final long expire) {
        try {
            RedisCallback<Boolean> callback = (connection) -> {
                String lockValue = UUID.randomUUID().toString();
                LOCK_VALUE.set(lockValue);
                return connection.set(key.getBytes(CHARSET_UTF8), lockValue.getBytes(CHARSET_UTF8), Expiration.seconds(TimeUnit.MILLISECONDS.toSeconds(expire)), RedisStringCommands.SetOption.SET_IF_ABSENT);
            };
            return (Boolean)this.redisTemplate.execute(callback);
        } catch (Exception var5) {
            LOGGER.error("do lock in redis failed, the exception is: ", var5);
            return false;
        }
    }

    public boolean unlock(String key) {
        LOGGER.info("try unlock key:{}, value:{}", key, LOCK_VALUE.get());

        Boolean unlocked;
        try {
            RedisCallback<Boolean> callback = (connection) -> {
                if (LOCK_VALUE.get() == null) {
                    throw new DistributedLockException(key, "分布式锁解锁异常, Lock Value 不能为空!");
                } else {
                    return (Boolean)connection.eval("if redis.call(\"get\", KEYS[1]) == ARGV[1] then     return redis.call(\"del\", KEYS[1]) else     return 0 end ".getBytes(), ReturnType.BOOLEAN, 1, new byte[][]{key.getBytes(CHARSET_UTF8), ((String)LOCK_VALUE.get()).getBytes(CHARSET_UTF8)});
                }
            };
            unlocked = (Boolean)this.redisTemplate.execute(callback);
        } finally {
            LOCK_VALUE.remove();
        }

        LOGGER.info("try unlock key:{}, unlocked:{}", key, unlocked);
        return unlocked;
    }
}
