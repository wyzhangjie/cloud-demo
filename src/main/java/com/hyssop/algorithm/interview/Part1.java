package com.hyssop.algorithm.interview;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Description:   两个线程交替打印数据
 * @Author:         zhjie.zhang
 * @CreateDate:     2019/7/22$ 11:09$
 * @UpdateUser:     zhjie.zhang
 * @UpdateDate:     2019/7/22$ 11:09$
 * @Version:        1.0
 */
public class Part1 {
    private static volatile int i = 0;
    private static ExecutorService executor = Executors.newFixedThreadPool(100);
    private static Semaphore semaphore = new Semaphore(1);
    private static Object oddAndEven = new Object();
    private static int state = 1;

    public static class OddTask1 implements Runnable {
        private String name = "";

        public OddTask1(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                synchronized (oddAndEven) {
                    while (i < 100) {
                        if (state != 1) {
                            try {
                                oddAndEven.wait();
                            } catch (Exception e) {

                            }
                        }
                        i++;
                        System.out.println(this.name + "---------" + i);
                        state = 2;
                        oddAndEven.notifyAll();
                    }

                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }

    public static class EvenTask2 implements Runnable {
        private String name = "";

        public EvenTask2(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {

                synchronized (oddAndEven) {
                    while (i < 100) {
                        if (state != 2) {
                            try {
                                oddAndEven.wait();
                            } catch (Exception e) {

                            }

                        }
                        i++;
                        System.out.println(this.name + "---------" + i);
                        state = 1;
                        oddAndEven.notifyAll();
                    }

                }


            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }


    public static void main(String[] args) throws Exception {
        OddTask1 myTask1 = new OddTask1("mytask1");
        EvenTask2 myTask2 = new EvenTask2("mytask2");
        Thread thread1 = new Thread(myTask1);
        thread1.start();
        Thread thread2 = new Thread(myTask2);
        thread2.start();
        thread1.join();
        thread2.join();

    }
}
