package zhjie;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author jie.zhang
 * @create_time 2019/12/19 10:12
 * @updater
 * @update_time
 **/
@Slf4j
public class BreadTest2<T> {
    private static BreadFactory<String> breadFactory = new BreadFactory<String>();
    private static int CUSTOMER_NUM_PER_TIME= 5;
    private static int PROJECT_NUM_PER_TIME =5;
    private static Product product = new Product();
    private static Customer customer = new Customer();

    public static void main(String[] args) {
        for(int i=0;i<5;i++){
            new Thread(product).start();
            new Thread(customer).start();
        }
    }

    public static class Product implements Runnable {

        @Override
        public void run() {
            for(int i=0;i<PROJECT_NUM_PER_TIME;i++){
                breadFactory.putBread(Thread.currentThread().getName()+"_"+i);
            }

        }
    }

    public static class Customer implements Runnable {

        @Override
        public void run() {
            for(int i=0;i<CUSTOMER_NUM_PER_TIME;i++){
                breadFactory.getBread();
            }

        }
    }

    private static class BreadFactory<T> {
       private int CAPACITY = 10;
        private List<T> a = new ArrayList();

        public T getBread() {
            synchronized (a) {
                    try {
                        while (a.size() <= 0) {
                            a.wait();
                        }

                    } catch (Exception e) {
                        //异常不要被吞掉
                        e.printStackTrace();
                    }

                }

            T result = a.get(0);
            a.remove(0);
            a.notifyAll();
            log.info("拿走了一个这样的面包：{}", result);
            return result;
        }

        public void putBread(T bread) {
            synchronized (a) {
                    try {
                        while (a.size() == CAPACITY) {
                            a.wait();
                        }

                    } catch (Exception e) {
                        //异常不要被吞掉
                        e.printStackTrace();
                    }

                a.add(bread);
                a.notifyAll();
                log.info("添加了一个这样的面包：{}", bread);
            }
        }
    }
}