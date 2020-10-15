package base;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 如何用 Condition 实现生产者消费者模式
 *
 * @Author jie.zhang
 * @create_time 2020/7/7 15:17
 * @updater
 * @update_time
 **/
@Slf4j
public class MyBlockingQueueForCondition {
    Queue<Object> queue;
    private int maxLen = 16;
    private ReentrantLock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public MyBlockingQueueForCondition(int maxLen) {
        this.maxLen = maxLen;
        queue = new LinkedList<>();
    }

    public void put() throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == maxLen) {
                notFull.await();
            }
            queue.add(new Object());
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void get()  throws InterruptedException{

        lock.lock();
        try{
            while(queue.size()<=0){
                notEmpty.wait();
            }
            queue.poll();
            notFull.signalAll();
        }finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        MyBlockingQueueForCondition condition = new MyBlockingQueueForCondition(16);
        Runnable producer = () -> {
            while (true) {
                try {
                    condition.put();
                    log.info("放了一个对象");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(producer).start();
        new Thread(producer).start();

        Runnable consumer = () -> {
            while (true) {
                try {
                    condition.get();
                    log.info("取出来一个对象");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(consumer).start();
        new Thread(consumer).start();
    }

}