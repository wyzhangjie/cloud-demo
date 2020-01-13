package zhjie.entity;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author jie.zhang
 * @create_time 2019/12/19 14:32
 * @updater
 * @update_time
 **/
@Slf4j
public class ThreadSubtract extends Thread {

    private Subtract r;

    public ThreadSubtract(Subtract r) {
        super();
        this.r = r;
    }

    @Override
    public void run() {
        r.subtract();
    }
    private static final Object PRESENT = new Object();
    public static void main(String[] args) throws InterruptedException {

        String lock = new String("");

        Add add = new Add(lock);
        Subtract subtract = new Subtract(lock);

        ThreadSubtract subtract1Thread = new ThreadSubtract(subtract);
        subtract1Thread.setName("subtract1Thread");
        subtract1Thread.start();

        ThreadSubtract subtract2Thread = new ThreadSubtract(subtract);
        subtract2Thread.setName("subtract2Thread");
        subtract2Thread.start();

        Thread.sleep(1000);

        ThreadAdd addThread = new ThreadAdd(add);
        addThread.setName("addThread");
        addThread.start();
        Thread thread = new Thread();
        if(PRESENT == null){
            log.info("相同");
        }else {
            log.info("不同");
        }

    }
}