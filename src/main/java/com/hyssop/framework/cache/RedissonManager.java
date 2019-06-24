package com.hyssop.framework.cache;

import org.redisson.Redisson;
import org.redisson.config.Config;

/**
 * Created by zhjie.zhang on 2019/5/7.
 */
public class RedissonManager {
    private static Config config = new Config();
    //声明redisso对象
    private static Redisson redisson = null;

    //实例化redisson
    static {

        config.useSingleServer().setAddress("10.93.13.28:6379");
        //得到redisson对象
        redisson = (Redisson) Redisson.create(config);

    }

    //获取redisson对象的方法
    public static Redisson getRedisson() {
        return redisson;
    }
}
