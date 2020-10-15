package com.hyssop.framework.redisson;

import org.redisson.Redisson;
import org.redisson.config.Config;

/**
 * @Author jie.zhang
 * @create_time 2019/8/9 20:19
 * @updater
 * @update_time
 **/
public class RedissonManager {
    private static Config config = new Config();
    //声明redisso对象
    private static Redisson redisson;
    //实例化redisson
    static{
        config.useSingleServer().setAddress("redis://10.22.31.103:7000").setPassword("ug33KaQGFfFl4YUk");
        //得到redisson对象
        redisson = (Redisson) Redisson.create(config);

    }

    //获取redisson对象的方法
    public static Redisson getRedisson(){
        return redisson;
    }
}