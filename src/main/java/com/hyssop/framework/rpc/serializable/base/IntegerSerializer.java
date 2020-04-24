package com.hyssop.framework.rpc.serializable.base;

import com.hyssop.framework.rpc.serializable.Serializer;

/**
 * @Author jie.zhang
 * @create_time 2020/4/24 15:01
 * @updater
 * @update_time
 **/
public class IntegerSerializer implements Serializer<Integer> {
    @Override
    public int size(Integer entry) {
        return 0;
    }

    @Override
    public void serialize(Integer entry, byte[] bytes, int offset, int length) {

    }

    @Override
    public Integer parse(byte[] bytes, int offset, int length) {
        return null;
    }

    @Override
    public byte type() {
        return 0;
    }

    @Override
    public Class<Integer> getSerializeClass() {
        return null;
    }
}