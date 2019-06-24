package com.hyssop.framework.util.TaskExecutePool;

import com.hyssop.framework.vo.BaseRequest;

import java.util.List;
import java.util.Optional;

/**
 * @Description:    java类作用描述
 * @Author:         zhjie.zhang
 * @CreateDate:     2019/6/24$ 16:33$
 * @UpdateUser:     zhjie.zhang
 * @UpdateDate:     2019/6/24$ 16:33$
 * @Version:        1.0
 */
public interface IBasePullRepository<T extends BaseRequest> {
    /**
     * 拉取
     * @param size
     * @return
     */
    Optional<List<T>> fetchRequest(int size);
}
