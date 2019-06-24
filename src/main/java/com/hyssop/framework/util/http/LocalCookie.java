package com.hyssop.framework.util.http;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import java.io.Serializable;
import java.net.HttpCookie;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.ToString;


@ToString
public class LocalCookie implements Serializable {
    private static final Joiner JOINER = Joiner.on(';').skipNulls().skipNulls();

    private static final Splitter SPLITTER = Splitter.on(';').omitEmptyStrings().trimResults();
    private static final int PAIR_LENGTH = 2;

    @Getter
    private String name;
    @Getter
    private String value;

    public LocalCookie(String name, String value) {
        this.name = name;
        this.value = value;
    }

    /**
     *
     * @param cookieHeader 包含 set-cookie 或者 set-cookie2 的 Http 头信息
     * @return
     */
    public static List<LocalCookie> parseCookie(List<String> cookieHeader) {
        List<HttpCookie> cookies = cookieHeader.stream()
                .map(HttpCookie::parse)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        return cookies.stream()
                .map(cookie -> new LocalCookie(cookie.getName(), cookie.getValue()))
                .collect(Collectors.toList());
    }

    public static Optional<LocalCookie> fromValue(String value) {
        List<String> pairs = SPLITTER.splitToList(value);
        if (pairs.size() != 1) {
            return Optional.empty();
        }

        String pair = pairs.get(0);
        String[] split = pair.split("=");
        if (split.length != PAIR_LENGTH) {
            return Optional.empty();
        }

        return Optional.of(new LocalCookie(split[0], split[1]));
    }

    public String toValue() {
        return String.format("%s=%s;", this.name, this.value);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LocalCookie that = (LocalCookie) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
