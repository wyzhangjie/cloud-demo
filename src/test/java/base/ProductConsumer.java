package base;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Author jie.zhang
 * @create_time 2020/7/7 15:14
 * @updater
 * @update_time
 **/
@Slf4j
public class ProductConsumer {

    public static void main(String[] args) throws Exception {
 
  BlockingQueue<Object> queue = new ArrayBlockingQueue<>(10);
 
 Runnable producer = () -> {
    while (true) {
        try {
            queue.put(new Object());
            log.info("放了一个对象");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
   };
 
        new Thread(producer).start();
        new Thread(producer).start();
 
        Runnable consumer = () -> {
      while (true) {
          try {
              queue.take();
              log.info("取出来一个对象");
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
      }
   };
        new Thread(consumer).start();
        new Thread(consumer).start();
    }
}