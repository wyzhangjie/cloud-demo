package com.hyssop.framework.util.TaskExecutePool;


import com.fasterxml.jackson.core.type.TypeReference;
import com.hyssop.framework.util.JsonMapper;
import com.hyssop.framework.util.http.HttpClient;
import com.hyssop.framework.util.http.HttpRequest;
import com.hyssop.framework.util.http.HttpResponse;
import com.hyssop.framework.vo.BaseRequest;
import com.hyssop.framework.vo.BaseResponse;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.MessageFormat;

import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;


/**
 * @author zhjie.zhang
 */
@Slf4j
@Component
public class PullPushRepositoryAssist<T extends BaseRequest> implements BeanPostProcessor {

    private static final int RESPONSE_OK = 200 ;
    public  Optional fetchRequest(HttpClient httpClient, String pullURL, int size) {
        BaseResponse<List<BaseRequest>> listResponseObj = null;
        try {
            String url = MessageFormat.format(pullURL, size);
            HttpRequest request = HttpRequest.builder().url(url).build();
            HttpResponse response = httpClient.execute(request, true);
            if (response.getCode() != RESPONSE_OK) {
                return Optional.empty();
            }

            String content = response.getStringBody();

            listResponseObj = JsonMapper.getInstance().fromJson(content, new TypeReference<BaseResponse<List<BaseRequest>>>() {
            });

            if (listResponseObj != null && listResponseObj.isSuccess()) {
                return Optional.ofNullable(listResponseObj.getData());
            }
        } catch (IOException e) {
            log.error("fetchRequest exception", e);
        }finally {
           /* MonitorBuilder.newMonitor("resign_pull_request")
                    .append("pull_request")
                    .recordTotal();*/

        }

        return Optional.empty();
    }

    public  void pushResult(HttpClient httpClient, BaseResponse response, String pushResultURL) {
        HttpResponse httpResponse = null;

        try {
            String json = JsonMapper.getInstance().toJson(response);

            HttpRequest request = HttpRequest.builder()
                    .url(pushResultURL)
                    .body(json.getBytes())
                    .build();

            httpResponse = httpClient.execute(request, true);
            log.info("pushResult result:{}", httpResponse.getCode());
        } catch (Exception e) {
            log.error("", e);
        } finally {
            //添加监控信息
        /*    MonitorBuilder.newMonitor("resign_push_result")
                    .append("push_result")
                    .recordTotal();*/

        }
    }
}
