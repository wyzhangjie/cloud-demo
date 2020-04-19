package zhjie.entity;

/**
 * @Author jie.zhang
 * @create_time 2019/12/19 14:30
 * @updater
 * @update_time
 **/
public class Add {
    private String lock;

    public Add(String lock) {
        super();
        this.lock = lock;
    }

    public void add() {
        synchronized (lock) {
            ValueObject.list.add("anyString");
            lock.notifyAll();
        }
    }
}