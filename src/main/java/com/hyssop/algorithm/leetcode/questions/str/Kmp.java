package com.hyssop.algorithm.leetcode.questions.str;

/**
 * @Author jie.zhang
 * @create_time 2020/7/15 9:55
 * @updater
 * @update_time
 **/
public class Kmp {

   static int[] getNext(char[] a){

        int len = a.length;
        int[] next = new int[len];
        int k=-1;
       //0位置永远的-1
        next[0]=-1;
        //从第一个起，开始按照next的数据开始构造自己再失效的时候需要回溯的位置
        for(int i=1;i<len;i++){
            //如果是第二个数，由于第一个数next[0]是-1
            //如果他要是跟next[0]不匹配则要回溯到next[0] 计：next[1]=-1;
            //如果匹配则会将-1+1=0，下次回溯从1开始。之后每一次都按照以下规则去找到自己的位置
            //1、如果能够跟前一个next的内容位置所在的字符相匹配，则位置变成前一个next的内容位置+1
            //2、如果跟前一个next的内容对应的位置不匹配，则位置继续回溯到前一个next的内容所在位置的前一个位置，并将自身值，跟那个位置后面那个字符向比较
            //2.1如果相同，则将自己的next内容设置成匹配的next内容位置+1，
            //2.2如果不匹配，会因为k==-1,而将自身的next内容设置成-1.
            while(k!=-1 && a[k+1]!=(a[i])){
                k=next[k];
            }
            if(a[k+1]==(a[i])){
                k++;
            }
            next[i]=k;
        }
        return next;
    }
    public static int kmp(char[] a,int n,char[] b,int m){
        int[] next = getNext(b);
        int j=0;
        for(int i=0;i<n;i++){
            while(j>0 && a[i]!=b[j]){
                j=next[j-1]+1;
            }
            if(a[i]==b[j]){
                ++j;
            }
            if(j==m){
                return i-m+1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        char[] a = new char[]{'a','b','a'};
        char[] b = new char[]{'b','a'};
        System.out.println(kmp(a,3,b,2));
    }
}