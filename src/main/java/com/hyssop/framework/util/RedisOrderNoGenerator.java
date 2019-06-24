package com.hyssop.framework.util;

import com.google.common.base.Preconditions;

import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.LongCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 使用redis保证全局唯一
 *
 * @author wu.shen
 * @date 2018/08/03
 */
@Service
public class RedisOrderNoGenerator {
    private static final String REASSURE_ORDER_NO_SUFFIX_KEY = "reassure_order_no_suffix_key";

    private static final String REWARD_ORDER_NO_SUFFIX_KEY ="reward_order_suffix_key";

    private static final int PREFFIX_LENGTH = 5;
    /**
     * 4095的16进制为fff,大于这个数则后三位可能出现重复
     * 但当此接口qps大于此数时会出现重复.
     */
    private static final int SUFFIX_MAX_LIMIT = 4095;

    @Autowired
    private RedissonClient redissonClient;
    /**
     * 使用redis生成全局唯一20位订单号
     * preffix保证为5位
     */
    public String generator(String preffix) {
        return getString(preffix, REASSURE_ORDER_NO_SUFFIX_KEY);
    }

    public String generatorForReward(String preffix) {
        return getString(preffix, REWARD_ORDER_NO_SUFFIX_KEY);
    }

    private String getString(String preffix, String suffixKey) {
        Preconditions.checkNotNull(preffix);
        Preconditions.checkArgument(preffix.length() == PREFFIX_LENGTH);
        RMapCache<String, Long> mapCache = redissonClient.getMapCache("nameSpace", LongCodec.INSTANCE);
        Long incr = mapCache.addAndGet(suffixKey, 1);
        if (incr >= SUFFIX_MAX_LIMIT) {
            incr = mapCache.put(preffix,Long.parseLong("1"));
        }
        return String.format("%s%s%03x", preffix,
                DateTimeUtils.formatCurrentDate(DateTimeUtils.GENERAL_DATETIME_PATTERN_SIMPLIFIED),
                incr);
    }

}
