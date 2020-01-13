package zhjie;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * 场景编程题：现在有一个小型面包店，其仓库的容量是有限的，聘请了少量的生产工人生产面包，顾客会购买面包，当仓库填满后，生产工人将停止生产，如果仓库中没有面包，前来购买面包的顾客将排队等待。
 * <p>
 * 上面的场景，大家是否有兴趣使用 java 对其进行编码实现？
 * <p>
 * 本题目标：典型的生产者消费者模式，考察对 Object.wait 与 Object.nofify 方法的运用与理解。
 *
 * @Author jie.zhang
 * @create_time 2019/12/18 9:56
 * @updater
 * @update_time
 **/
@Slf4j
public class BreadTest {
    private static BreadFactory<String> breadFactory = new BreadFactory<String>();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            final int t = i;
            Thread thread = new Thread(() -> {
                breadFactory.putBread(Thread.currentThread().getName()+"b" + t);
            });
            thread.start();
            new Thread(() -> {
                breadFactory.getBread();
            }).start();
        }

    }

    private static class BreadFactory<T> {
        private int DEFAULT_LEN = 10;
        private List<T> a = new ArrayList();
        public Semaphore mutex =new Semaphore(1);
        private Semaphore empty = new Semaphore(DEFAULT_LEN);
        private Semaphore full = new Semaphore(1);

        public T getBread() {
            T result = null;
            try {
                full.acquire();
                mutex.acquire();
                result = a.get(0);
                a.remove(0);
                log.info("拿走了一个这样的面包：{}", result);
                mutex.release();
                empty.release();
            } catch (Exception e) {
                log.error("", e);
            } finally {
                return result;
            }
        }

        public void putBread(T bread) {
            try {
                empty.acquire();
                mutex.acquire();
                a.add(bread);
                log.info("添加了一个这样的面包：{}", bread);
                mutex.release();
                full.release();
            } catch (Exception e) {
                log.error("", e);
            } finally {
            }
        }
    }

}