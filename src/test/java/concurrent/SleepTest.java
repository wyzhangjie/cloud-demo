package concurrent;

/**
 * @Author jie.zhang
 * @create_time 2020/1/15 10:00
 * @updater
 * @update_time
 **/
public class SleepTest {
    public void test(){
        synchronized (this){
            System.out.println("11111");
        }
    }

    public static void main(String[] args) throws Exception{
        SleepTest sleepTest = new SleepTest();
        sleepTest.test1();
    }
    public void test2(){
        SleepTest sleepTest = new SleepTest();
        Thread mianThreand = Thread .currentThread();
        System.out.println("1");
        Thread a = new Thread(() -> {
            Thread.currentThread().getId();

            synchronized (mianThreand){
                try {
                    System.out.println("2");
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("3");
            sleepTest.test();
            System.out.println("4");
        });
        a.start();
        System.out.println("5");
        //      Thread.sleep(10000);
        System.out.println("6");
        a.interrupt();
        System.out.println("7");
    }
    public void test1(){
        SleepTest sleepTest = new SleepTest();
        Thread mianThreand = Thread .currentThread();
        System.out.println("1");
        Thread a = new Thread(() -> {
            Thread.currentThread().getId();

            synchronized (sleepTest){
                try {
                    System.out.println("2");
                    Thread.sleep(500);
                    sleepTest.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("3");
            sleepTest.test();
            System.out.println("4");
        });
        a.start();

        synchronized (sleepTest){
            try {
                System.out.println("进到了主线程的锁对象里面");
                sleepTest.wait();
            //    Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("5");
        //      Thread.sleep(10000);
        System.out.println("6");
        System.out.println("7");
    }
}