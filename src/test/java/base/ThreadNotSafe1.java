package base;

/**访问共享变量或资源
 * @Author jie.zhang
 * @create_time 2020/7/7 16:31
 * @updater
 * @update_time
 **/
public class ThreadNotSafe1 {
     static int i;

    public static void main(String[] args) throws InterruptedException {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                for (int j = 0; j < 10000; j++) {
                    i++;
                }
            }
        };
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(i);
    }
}