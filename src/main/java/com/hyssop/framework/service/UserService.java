package com.hyssop.framework.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

/**
 * Created by zhjie.zhang on 2019/5/7.
 */
@Service
public class UserService {


    @Async
    public void sendSms() {
        System.out.println("####sendSms####   2");
        IntStream.range(0, 5).forEach(d -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("####sendSms####   3");
    }
}
