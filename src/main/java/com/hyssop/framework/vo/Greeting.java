package com.hyssop.framework.vo;

/**
 * @Author jie.zhang
 * @create_time 2020/5/6 14:52
 * @updater
 * @update_time
 **/
public class Greeting {

    private final long id;
    private final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}