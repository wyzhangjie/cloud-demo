package com.hyssop.framework.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author jie.zhang
 * @create_time 2020/4/30 10:28
 * @updater
 * @update_time
 **/
@Component
@Slf4j
public class RedisUtils {
    @Autowired
    protected RedisTemplate redisTemplate;

    /**
     * 放重复，短暂的时间内有两笔请求过来，一个成功一个必然要失败
     */
    public void voidReputable(String key, String value1, Double value2, final long timeout, final TimeUnit unit) {
        if (redisTemplate.opsForZSet().add(key, value1, value2)) {
            //进入到这里有可能有一个单子成功了
            //做业务
            //这里面有一个坑，就是double不一定返回的是double ,redis 底层有转换，所以要根据类型做转化
            String classType = redisTemplate.opsForHash().get(key,value1).getClass().getTypeName();
            log.info("classType={}",classType);
            long tep;
            if(classType.equals(Integer.class.getName())){
                tep = Long.valueOf((int) redisTemplate.opsForHash().get(key,value1));
            }else{
                tep = (long) redisTemplate.opsForHash().get(key,value1);
            }
        } else {
            //重复请求过滤掉

        }

    }
}