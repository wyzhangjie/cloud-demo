package com.hyssop.framework.util.http;

import java.io.IOException;

import javax.annotation.Nonnull;

/**
 * @author haoyang.shi
 * @date 2018/11/16
 */
public interface HttpClient {
    /**
     * @param request
     * @param isEncrypt 是否进行 AES 加解密，与 speed 等系统的 http 接口需要进行加密
     * @return
     */
    @Nonnull
    HttpResponse execute(HttpRequest request, boolean isEncrypt) throws IOException;
}