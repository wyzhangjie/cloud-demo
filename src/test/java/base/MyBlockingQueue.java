package base;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author jie.zhang
 * @create_time 2020/7/7 15:48
 * @updater
 * @update_time
 **/
@Slf4j
public class MyBlockingQueue {
    LinkedList<Object> queue;
    private int maxLen = 16;

    public MyBlockingQueue(int maxLen) {
        this.maxLen = maxLen;
        queue = new LinkedList<>();
    }

    public synchronized void put() throws InterruptedException {

        while (queue.size() == maxLen) {
            wait();
        }
        queue.add(new Object());
        notifyAll();
    }

    public synchronized void get() throws InterruptedException {

        while (queue.size() <= 0) {
            wait();
        }
        Object getOne = queue.poll();
        notifyAll();


    }

    public static void main(String[] args) {
        MyBlockingQueue condition = new MyBlockingQueue(16);
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