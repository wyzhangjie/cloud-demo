package com.hyssop.algorithm.leetcode.questions.find;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Author jie.zhang
 * @create_time 2020/7/14 15:26
 * @updater
 * @update_time LeetCode 第 56 题：给出一个区间的集合，请合并所有重叠的区间。
 * <p>
 * <p>
 * <p>
 * 示例 1
 * <p>
 * 输入: [[1,3], [2,6], [8,10], [15,18]]
 * <p>
 * 输出: [[1,6], [8,10], [15,18]]
 * <p>
 * <p>
 * <p>
 * 解释: 区间 [1,3] 和 [2,6] 重叠，将它们合并为 [1,6]。
 * <p>
 * <p>
 * <p>
 * 示例 2
 * <p>
 * 输入: [[1,4], [4,5]]
 * <p>
 * 输出: [[1,5]]
 * <p>
 * <p>
 * <p>
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 **/
public class LeetCode56 {
    //方法1，贪婪算法
    /**
     * 时间复杂度 O(nlog(n))，因为一开始要对数组进行排序。
     *
     *
     *
     * 空间复杂度为 O(n)，因为用了一个额外的 result 数组来保存结果。
     *
     *  
     *
     * 注意：和区间相关的问题，有非常多的变化，融合区间可以说是最基本也是最常考的一个
     * */
    int[][] merge(int[][] intervals) {
        List<int[]> result = new ArrayList<>();
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));
        int[] previous = null;
        for (int[] current : intervals) {
            if (previous == null || current[0] >= previous[1]) {
                //如果没有重叠或者是第一个节点，则将其增加到结果链表中
                result.add(previous = current);
            } else {
                //如果有重叠，则更改区间的结尾数据为两者最大值
                previous[1] = Math.max(previous[1], current[1]);
            }
        }
        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        int[][] a = new int[][]{{1, 4}, {3, 5}};

        LeetCode56 leetCode56 = new LeetCode56();
        int[][] result = leetCode56.merge(a);
        for(int i=0;i<result.length;i++){
            for(int j=0;j<2;j++){
                System.out.println(result[i][j]);
            }
        }
    }
}