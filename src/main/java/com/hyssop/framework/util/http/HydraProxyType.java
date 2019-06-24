package com.hyssop.framework.util.http;

/**
 * @author haoyang.shi
 * @date 2018/11/6
 */
public enum HydraProxyType {
    /**
     * 普通 HTTP 代理
     */
    HTTP,

    /**
     * 本机 IP 作为出口IP
     */
    NOOP
}
