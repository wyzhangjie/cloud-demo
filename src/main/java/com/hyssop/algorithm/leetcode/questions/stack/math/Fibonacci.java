package com.hyssop.algorithm.leetcode.questions.stack.math;

import java.util.Arrays;

public class Fibonacci {

    public static long fibonacciNotAc(long n){
        if(n ==0){
            return 0;
        }
        if(n==1){
            return 1;
        }
        return fibonacciNotAc((n-1)) +fibonacciNotAc((n-2));

    }

    public static long fibonacciAc(int n){
        if(n==0){
            return 0;
        }
        if(n==1){
            return 1;
        }
        if(n==2){
            return 1;
        }
        //状态转移方程
        long[] mid = new long[n+1];
        Arrays.fill(mid,-1);
        mid[0]=0;
        mid[1]=1;
        mid[2]=1;
//转移的条件
        for(int i =3;i<n+1;i++){
            if(mid[i]==-1){
                mid[i]=mid[i-1]+mid[i-2];
            }
        }
        return mid[n];
        //最终的结果
    }

    public static void main(String[] args) {

       // fibonacciNotAc(100);
        System.out.println(  fibonacciAc(100));
    }
}
