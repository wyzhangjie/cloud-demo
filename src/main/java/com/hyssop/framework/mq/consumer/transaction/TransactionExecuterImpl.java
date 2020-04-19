package com.hyssop.framework.mq.consumer.transaction;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @Author jie.zhang
 * @create_time 2019/8/10 17:32
 * @updater
 * @update_time
 **/
public class TransactionExecuterImpl implements TransactionListener {

    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        return null;
    }


    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        return null;
    }
}