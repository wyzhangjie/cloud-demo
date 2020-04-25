package com.hyssop.framework.util.TaskExecutePool;

import com.github.fartherp.framework.common.util.IPUtil;
import com.hyssop.framework.vo.BaseRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadPoolExecutor;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description:    java类作用描述
 * @Author:         zhjie.zhang
 * @CreateDate:     2019/6/24$ 17:19$
 * @UpdateUser:     zhjie.zhang
 * @UpdateDate:     2019/6/24$ 17:19$
 * @Version:        1.0
 */
@Slf4j
@Service
public class DemoPullService {

    private IBasePullRepository resignPullRepository;

    private ThreadPoolExecutor distributorExecutor;


    private static int DURATION = 30;


    @Value("${project.env}")
    private String env;

    public DemoPullService(){
        distributorExecutor = ThreadPoolEnum.DISTRIBUTOR_EVENT_THREAD_POOL.getExecutor().get();
    }
    @PostConstruct
    public void init() {
        if (env.equals("local")) {
            return;
        }
        run();
    }



    public void run() {

        try {
            Random random = new Random(InetAddress.getLocalHost().getHostName().hashCode());
            Thread.sleep(random.nextInt(60*1000));
        } catch (InterruptedException e) {
            log.error("",e);
        }catch (UnknownHostException e1){

                log.warn("get local result error", e1);
                return ;
        }
        Thread thread=new Thread(() -> {
            while (true){
                try{
                    Thread.sleep(DURATION*1000);

                  /*  Optional<List<BaseRequest>> resignRequestOps = resignPullRepository.fetchRequest(calculateNeedFetchCount());
                    log.info("resignRequestOps:{}",resignRequestOps);
                    resignRequestOps.ifPresent(list->{
                    *//*    list.forEach(resignRequest -> resignTicketService.pcResign(resignRequest));*//*
                    });*/
                }catch (Exception e){
                    log.error("run failed",e);
                }
            }
        });
        thread.setName("resignThread");
        thread.start();
    }

    private Integer calculateNeedFetchCount() {
        //队列中有待处理任务，不拉取新任务
        if (distributorExecutor.getQueue().size() > 0) {
            return 0;
        }

        int activeCount = distributorExecutor.getActiveCount();
        int corePoolSize = distributorExecutor.getCorePoolSize();
        return corePoolSize - activeCount;
    }


    @Autowired
    public void setResignPullRepository(IBasePullRepository resignPullRepository) {
        this.resignPullRepository = resignPullRepository;
    }




}
