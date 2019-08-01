package com.hyssop.framework.util.TaskExecutePool;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:    责任链模式，执行任务
 * @Author:         zhjie.zhang
 * @CreateDate:     2019/6/24$ 17:30$
 * @UpdateUser:     zhjie.zhang
 * @UpdateDate:     2019/6/24$ 17:30$
 * @Version:        1.0
 */
public abstract  class BaseAbstractTaskProcess implements BaseTaskProcess {
    public static List<BaseTaskProcess> baseTaskProcessList = new ArrayList<>(2);

    public void tasKProcess(){
        for(BaseTaskProcess baseTaskProcess:baseTaskProcessList){
            baseTaskProcess.doProcess();
        }
    }
    abstract void putTask(BaseTaskProcess baseTaskProcess);

}
