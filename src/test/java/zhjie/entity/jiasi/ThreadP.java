package zhjie.entity.jiasi;

/**
 * @Author jie.zhang
 * @create_time 2019/12/19 14:50
 * @updater
 * @update_time
 **/
public class ThreadP extends Thread {

    private P p;

    public ThreadP(P p) {
        super();
        this.p = p;
    }

    @Override
    public void run() {
        while (true) {
            p.setValue();
        }
    }
}