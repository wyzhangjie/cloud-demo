package com.hyssop.framework.mq.productor;

import com.hyssop.framework.mq.consumer.domain.OrderPaidEvent;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * recketmq spring boot demo
 * 基于 RocketMQTemplate
 */
@Component
public class SpringbootProducerDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringbootProducerDemo.class);

    private static final String TX_PGROUP_NAME = "myTxProducerGroup";

    @Value("${example.rocketmq.transTopic}")
    private String stringTransTopic;

    @Value("${example.rocketmq.stringTopic}")
    private String stringTopic;

    @Value("${example.rocketmq.orderTopic}")
    private String orderPaidTopic;

    @Value("${example.rocketmq.msgExtTopic}")
    private String msgExtTopic;

    @Resource
    private RocketMQTemplate rocketMQTemplate;

//    @Resource(name = "extRocketMQTemplate")
//    private RocketMQTemplate extRocketMQTemplate;



    public void produce() {
        // Send string
        SendResult sendResult = rocketMQTemplate.syncSend(stringTopic, "Hello, World!");
        LOGGER.info("syncSend1 to topic: {}, sendResult: {}", stringTopic, sendResult);

        // Use the extRocketMQTemplate
//        sendResult = extRocketMQTemplate.syncSend(stringTopic, "Hello, World!");
//        System.out.printf("extRocketMQTemplate.syncSend1 to topic %s sendResult=%s %n", stringTopic, sendResult);

        // Send string with spring Message
        sendResult = rocketMQTemplate.syncSend(stringTopic, MessageBuilder.withPayload("Hello, World! I'm from spring message").build());
        LOGGER.info("syncSend2 to topic: {}, sendResult: {}", stringTopic, sendResult);

        // Send object with user-defined object by async
        OrderPaidEvent orderPaidEvent = new OrderPaidEvent();
        orderPaidEvent.setOrderId("111115555577");
        orderPaidEvent.setPaidMoney(new BigDecimal("5.66"));
//        LOGGER.info("the producer orderPaidEvent:{}", JSON.toJSONString(orderPaidEvent));
        rocketMQTemplate.asyncSend(orderPaidTopic, orderPaidEvent, new SendCallback() {
            public void onSuccess(SendResult sr) {
                LOGGER.info("async to topic: {}, sendResult: {}", orderPaidTopic, sr);
            }

            public void onException(Throwable throwable) {
                LOGGER.info("async to topic: {}, Throwable: {} ", orderPaidTopic, throwable);
            }
        });

        // Send message with special tag
        rocketMQTemplate.convertAndSend(msgExtTopic + ":example-tag-1", "I'm from example-tag-1");  // example-tag-1 will not be consumer-selected
        LOGGER.info("async to topic: {}, tag: {} ", msgExtTopic, "example-tag-1");
        rocketMQTemplate.convertAndSend(msgExtTopic + ":example-tag-2", "I'm from example-tag-2");
        LOGGER.info("async to topic: {}, tag: {} ", msgExtTopic, "example-tag-2");

//
//        // Send a batch of strings
//        testBatchMessages();
//
//        // Send transactional messages
//        testTransaction();
    }

    private void testBatchMessages() {
        List<Message> msgs = new ArrayList<Message>();
        for (int i = 0; i < 10; i++) {
            msgs.add(MessageBuilder.withPayload("Hello RocketMQ Batch Msg#" + i).
                    setHeader(RocketMQHeaders.KEYS, "KEY_" + i).build());
        }

        SendResult sr = rocketMQTemplate.syncSend(stringTopic, msgs, 60000);

        System.out.printf("--- Batch messages send result :" + sr);
    }


    private void testTransaction() throws MessagingException {
        String[] tags = new String[]{"TagA", "TagB", "TagC", "TagD", "TagE"};
        for (int i = 0; i < 10; i++) {
            try {

                Message msg = MessageBuilder.withPayload("Hello RocketMQ " + i).
                        setHeader(RocketMQHeaders.TRANSACTION_ID, "KEY_" + i).build();
                SendResult sendResult = rocketMQTemplate.sendMessageInTransaction(TX_PGROUP_NAME,
                        stringTransTopic + ":" + tags[i % tags.length], msg, null);
                System.out.printf("------ send Transactional msg body = %s , sendResult=%s %n",
                        msg.getPayload(), sendResult.getSendStatus());

                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @RocketMQTransactionListener(txProducerGroup = TX_PGROUP_NAME)
    class TransactionListenerImpl implements RocketMQLocalTransactionListener {
        private AtomicInteger transactionIndex = new AtomicInteger(0);

        private ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<String, Integer>();

        @Override
        public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
            String transId = (String)msg.getHeaders().get(RocketMQHeaders.TRANSACTION_ID);
            System.out.printf("#### executeLocalTransaction is executed, msgTransactionId=%s %n",
                    transId);
            int value = transactionIndex.getAndIncrement();
            int status = value % 3;
            localTrans.put(transId, status);
            if (status == 0) {
                // Return local transaction with success(commit), in this case,
                // this message will not be checked in checkLocalTransaction()
                System.out.printf("    # COMMIT # Simulating msg %s related local transaction exec succeeded! ### %n", msg.getPayload());
                return RocketMQLocalTransactionState.COMMIT;
            }

            if (status == 1) {
                // Return local transaction with failure(rollback) , in this case,
                // this message will not be checked in checkLocalTransaction()
                System.out.printf("    # ROLLBACK # Simulating %s related local transaction exec failed! %n", msg.getPayload());
                return RocketMQLocalTransactionState.ROLLBACK;
            }

            System.out.printf("    # UNKNOW # Simulating %s related local transaction exec UNKNOWN! \n");
            return RocketMQLocalTransactionState.UNKNOWN;
        }

        @Override
        public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
            String transId = (String)msg.getHeaders().get(RocketMQHeaders.TRANSACTION_ID);
            RocketMQLocalTransactionState retState = RocketMQLocalTransactionState.COMMIT;
            Integer status = localTrans.get(transId);
            if (null != status) {
                switch (status) {
                    case 0:
                        retState = RocketMQLocalTransactionState.UNKNOWN;
                        break;
                    case 1:
                        retState = RocketMQLocalTransactionState.COMMIT;
                        break;
                    case 2:
                        retState = RocketMQLocalTransactionState.ROLLBACK;
                        break;
                }
            }
            System.out.printf("------ !!! checkLocalTransaction is executed once," +
                            " msgTransactionId=%s, TransactionState=%s status=%s %n",
                    transId, retState, status);
            return retState;
        }
    }
}
