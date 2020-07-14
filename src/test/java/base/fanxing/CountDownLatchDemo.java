package base.fanxing;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author jie.zhang
 * @create_time 2020/7/8 10:34
 * @updater
 * @update_time
 **/
public class CountDownLatchDemo {
    ExecutorService threadPool = Executors.newFixedThreadPool(3);

    public static void main(String[] args) throws InterruptedException {

        CountDownLatchDemo countDownLatchDemo = new CountDownLatchDemo();
        System.out.println(countDownLatchDemo.getPrices());

    }

    private Set<Integer> getPrices() throws InterruptedException {

        Set<Integer> prices = Collections.synchronizedSet(new HashSet<Integer>());
        CountDownLatch countDownLatch = new CountDownLatch(3);
        long begin = System.currentTimeMillis();
        threadPool.submit(new Task(123, prices, countDownLatch));
        threadPool.submit(new Task(456, prices, countDownLatch));
        threadPool.submit(new Task(789, prices, countDownLatch));

        countDownLatch.await(3, TimeUnit.SECONDS);
        System.out.println(System.currentTimeMillis()-begin);
        return prices;
    }

    private class Task implements Runnable {

        Integer productId;
        Set<Integer> prices;
        CountDownLatch countDownLatch;

        public Task(Integer productId, Set<Integer> prices,
                    CountDownLatch countDownLatch) {
            this.productId = productId;
            this.prices = prices;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            int price = 0;
            try {
                Thread.sleep((long) (Math.random() * 4000));
                price = (int) (Math.random() * 4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            prices.add(price);
            countDownLatch.countDown();
        }
    }
}