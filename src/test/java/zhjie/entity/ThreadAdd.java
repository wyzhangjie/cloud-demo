package zhjie.entity;

/**
 * @Author jie.zhang
 * @create_time 2019/12/19 14:31
 * @updater
 * @update_time
 **/
public class ThreadAdd extends Thread {

    private Add p;

    public ThreadAdd(Add p) {
        super();
        this.p = p;
    }

    @Override
    public void run() {
        p.add();
    }
}