package com.hyssop.framework.rpc.serializable.base;

import com.hyssop.framework.rpc.serializable.Serializer;

import java.nio.charset.StandardCharsets;

/**
 * @Author jie.zhang
 * @create_time 2020/4/24 11:26
 * @updater
 * @update_time
 **/
public class StringSerializer implements Serializer<String> {
    @Override
    public int size(String entry) {
        return entry.getBytes(StandardCharsets.UTF_8).length;
    }

    @Override
    public void serialize(String entry, byte[] bytes, int offset, int length) {
        byte[] b = entry.getBytes(StandardCharsets.UTF_8);
        System.arraycopy(b,0,bytes,offset,length);

    }

    @Override
    public String parse(byte[] bytes, int offset, int length) {
        return new String(bytes,offset,length,StandardCharsets.UTF_8);
    }

    @Override
    public byte type() {
        return Types.TYPE_STRING;
    }

    @Override
    public Class<String> getSerializeClass() {
        return String.class;
    }
}