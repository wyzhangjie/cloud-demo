package com.hyssop.framework.util.TaskExecutePool;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description:    java类作用描述
 * @Author:         zhjie.zhang
 * @CreateDate:     2019/6/24$ 17:51$
 * @UpdateUser:     zhjie.zhang
 * @UpdateDate:     2019/6/24$ 17:51$
 * @Version:        1.0
 */
@Slf4j
@Component
public class BaseTaskProcessService extends BaseAbstractTaskProcess {

    @Override
    void putTask(BaseTaskProcess baseTaskProcess) {
        baseTaskProcessList.add(this);
    }

    @Override
    public void doProcess() {
        log.info("第一个任务处理完毕");

    }
}
