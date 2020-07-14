package com.hyssop.framework.service.impl;

import com.hyssop.framework.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.URL;

/**
 * @Author jie.zhang
 * @create_time 2020/5/6 16:57
 * @updater
 * @update_time
 **/
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisTemplate<String, String> template;

    @Resource(name="redisTemplate")
    private ListOperations<String, String> listOps;

    @Override
    public void addLink(String userId, URL url) {
        listOps.leftPush(userId, url.toExternalForm());
    }

    @Override
    public String getLink(String userId){
        return listOps.leftPop(userId);
    }
}