package com.hyssop.algorithm.leetcode.questions.dpproblem;

/**
 *
 * Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.
 *
 * Example 1:
 * Input: [0,1]
 * Output: 2
 * Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
 * Example 2:
 * Input: [0,1,0]
 * Output: 2
 * Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 * */
public class Leecode505 {

    public int findMaxLength(int[] nums) {
     int len =nums.length;
        int[] dp = new int[len];
        dp[0]=1;
        if (len==1)return dp[0];
        for(int i=1;i<len;i++){

        }
        return len;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,1,0};
    }
}
