package com.hyssop.framework.util.TaskExecutePool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import lombok.extern.slf4j.Slf4j;

/**
 * @author haoyang.shi
 * @date 2018/11/9
 */
@Slf4j
public class BlockingRejectHandler implements RejectedExecutionHandler {

    public static final BlockingRejectHandler INSTANCE = new BlockingRejectHandler();

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        try {
            executor.getQueue().put(r);
        } catch (InterruptedException e) {
            log.error("force put runnable error,", e);
        }
    }
}
