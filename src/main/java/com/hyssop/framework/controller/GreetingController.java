package com.hyssop.framework.controller;

/**
 * @Author jie.zhang
 * @create_time 2020/5/6 14:51
 * @updater
 * @update_time
 **/
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import com.hyssop.framework.service.RedisService;
import com.hyssop.framework.vo.Greeting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class GreetingController {
    @Autowired
    private JedisConnectionFactory redisConnectionFactory;
    @Resource
    private RedisService redisService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
    @GetMapping("/")
    public Map<String, Object> greeting() {
        return Collections.singletonMap("message", "Hello, World");
    }
    @GetMapping("/redis")
    public Map<String,Object> getRedisInfo(@RequestParam(value = "name", defaultValue = "World") String name){
        log.info("Sending message...");
        stringRedisTemplate.convertAndSend("chat", name);
        try{
            redisService.addLink(name,new URL("http://www.baidu.com"));
            return Collections.singletonMap("message",redisService.getLink(name));
        }catch (Exception e){
            log.error("异常处理：",e);
        }

        return Collections.singletonMap("message", "send successfully!");
    }
}