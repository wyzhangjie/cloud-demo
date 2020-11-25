package com.hyssop.framework.clouddemo;

import com.hyssop.framework.ClouddemoApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.JedisCluster;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClouddemoApplication.class)
@Slf4j
public class RedisClusterConfigTest {
    @Autowired
    private JedisCluster jedisCluster;

    @Test
   public void test() {
        jedisCluster.set("userName", "zhangsan");
        String userName = jedisCluster.get("userName");
        System.out.println("userName======" + userName);
    }

}
