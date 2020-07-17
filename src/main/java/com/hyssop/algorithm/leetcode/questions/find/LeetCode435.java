package com.hyssop.algorithm.leetcode.questions.find;

import java.util.Arrays;

/**
 * @Author jie.zhang
 * @create_time 2020/7/14 16:07
 * @updater
 * @update_time 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 * <p>
 * 注意:
 * <p>
 * 可以认为区间的终点总是大于它的起点。
 * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 * 示例 1:
 * <p>
 * 输入: [ [1,2], [2,3], [3,4], [1,3] ]
 * <p>
 * 输出: 1
 * <p>
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 * 示例 2:
 * <p>
 * 输入: [ [1,2], [1,2], [1,2] ]
 * <p>
 * 输出: 2
 * <p>
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 * 示例 3:
 * <p>
 * 输入: [ [1,2], [2,3] ]
 * <p>
 * 输出: 0
 * <p>
 * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/non-overlapping-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class LeetCode435 {
    //暴力方法
    //递归选择，在删除当前和不删除当前两种情况下的最好结果
    int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));
        return eraseOverlapIntervals(-1, 0, intervals);
    }

    /**
     * description
     *
     * @param
     * @return 要删除的区间个数
     * @Author jie.zhang
     * @create_time 2020/7/14 16:17
     */
    private int eraseOverlapIntervals(int pre, int cur, int[][] intervals) {
        //递归终结位置
        if (cur == intervals.length) {
            return 0;
        }
        int token = Integer.MAX_VALUE;
        int notoken = 0;
        if (pre == -1 || intervals[pre][1] <= intervals[cur][0]) {
            //  只有当prev,  curr没有发生重叠的时候，才可以选择保留当前的区间curr
            token = eraseOverlapIntervals(cur, cur + 1, intervals);
        }

        notoken = eraseOverlapIntervals(pre, cur + 1, intervals) + 1;
        return Math.min(token, notoken);


    }

    public static void main(String[] args) {
        int[][] a = new int[][]{{1, 4}, {4, 5}, {1, 7}};
        LeetCode435 leetCode435 = new LeetCode435();
        int delCount = leetCode435.eraseOverlapIntervals(a);
        System.out.println(delCount);
    }

    //解题思路二：贪婪法 所谓贪婪就是说哪个删除对结果最有利删除哪个，贪婪算法不一定能够得到全局最优解，只能得到局部最优解。

    int eraseOverlapIntervalsTanLan(int[][] intervals) {
        if (intervals.length == 0) return 0;

        //  将所有区间按照起始时间排序
        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));

        //  用一个变量  end  记录当前的最小结束时间点，以及一个  count  变量记录到目前为止删除掉了多少区间
        int end = intervals[0][1], count = 0;

        //  从第二个区间开始，判断一下当前区间和前一个区间的结束时间
        for (int i = 1; i < intervals.length; i++) {
            //  当前区间和前一个区间有重叠，即当前区间的起始时间小于上一个区间的结束时间，end记录下两个结束时间的最小值，把结束时间晚的区间删除，计数加1。
            if (intervals[i][0] < end) {
                end = Math.min(end, intervals[i][1]);
                count++;
            } else {
                end = intervals[i][1];
            }
        }

        //  如果没有发生重叠，根据贪婪法，更新  end  变量为当前区间的结束时间
        return count;

    }

    /**
     * 题目演变：在给定的区间中，最多有多少个区间相互之间是没有重叠的？
     */
    int eraseOverlapIntervalsExtend(int[][] intervals) {
        if (intervals.length == 0) return 0;

        //  按照结束时间将所有区间进行排序
        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[1], i2[1]));

        //  定义一个变量  end  用来记录当前的结束时间，count  变量用来记录有多少个没有重叠的区间  
        int end = intervals[0][1], count = 1;

        //  从第二个区间开始遍历剩下的区间    
        for (int i = 1; i < intervals.length; i++) {
            //  当前区间和前一个结束时间没有重叠，那么计数加  1，同时更新一下新的结束时间
            if (intervals[i][0] >= end) {
                end = intervals[i][1];
                count++;
            }
        }

        //  用总区间的个数减去没有重叠的区间个数，即为最少要删除掉的区间个数
        return intervals.length - count;
    }


}