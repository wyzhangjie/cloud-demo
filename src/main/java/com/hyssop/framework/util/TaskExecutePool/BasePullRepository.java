package com.hyssop.framework.util.TaskExecutePool;

import com.hyssop.framework.util.http.HttpClient;
import com.hyssop.framework.vo.BaseRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Optional;

/**
 * @Description:    任务拉取的抽象类实现
 * @Author:         zhjie.zhang
 * @CreateDate:     2019/6/24$ 17:15$
 * @UpdateUser:     zhjie.zhang
 * @UpdateDate:     2019/6/24$ 17:15$
 * @Version:        1.0
 */
public abstract class BasePullRepository implements IBasePullRepository  {

    @Value("www.baidu.com")
    private String pullURL;

    private HttpClient httpClient;

    private PullPushRepositoryAssist pullPushRepositoryAssist;
    @Override
    public Optional<List<BaseRequest>> fetchRequest(int size) {
        return pullPushRepositoryAssist.fetchRequest(httpClient,pullURL,size);
    }


    @Autowired
    public void setPullPushRepositoryAssist(PullPushRepositoryAssist pullPushRepositoryAssist) {
        this.pullPushRepositoryAssist = pullPushRepositoryAssist;
    }

    @Autowired
    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }
}
