package com.hyssop.algorithm.leetcode.questions.dpproblem;

public class Leecode871 {

    public int getFuel(int[][] stations,int startFuel,int target){
        int size = stations.length;
        int[][] dp = new int[size+1][];
        //初始化的值
        dp[0][0]=startFuel;
        dp[0][1]=startFuel;
        for(int i=0;i<size;i++){
            if(dp[i][0]-stations[i][0]>0 && dp[i][1]-stations[i][0]>0){
                dp[i+1][0]= Math.max(dp[i][0],dp[i][1]);
                dp[i+1][1]=Math.max(dp[i][0]+stations[i][1],dp[i][1]+stations[i][1]);
            }
            if(dp[i][0]-stations[i][0]<0 && dp[i][1]-stations[i][0]>0){
                dp[i+1][0]= dp[i][1];
                dp[i+1][1]=dp[i][1]+stations[i][1];
            }else {
                dp[i+1][0]=-1;
                dp[i+1][1]=-1;
            }

    }
        Integer count= Integer.MAX_VALUE;
        for(int j=0;j<size+1;j++){
            int tempTarget = Math.max(dp[j][0],dp[j][1]);
            if(tempTarget>=target){
                count = Math.min(count,j);
            }
        }
        if(count== Integer.MAX_VALUE){
            return -1;
        }
        return count;
    }
}
