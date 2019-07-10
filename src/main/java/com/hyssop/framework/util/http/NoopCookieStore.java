package com.hyssop.framework.util.http;

import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author haoyang.shi
 * @date 2018/11/2
 */
public class NoopCookieStore implements CookieStore {
    static final NoopCookieStore INSTANCE = new NoopCookieStore();

    private NoopCookieStore() {
    }

    @Override
    public void addCookie(Cookie cookie) {
        //Noop
    }

    @Override
    public List<Cookie> getCookies() {
        return Collections.emptyList();
    }

    @Override
    public boolean clearExpired(Date date) {
        return true;
    }

    @Override
    public void clear() {
        //Noop
    }
}
