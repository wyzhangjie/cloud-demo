package base.fanxing;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author jie.zhang
 * @create_time 2020/7/8 11:02
 * @updater
 * @update_time
 **/
public class RunDemo1 {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            final int no = i + 1;
            Runnable runnable = () -> {
                try {
                    Thread.sleep((long) (Math.random() * 10000));
                    System.out.println(no + "号运动员完成了比赛。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            };
            service.submit(runnable);
        }
        System.out.println("等待5个运动员都跑完.....");
        latch.await();
        System.out.println("所有人都跑完了，比赛结束。");
    }
}