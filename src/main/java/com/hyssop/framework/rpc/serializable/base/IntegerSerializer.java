package com.hyssop.framework.rpc.serializable.base;

import com.hyssop.framework.rpc.serializable.Serializer;
import org.apache.commons.compress.utils.ByteUtils;

import java.nio.charset.StandardCharsets;

/**
 * @Author jie.zhang
 * @create_time 2020/4/24 15:01
 * @updater
 * @update_time
 **/
public class IntegerSerializer implements Serializer<Integer> {
    @Override
    public int size(Integer entry) {
        return entry.intValue();
    }

    @Override
    public void serialize(Integer entry, byte[] bytes, int offset, int length) {
        String a = entry.toString();
        byte[] b = a.getBytes(StandardCharsets.UTF_8);
        System.arraycopy(b,0,bytes,offset,length);
    }

    @Override
    public Integer parse(byte[] bytes, int offset, int length) {
        String tmp =  new String(bytes,offset,length,StandardCharsets.UTF_8);
        return Integer.parseInt(tmp);
    }

    @Override
    public byte type() {
        return Types.TYPE_INTEGER;
    }

    @Override
    public Class<Integer> getSerializeClass() {
        return Integer.class;
    }
}