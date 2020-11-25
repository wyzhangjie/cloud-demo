package com.hyssop.algorithm.interview;

import java.util.Arrays;

public class MoneyCoin {


    public static void main(String[] args) {
        int[] c={5,3};
        int k=11;
        findSmallNum(c,k);
        MoneyCoin moneyCoin = new MoneyCoin();
        System.out.println(moneyCoin.getMinCoinCountOfValue());
        System.out.println(moneyCoin.getMinCounts(k,c));
    }
    //贪心算法
    private static void findSmallNum(int[] c, int k) {
    int rest =k;
    int count=0;
    int maxIndex =c.length-1;
    Arrays.sort(c);
    for(int i=maxIndex;i>=0;i--){
        int temp =rest/c[i];
        rest = rest- temp*c[i];
        count+=temp;
        if(rest<=0){
            break;
        }
    }
        System.out.println(count);
    }

    //贪心添加回溯思想获取全局最优解
    int getMinCoinCountOfValue(int total, int[] values, int valueIndex) {
        int valueCount = values.length;
        if (valueIndex == valueCount) { return Integer.MAX_VALUE; }

        int minResult = Integer.MAX_VALUE;
        int currentValue = values[valueIndex];
        int maxCount = total / currentValue;

        for (int count = maxCount; count >= 0; count --) {
            int rest = total - count * currentValue;

            // 如果rest为0，表示余额已除尽，组合完成
            if (rest == 0) {
                minResult = Math.min(minResult, count);
                break;
            }

            // 否则尝试用剩余面值求当前余额的硬币总数
            int restCount = getMinCoinCountOfValue(rest, values, valueIndex + 1);

            // 如果后续没有可用组合
            if (restCount == Integer.MAX_VALUE) {
                // 如果当前面值已经为0，返回-1表示尝试失败
                if (count == 0) { break; }
                // 否则尝试把当前面值-1
                continue;
            }

            minResult = Math.min(minResult, count + restCount);
        }

        return minResult;
    }

    int getMinCoinCountLoop(int total, int[] values, int k) {
        int minCount = Integer.MAX_VALUE;
        int valueCount = values.length;

        if (k == valueCount) {
            return Math.min(minCount, getMinCoinCountOfValue(total, values, 0));
        }

        for (int i = k; i <= valueCount - 1; i++) {
            // k位置已经排列好
            int t = values[k];
            values[k] = values[i];
            values[i]=t;
            minCount = Math.min(minCount, getMinCoinCountLoop(total, values, k + 1)); // 考虑后一位

            // 回溯
            t = values[k];
            values[k] = values[i];
            values[i]=t;
        }

        return minCount;
    }

    //动归初步
    //k 目标金额
    //values 硬币数组
    int getMinCounts(int k,int[] values){
        //备忘录
        int[] memo = new int[k+1];

        Arrays.fill(memo,-1);
        //初始化状态
        memo[0]=0;
        //递归过程
        for(int v=1;v<=k;v++){
            int minCount =k+1;
            for(int i=0;i<values.length;i++){
                int currentValue = values[i];
                if(currentValue>v){continue;}
                //扣减这个金额，剩余的金额
                int restValue = v-currentValue;
                //剩余金额能够被凑出来最少的硬币数
                int restCount=memo[restValue];
                if(restCount==-1){continue;}
                int currentCount = restCount+1;
                if(currentCount<minCount){
                    minCount=currentCount;
                }

            }
            if(minCount!=k+1){
                memo[v]=minCount;
            }
        }
        return memo[k];
    }
    int getMinCountsForDp(int k,int[] values){
        //备忘录
        int[] memo = new int[k+1];
        memo[0]=0;
        for(int i=1;i<k+1;i++){
            memo[i]=k+1;
        }

        for(int i=1;i<k+1;i++){
            for(int coin:values){
                if(i-coin<0){
                    continue;
                }
                memo[i]=Math.max(memo[i],memo[i-coin]+1);
            }
        }


        return memo[k];
    }
    int getMinCoinCountOfValue() {
        int[] values = { 5, 3 }; // 硬币面值
        int total = 11; // 总价
        int minCoin = getMinCoinCountLoop(total, values, 0);

        return (minCoin == Integer.MAX_VALUE) ? -1 : minCoin;  // 输出答案
    }
}
