package com.hyssop.framework.redisson;


import com.hyssop.framework.util.TaskExecutePool.ThreadPoolEnum;
import com.hyssop.algorithm.concurrent.InheritableThreadLocalService1;
import org.junit.Test;

import java.util.concurrent.ThreadPoolExecutor;


public class DistributedRedisLockTest {
    private ThreadPoolExecutor distributorExecutor;
    private static String key = "test123";
    @Test
    public void testAdd() {
        InheritableThreadLocal<Integer> requestIdThreadLocal = new InheritableThreadLocal<>();
        String key = "test123";
        //加锁
        DistributedRedisLock.acquire(key);
        //执行具体业务逻辑
        System.out.println("进入锁");
        //释放锁
        DistributedRedisLock.release(key);
        //返回结果

        distributorExecutor = ThreadPoolEnum.LINKED_EVENT_THREAD_POOL.getExecutor().get();

       /* for (int j = 0; j < 100; j++) {  // 模式10个请求，每个请求执行ControlThread的逻辑，其具体实现就是，先输出父线程的名称，
            //  然后设置本地环境变量，并将父线程名称传入到子线程中，在子线程中尝试获取在父线程中的设置的环境变量
            distributorExecutor.submit(new ControlThread(j));
        }*/

        InstanceVarThreadNotSafeEx notSafeEx = new InstanceVarThreadNotSafeEx();
        InstanceVarThreadNotSafeA safeA = new InstanceVarThreadNotSafeA(notSafeEx);
        safeA.start();
        InstanceVarThreadNotSafeB safeB = new InstanceVarThreadNotSafeB(notSafeEx);
        safeB.start();
        InstanceVarThreadNotSafeA safeA1 = new InstanceVarThreadNotSafeA(notSafeEx);
        safeA1.start();
        InstanceVarThreadNotSafeB safeB1 = new InstanceVarThreadNotSafeB(notSafeEx);
        safeB1.start();
    }

    class InstanceVarThreadNotSafeEx {
        int num = 0;
        String key = "test123";
        public void add(String username) {
            try {

                if ("a".equals(username)) {
                    num = 100;
                    System.out.println("a set over");
                    /*Thread.sleep(2000);*/
                } else {
                    num = 200;
                    System.out.println("b set over");
                }
                System.out.println(username + " num = " + num);

            } catch (Exception e) {
            }
        }
    }

    class InstanceVarThreadNotSafeA extends Thread {
        private InstanceVarThreadNotSafeEx safe;

        public InstanceVarThreadNotSafeA(InstanceVarThreadNotSafeEx safe) {
            this.safe = safe;
        }

        @Override
        public void run() {
            DistributedRedisLock.acquire(key);
            safe.add("a");
            DistributedRedisLock.release(key);
        }
    }

    class InstanceVarThreadNotSafeB extends Thread {
        private InstanceVarThreadNotSafeEx safe;

        public InstanceVarThreadNotSafeB(InstanceVarThreadNotSafeEx safe) {
            this.safe = safe;
        }

        @Override
        public void run() {
            DistributedRedisLock.acquire(key);
            safe.add("b");
            DistributedRedisLock.release(key);
        }
    }

    /**
     * 模拟Control任务
     */
    static class ControlThread implements Runnable {

        int i;

        public ControlThread(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ":" + ++i);

        }
    }

    @Test
    public void acquire() {
    }

    @Test
    public void release() {
    }

    public static void main(String[] args) {


    }
}
