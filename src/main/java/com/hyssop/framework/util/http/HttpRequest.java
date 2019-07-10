package com.hyssop.framework.util.http;

import com.google.common.base.Preconditions;


import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author haoyang.shi
 * @date 2018/11/8
 */
@Getter
@EqualsAndHashCode
@Slf4j
public class HttpRequest {
    @Setter
    private String url;

    @Setter
    private byte[] body;

    private String contentType;

    private Map<String, String> headers;

    private Method method;


    @Setter
    private Integer timeout;



    @Builder
    public HttpRequest(String url, byte[] body, String contentType, Map<String, String> headers,
                       Method method, Integer timeout,int proxyUseWay) {
        this.url = url;
        this.contentType = (contentType == null ? "application/json;charset=utf-8" : contentType);
        this.headers = headers;
        this.method = (method == null ? (body == null || body.length == 0 ? Method.GET : Method.POST) : method);

        this.timeout = timeout;
        this.body = body == null ? new byte[0] : body;

    }

    public void addHeader(String name, String value) {
        Preconditions.checkArgument(StringUtils.isNotBlank(name));
        Preconditions.checkArgument(StringUtils.isNotBlank(value));

        headers.computeIfPresent(name, (s, s2) -> s + ";" + s2);
    }

    public void setHeader(String name, String value) {
        Preconditions.checkArgument(StringUtils.isNotBlank(name));
        Preconditions.checkArgument(StringUtils.isNotBlank(value));

        headers.put(name, value);
    }


    @Override
    public String toString() {
        if (ArrayUtils.isNotEmpty(body) && body.length < 1000) {
            return "HttpRequest{" +
                    "url='" + url + '\'' +
                    ", body=" + new String(body, StandardCharsets.UTF_8) +
                    ", contentType='" + contentType + '\'' +
                    ", headers=" + headers +
                    ", method=" + method +

                    ", timeout=" + timeout +
                    '}';
        }

        return "HttpRequest{" +
                "url='" + url + '\'' +
//                ", body=" + Arrays.toString(body) +
                ", contentType='" + contentType + '\'' +
                ", headers=" + headers +
                ", method=" + method +

                ", timeout=" + timeout +
                '}';
    }

    public enum Method {
        /**
         *
         */
        GET,
        /**
         *
         */
        POST

    }
}
