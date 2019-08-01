package com.hyssop.framework.util;

import org.springframework.util.StringUtils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 订单ID生成器
 * 
 * @author yi.chen@qunar.com
 */
public class OrderIdGenerator {

    private static AtomicInteger counter = new AtomicInteger(0);
    private static OrderIdGenerator uuidgen = new OrderIdGenerator();

    public static OrderIdGenerator getInstance() {
        return uuidgen;
    }

    protected String format(short intval) {
        String formatted = Integer.toHexString(intval);
        StringBuffer buf = new StringBuffer("000");
        buf.replace(3 - formatted.length(), 3, formatted);
        return buf.toString();
    }

    protected short getCount() {
        counter.compareAndSet(4000, 0);
        return (short) counter.incrementAndGet();
    }

    public synchronized String genOrderId(String pre) {
        if (!StringUtils.hasText(pre) || pre.length() != 5)
            throw new IllegalArgumentException("the pre must has five chars.");
        String ret = pre + DateTimeUtils.formatCurrentDate(DateTimeUtils.PATTEN_yyMMddHHmmss) + format(getCount());
        return ret.toLowerCase();
    }



    public static void main(String[] args) throws Exception {
        System.out.println(OrderIdGenerator.getInstance().genOrderId("abcde"));
    }
}
