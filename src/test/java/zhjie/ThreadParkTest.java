package zhjie;

import java.util.concurrent.locks.LockSupport;

/**
 * @Author jie.zhang
 * @create_time 2019/12/3 17:34
 * @updater
 * @update_time
 **/
public class ThreadParkTest {
    public static void main(String[] args) {

      /*  System.out.println("start");
        // 毫微秒 单位是  毫微秒
        LockSupport.parkNanos(1000000000);
        System.out.println("end");*/
//一开始会block线程，直到给定时间过去后才往下走

      /*  System.out.println("start");
        LockSupport.unpark(Thread.currentThread());
        LockSupport.parkNanos(10000000000L);
        System.out.println("end");*/
//不会block，因为一开始给了一个permit

        System.out.println("start");
        LockSupport.unpark(Thread.currentThread());
        LockSupport.unpark(Thread.currentThread());
        LockSupport.parkNanos(1000000000);
        System.out.println("inter");
        LockSupport.parkNanos(10000000000L);
        System.out.println("end");
//第一个park不会block，第2个会，因为permit不会因为多次调用unpark就积累
    }
}