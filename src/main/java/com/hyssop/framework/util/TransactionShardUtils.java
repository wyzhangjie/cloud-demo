//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hyssop.framework.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import java.math.BigInteger;
import java.util.List;

public class TransactionShardUtils {
    private static final String TABLE_NAME = "hcs_apply_transaction";
    private static final String TABLE_PREFIX = "hcs_apply_transaction_";
    private static final int TABLE_COUNT = 3;
    public static final long TEN_BILLION = 10000000000L;

    public TransactionShardUtils() {
    }

    public static long getStartIncrementByIdx(int idx) {
        Preconditions.checkArgument(idx >= 17);
        return (long)(idx - 16) * 10000000000L;
    }

    public static String getTableNameById(BigInteger id) {
        long idL = id.longValue();
        if (idL < 10000000000L) {
            return "hcs_apply_transaction";
        } else {
            long idx = idL / 10000000000L + 16L;
            return "hcs_apply_transaction_" + idx;
        }
    }

    public static String getTableNameByOrderNo(String orderNo) {

        String tableName = "hcs_apply_transaction";
        if (isUseShard(orderNo)) {
            tableName = "hcs_apply_transaction_" + ShardUtils.getIdxByOrderNo(orderNo);
        }

        return tableName;
    }

    public static List<String> getUsedTableNames() {
        List<String> result = Lists.newArrayList();
        result.add("hcs_apply_transaction");
        int currIdx = ShardUtils.getCurrIdx();

        for(int i = 17; i <= currIdx; ++i) {
            result.add("hcs_apply_transaction_" + i);
        }

        return result;
    }

    public static List<String> getLast3TableNames() {
        List<String> result = getUsedTableNames();
        return result.size() < 3 ? result : result.subList(result.size() - 3, result.size());
    }

    private static boolean isUseShard(String orderNo) {
      return true;
    }
}
