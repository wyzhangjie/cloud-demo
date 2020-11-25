package com.hyssop.algorithm.leetcode.questions.dpproblem;

public class Leecode746 {
    /**
     * On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).
     *
     * Once you pay the cost, you can either climb one or two steps.
     * You need to find minimum cost to reach the top of the floor,
     * and you can either start from the step with index 0, or the step with index 1.
     * */

    public int minCostClimbingStairs(int[] cost) {

        int[] a = new int[cost.length];
        a[0]=cost[0];
        a[1]=cost[0];
        for(int i=2;i<cost.length;i++){
            a[i]=Math.min(cost[i-1]+a[i-1],cost[i-2]+a[i-2]);
        }
        return a[cost.length-1];
    }
    public int minCostClimbingStairsFair(int[] cost) {

        int[] a = new int[cost.length];
        a[0]=cost[0];
        a[1]=cost[0];
        for(int i=2;i<cost.length;i++){
            a[i]+=Math.min(cost[i-1],cost[i-2]);
        }
        return a[cost.length-1];
    }

    public static void main(String[] args) {
        int[] a= new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        Leecode746 leecode746 = new Leecode746();
        System.out.println(leecode746.minCostClimbingStairsFair(a));
    }
}
