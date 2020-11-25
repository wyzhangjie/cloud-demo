package com.hyssop.framework.util;

import com.alibaba.ttl.TtlEnhanced;
import com.alibaba.ttl.threadpool.TtlExecutors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @Author jie.zhang
 * @create_time 2020/2/28 19:21
 * @updater
 * @update_time
 **/
@Slf4j
/*@Service*/
/*@DependsOn("initApolloConfigure")*/
public class AsyncThreadExecutor implements AutoCloseable, TtlEnhanced {

    @Value("${userThreadcorePoolSize:5}")
    private int userThreadcorePoolSize;
    @Value("${userThreadmaximumPoolSize:5}")
    private int userThreadmaximumPoolSize;
    @Value("${userThreadkeepAliveTime:5}")
    private long userThreadkeepAliveTime;
    /**
     * 用于周期性监控线程池的运行状态
     */
    private final ScheduledExecutorService scheduledExecutorService =
            Executors.newSingleThreadScheduledExecutor(new BasicThreadFactory.Builder().namingPattern("async thread executor monitor").build());

    /**
     * 自定义异步线程池
     * （1）任务队列使用有界队列
     * （2）自定义拒绝策略
     */
    private  ThreadPoolExecutor threadPoolExecutor =
            new ThreadPoolExecutor(
                    userThreadcorePoolSize,
                    userThreadmaximumPoolSize,
                    userThreadkeepAliveTime,
                    TimeUnit.MINUTES, new SynchronousQueue(),
                    new BasicThreadFactory.Builder().namingPattern("async-thread-%d").build(),
                    (r, executor) -> {log.error("the async executor pool is full!!");
                        throw new RejectedExecutionException();
                    });
    private final ExecutorService executorService =  TtlExecutors.getTtlExecutorService(threadPoolExecutor);

  /*  private void someChangeHandler(ConfigChangeEvent changeEvent) {
        if (changeEvent.isChanged("userThreadmaximumPoolSize")) {
            log.info("用户线程池最大大小改变,userThreadmaximumPoolSize:{}",userThreadmaximumPoolSize);
            threadPoolExecutor.setMaximumPoolSize(userThreadmaximumPoolSize);
        }

        if (changeEvent.isChanged("userThreadkeepAliveTime")) {
            log.info("用户线程池存活时间改变userThreadkeepAliveTime:{}",userThreadkeepAliveTime);
            threadPoolExecutor.setKeepAliveTime(userThreadkeepAliveTime,TimeUnit.MILLISECONDS);
        }
        if (changeEvent.isChanged("userThreadcorePoolSize")) {
            log.info("用户线程池大小改变,userThreadcorePoolSize:{}",userThreadcorePoolSize);
            threadPoolExecutor.setCorePoolSize(userThreadcorePoolSize);
        }
    }*/

    @PostConstruct
    public void init() {
    //    log.info("anotherConfig:{}", JSON.toJSONString(anotherConfig));
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            /**
             * 线程池需要执行的任务数
             */
            long taskCount = threadPoolExecutor.getTaskCount();
            /**
             * 线程池在运行过程中已完成的任务数
             */
            long completedTaskCount = threadPoolExecutor.getCompletedTaskCount();
            /**
             * 曾经创建过的最大线程数
             */
            long largestPoolSize = threadPoolExecutor.getLargestPoolSize();
            /**
             * 线程池里的线程数量
             */
            long poolSize = threadPoolExecutor.getPoolSize();
            /**
             * 线程池里活跃的线程数量
             */
            long activeCount = threadPoolExecutor.getActiveCount();
            Map<String, Object> warnMessage = new LinkedHashMap<>();
            warnMessage.put("线程池需要执行的任务数",taskCount);
            warnMessage.put("线程池在运行过程中已完成的任务数",completedTaskCount);
            warnMessage.put("曾经创建过的最大线程数",largestPoolSize);
            warnMessage.put("线程池里的线程数量",poolSize);
            warnMessage.put("线程池里活跃的线程数量",activeCount);

            log.info("async-executor monitor. taskCount:{}, completedTaskCount:{}, largestPoolSize:{}, poolSize:{}, activeCount:{}",
                    taskCount, completedTaskCount, largestPoolSize, poolSize, activeCount);
        }, 0, 10, TimeUnit.MINUTES);

    }

    public void execute(Runnable task) {
        executorService.execute(task);
    }

    public Future submit(Callable callable){

        return executorService.submit(callable);
    }

    @Override
    public void close() throws Exception {
        executorService.shutdown();
    }
}