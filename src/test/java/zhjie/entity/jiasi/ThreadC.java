package zhjie.entity.jiasi;

/**
 * @Author jie.zhang
 * @create_time 2019/12/19 14:51
 * @updater
 * @update_time
 **/
public class ThreadC extends Thread {

    private C r;

    public ThreadC(C r) {
        super();
        this.r = r;
    }

    @Override
    public void run() {
        while (true) {
            r.getValue();
        }
    }

}