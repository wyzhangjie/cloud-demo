package com.hyssop.framework.util.http;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.hyssop.framework.util.http.aes.AES;

import org.apache.commons.collections4.MapUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.net.ssl.SSLContext;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhjie.zhang
 * @date 2018/11/1
 */
@Slf4j
@Component
public class ApacheHttpClient implements HttpClient {
    private final CloseableHttpClient httpClient;

    private AES aes;

    @Autowired
    public void setAes(AES aes) {
        this.aes = aes;
    }

    {
        PoolingHttpClientConnectionManager defaultConnectionManager = createDefaultConnectionManager(3000, 4000, 200);

        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setCookieSpec(CookieSpecs.IGNORE_COOKIES)
                .setExpectContinueEnabled(false)
                .setRedirectsEnabled(false)
                .setConnectTimeout(3000)
                .setSocketTimeout(3000)
                .build();

        this.httpClient = HttpClients.custom()
                .setConnectionManager(defaultConnectionManager)
                .setConnectionReuseStrategy(DefaultConnectionReuseStrategy.INSTANCE)
                .setDefaultRequestConfig(defaultRequestConfig)
                .setDefaultCookieStore(NoopCookieStore.INSTANCE)
                .setUserAgent("Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/534.16 (KHTML, like Gecko) Chrome/10.0.648.204 Safari/534.16")
                .setRetryHandler((exception, executionCount, context) -> {
                    if (executionCount > 3) {
                        log.warn("Maximum tries reached for client http pool");
                        return false;
                    }
                    if (exception instanceof NoHttpResponseException) {
                        return true;
                    }

                    return false;
                })
                .build();
    }

    private static PoolingHttpClientConnectionManager createDefaultConnectionManager(int connectTimeout, int maxTotal, int maxPerRoute) {
        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(createSSLContext(), new NoopHostnameVerifier());
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", sslConnectionSocketFactory)
                .build();

        // 设置代理的默认超时时间
        SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(connectTimeout).build();
        // 设置抓取的默认编码 UTF-8
        ConnectionConfig connectionConfig = ConnectionConfig.custom().setCharset(Charsets.UTF_8).build();

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);

        // 该client的最大连接数
        cm.setMaxTotal(maxTotal);

        // 每个路由下最大的连接数
        cm.setDefaultMaxPerRoute(maxPerRoute);
        cm.setDefaultSocketConfig(socketConfig);
        cm.setDefaultConnectionConfig(connectionConfig);
        return cm;
    }

    private static SSLContext createSSLContext() {
        SSLContext sslContext;
        try {
            sslContext = SSLContexts.custom()
                    .loadTrustMaterial(null, (chain, authType) -> true).build();
        } catch (Exception e) {
            log.error("SSLContext build error", e);
            sslContext = SSLContexts.createSystemDefault();
        }

        sslContext.getServerSessionContext().setSessionCacheSize(1000);
        return sslContext;
    }

    private HttpRequestBase toRequestBase(HttpRequest request) {
        Header[] headers = null;
        if (MapUtils.isNotEmpty(request.getHeaders())) {
            headers = request.getHeaders().entrySet().stream()
                    .map(entry -> new BasicHeader(entry.getKey(), entry.getValue()))
                    .toArray(Header[]::new);
        }

        RequestConfig.Builder builder = RequestConfig.custom();


        Integer timeout = request.getTimeout();
        if (timeout == null || timeout <= 0) {
//            if (log.isDebugEnabled()) {
//                log.debug("request not specify timeout, use default 5000. url={}", request.getUrl());
//            }
            timeout = 5000; // 没有指定超时时间时，默认 5 秒
        }
        builder.setConnectTimeout(timeout)
                .setSocketTimeout(timeout);

        RequestConfig requestConfig = builder.build();
        switch (request.getMethod()) {
            case POST:
                HttpPost httpPost = new HttpPost(request.getUrl());
                if (headers != null) {
                    httpPost.setHeaders(headers);
                }
                httpPost.setEntity(new ByteArrayEntity(request.getBody(), ContentType.parse(request.getContentType())));
                httpPost.setConfig(requestConfig);
                return httpPost;

            default:
                HttpGet httpGet = new HttpGet(request.getUrl());
                if (headers != null) {
                    httpGet.setHeaders(headers);
                }
                httpGet.setConfig(requestConfig);
                return httpGet;
        }
    }

    private HttpResponse execute(HttpRequest request) throws IOException {
        HttpRequestBase requestBase = toRequestBase(request);
        CloseableHttpResponse response = null;
        int stateCode;
        Map<String, List<String>> headers = Maps.newHashMap();
        Header[] headerArr;
        byte[] content = new byte[0];
        try {
            response = this.httpClient.execute(requestBase);
            // code
            stateCode = response.getStatusLine().getStatusCode();
            // header
            headerArr = response.getAllHeaders();
            if (headerArr != null) {
                for (Header header : response.getAllHeaders()) {
                    headers.computeIfAbsent(header.getName(), s -> Lists.newLinkedList()).add(header.getValue());
                }
            }
            // body
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                content = EntityUtils.toByteArray(httpEntity);
            }

            EntityUtils.consumeQuietly(httpEntity);
        } finally { // 请求异常也确保连接关闭
            if (response != null) {
                response.close();
            }
        }
        return HttpResponse.builder()
                .code(stateCode)
                .headers(headers)
                .headerArr(headerArr)
                .body(content)
                .build();
    }

    @Nonnull
    @Override
    public HttpResponse execute(HttpRequest request, boolean isEncrypt) throws IOException {
        if (isEncrypt) {
            byte[] originBody = request.getBody();
            if (originBody != null) {
                byte[] encryptBody = aes.encryptByte(originBody);
                request.setBody(Base64.getEncoder().encode(encryptBody));
            }
        }

        HttpResponse response = execute(request);

        if (isEncrypt) {
            byte[] originBody = response.getBody();
            if (originBody != null && originBody.length != 0) {
                byte[] encryptData = Base64.getDecoder().decode(originBody);
                response.setBody(aes.decryptByte(encryptData));
            }
        }

        return response;
    }

    public CloseableHttpClient getHttpClient() {
        return httpClient;
    }
}
