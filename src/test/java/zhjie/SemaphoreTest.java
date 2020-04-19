package zhjie;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author jie.zhang
 * @create_time 2019/11/30 15:16
 * @updater
 * @update_time
 **/
public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);
        for (int i = 0; i < 200; i++) {
            (new Thread(new Task(semaphore))).start();
        }
    }

    static class Task implements Runnable {
        private Semaphore semaphore;

        public Task(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            System.out.println("start");
            try {
                if (semaphore.tryAcquire(100, TimeUnit.MILLISECONDS)) {
                    System.out.println("获取到信号量，执行业务");
                    Thread.sleep(200L);
                    System.out.println("业务执行完成");
                }
            } catch (Throwable e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    }
}