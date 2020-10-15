package base;

/**第三种线程安全问题统称为活跃性问题，最典型的有三种，分别为死锁、活锁和饥饿。
 * @Author jie.zhang
 * @create_time 2020/7/7 16:28
 * @updater
 * @update_time
 **/
public class MayDeadLock {
    Object o1 = new Object();
    Object o2 = new Object();

    public void thread1() throws InterruptedException {
        synchronized (o1) {
            Thread.sleep(500);
            synchronized (o2) {
                System.out.println("线程1成功拿到两把锁");
            }
        }
    }

    public void thread2() throws InterruptedException {
        synchronized (o2) {
            Thread.sleep(500);
            synchronized (o1) {
                System.out.println("线程2成功拿到两把锁");
            }
        }
    }

    public static void main(String[] args) {
        MayDeadLock mayDeadLock = new MayDeadLock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mayDeadLock.thread1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mayDeadLock.thread2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}