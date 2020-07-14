package com.hyssop.algorithm.leetcode.questions.other;

import com.hyssop.algorithm.leetcode.questions.parent.Leetcode;

/**
 * @Description:    Given an array A of non-negative integers, return an array consisting of all the
 * even elements of A, followed by all the odd elements of A.
 *
 * You may return any answer array that satisfies this condition.
 *
 * Example 1:
 *
 * Input: [3,1,2,4] Output: [2,4,3,1] The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be
 * accepted. Note:
 *
 * 1 <= A.length <= 5000 0 <= A[i] <= 5000
 * @Author:         zhjie.zhang
 * @CreateDate:     2019/7/17$ 19:46$
 * @UpdateUser:     zhjie.zhang
 * @UpdateDate:     2019/7/17$ 19:46$
 * @Version:        1.0
 */
public class Leetcode905 extends Leetcode {
    /**
     * 执行用时 :
     * 3 ms
     * , 在所有 Java 提交中击败了
     * 95.04%
     * 的用户
     * 内存消耗 :
     * 44.2 MB
     * , 在所有 Java 提交中击败了
     * 76.57%
     * 的用户
    *
    * */

    public static int[] sortArrayByParity(int[] A) {
     int pre =0;
     int end = A.length-1;
     while(pre<end){
         while (A[pre]%2==0&&pre<end) pre++;
        if(pre<end){
            swap(A,pre,end);
        }


         end--;

     }
       return A;
    }


    public static void main(String[] args) {
        int[] a = new int[]{3, 1, 2, 4};
      /*  a = sortArrayByParity(a);
        print(a);*/
      /*  a = new int[]{1,0};
        a = sortArrayByParity(a);*/
      /*  int[] b = new int[]{3,1,2,4};
        b = sortArrayByParity(b);
        print(b);*/
        int[] c = new int[]{1,0,3};
        c = sortArrayByParity(c);
        print(c);
      /*  print(a);*/
    }
}
