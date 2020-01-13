package com.hyssop.framework.util.http;

import java.io.IOException;


/**
 * @author zhjie.zhang
 * @date 2018/11/16
 */
public interface HttpClient {
    /**
     * @param request
     * @param isEncrypt 是否进行 AES 加解密，与 speed 等系统的 http 接口需要进行加密
     * @return
     */
    HttpResponse execute(HttpRequest request, boolean isEncrypt) throws IOException;
}
