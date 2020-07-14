package com.hyssop.framework.redis;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author jie.zhang
 * @create_time 2020/5/6 15:48
 * @updater
 * @update_time
 **/
@Slf4j
@Data
public class Receiver {

    private AtomicInteger counter = new AtomicInteger();

    public void receiveMessage(String message) {
        log.info("Received <" + message + ">");
        counter.incrementAndGet();
    }

    public int getCount() {
        return counter.get();
    }
}