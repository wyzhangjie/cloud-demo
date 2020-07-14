package com.hyssop.framework.service;

import java.net.URL;

/**
 * @Author jie.zhang
 * @create_time 2020/5/6 16:59
 * @updater
 * @update_time
 **/
public interface RedisService {

     void addLink(String userId, URL url);
     String getLink(String userId);
}