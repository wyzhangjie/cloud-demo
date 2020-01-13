package com.hyssop.framework.mq.consumer;


import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

public class ConsumerDemo {

    public static void consume() throws MQClientException {
        // 1、设置分组
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("demo-group-1");

        // 2、服务器集群的ip地址及端口号
        consumer.setNamesrvAddr("10.22.31.103:9876;10.22.31.116:9876");
        // 3、设置实例名称
        consumer.setInstanceName("demo-consumer-1");
        consumer.subscribe("demo-topic-1", "demo-tag-1");
//        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.setMessageModel(MessageModel.CLUSTERING);
//        consumer.setConsumeTimeout();
        // 4、设置监听器
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                int index = -1;
                for (MessageExt msg : msgs) {
                    index++;
                    try {
                        System.out.println(msg.getMsgId() + "---" + new String(msg.getBody()));
                    } catch (Exception e) {
                        e.printStackTrace();
                        context.setAckIndex(index);
                        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                    }
                }
                //返回值表示消费状态   1.消费成功   2.消费失败
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        // 4、启动
        consumer.start();
        System.out.println("Consumer Started.");
    }
}
