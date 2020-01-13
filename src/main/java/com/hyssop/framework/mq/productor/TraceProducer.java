package com.hyssop.framework.mq.productor;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @Author jie.zhang
 * @create_time 2019/8/30 20:44
 * @updater
 * @update_time
 **/
public class TraceProducer {
    public static void main(String[] args) throws MQClientException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("zjgroup",true);      // @1
        producer.setNamesrvAddr("10.22.31.103:9876:9876");
        producer.start();
        for (int i = 0; i < 10; i++)
            try {
                {
                    Message msg = new Message("TopicTest",
                            "TagA",
                            "OrderID188",
                            "Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));
                    SendResult sendResult = producer.send(msg);
                    System.out.printf("%s%n", sendResult);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        producer.shutdown();
    }
}