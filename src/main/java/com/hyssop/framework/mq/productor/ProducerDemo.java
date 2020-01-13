package com.hyssop.framework.mq.productor;


import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

public class ProducerDemo {

    public static void produce() throws MQClientException {
        // 1、设置分组
        DefaultMQProducer producer = new DefaultMQProducer("demo-group-1");
        // 2、服务器集群的ip地址及端口号
        producer.setNamesrvAddr("10.22.31.103:9876;10.22.31.116:9876");
        // 3、设置实例名称
        producer.setInstanceName("demo-producer-1");
        // 4、设置失败重试次数，默认16次，超过该次数到DLQ死信队列
        producer.setRetryTimesWhenSendFailed(6);
        producer.setVipChannelEnabled(false);
        // 5、启动
        producer.start();
        try {
            for (int i = 0; i < 10; i++) {
                // 每秒发送一次MQ
                Thread.sleep(2000);
                //new Message(String topic,String tags,byte[] body)
                // topic 主题名称
                Message message = new Message("demo-topic-1",
                        "demo-tag-1", // tag 临时值
                        ("mytopic-"+i).getBytes()// body 内容
                );
                //投递给broker
                producer.send(message, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        System.out.printf("%s%n", sendResult);
                        sendResult.getSendStatus();
                    }

                    @Override
                    public void onException(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.shutdown();
    }
}
