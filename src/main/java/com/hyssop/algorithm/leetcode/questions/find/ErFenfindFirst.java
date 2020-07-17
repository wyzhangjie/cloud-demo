package com.hyssop.algorithm.leetcode.questions.find;

/**
 * @Author jie.zhang
 * @create_time 2020/7/16 14:14
 * @updater
 * @update_time
 * 查找某个元素是第一个位置
 **/
public class ErFenfindFirst {

    public static  int findFirst(int[] a,int tofind){
        int end=a.length-1;
        int begin=0;

        while (begin<=end){
            int mid = begin +((end-begin)>>1);
            if(a[mid]<tofind){
                begin =mid+1;
            }
            if(a[mid]>tofind){
                end = mid-1;
            }else {
                //这个时候我们找到了需要找到的tofind ,那么怎么确定他是第一个呢？？？
                //如果这个mid指向的在第一个位置则肯定是第一个，或者说如果这个值指向的前一个值小于这个值，也找到了第一个位置
                if(mid==0 || a[mid-1]!=tofind) {
                    return mid;
                }else {
                    //否则往左继续寻找
                    end=mid-1;
                }
            }
        }
        return -1;


    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 1, 1, 1, 2, 2, 2, 4, 5, 8, 9};
        int[] b = new int[]{4, 5, 6, 7, 0, 1, 2};
        int[] c = new int[]{3, 5, 1};
        int[] d = new int[]{3, 1};
        int[] e = new int[]{5, 1, 3};
        int[] f = new int[]{5,1,2,3,4};
        //   System.out.println(getInt(a, 12));
        // System.out.println(getIntRever(a, a.length,15));
        //   System.out.println(getResult(1, 2, 2));
        //   System.out.println(findLastSmallEnent(a,1));
       ;
        System.out.println( findFirst(f,1));
    }
}