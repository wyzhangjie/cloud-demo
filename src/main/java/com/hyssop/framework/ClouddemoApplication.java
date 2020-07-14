package com.hyssop.framework;

import com.hyssop.framework.redis.Receiver;
import com.hyssop.framework.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.Resource;

/**
 * @author zhjie.zhang
 */
@SpringBootApplication
@MapperScan("com.hyssop.framework.repository")
@EnableAsync
@Slf4j
public class ClouddemoApplication {
    @Resource
    private RocketMQTemplate rocketMQTemplate;



    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(ClouddemoApplication.class, args);
        StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);
        Receiver receiver = ctx.getBean(Receiver.class);


    }
}

