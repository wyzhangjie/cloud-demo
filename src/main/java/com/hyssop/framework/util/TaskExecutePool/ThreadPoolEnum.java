package com.hyssop.framework.util.TaskExecutePool;

import com.google.common.base.Suppliers;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @Description:   事件分发池枚举
 * @Author:         zhjie.zhang
 * @CreateDate:     2019/6/24$ 16:17$
 * @UpdateUser:     zhjie.zhang
 * @UpdateDate:     2019/6/24$ 16:17$
 * @Version:        1.0
 */
public enum ThreadPoolEnum {
    /**
     * 事件分发线程池
     */
    DISTRIBUTOR_EVENT_THREAD_POOL(Suppliers.memoize(() -> {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("distributor_event_thread_pool_%d").build();
        return new ThreadPoolExecutor(100, 100, 5, TimeUnit.SECONDS, new SynchronousQueue<>(), threadFactory, BlockingRejectHandler.INSTANCE);
    })::get),;

    private Supplier<ThreadPoolExecutor> executor;

    ThreadPoolEnum(Supplier<ThreadPoolExecutor> executor) {
        this.executor = executor;
    }

    public Supplier<ThreadPoolExecutor> getExecutor() {
        return executor;
    }
}
