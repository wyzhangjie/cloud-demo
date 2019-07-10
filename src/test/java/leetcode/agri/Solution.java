package leetcode.agri;

import java.util.Comparator;

/**
 * @Description:    给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。 输入: [3,30,34,5,9] 输出: 9534330
 * @Author:         zhjie.zhang
 * @CreateDate:     2019/6/24$ 20:35$
 * @UpdateUser:     zhjie.zhang
 * @UpdateDate:     2019/6/24$ 20:35$
 * @Version:        1.0
 */
public class Solution {

    public MyCompare myCompare = new MyCompare();

    public String largestNumber(int[] nums) {
        StringBuilder stringBuilder = new StringBuilder();
        int len = nums.length;
        // int sbLen = String.valueOf(nums).length();
        int tep = 0;
        while (tep != -1) {
            tep = -1;
            for (int i = 0; i < len; i++) {
                tep = nums[i];
                if (nums[i] == -1) {
                    continue;
                } else {
                    break;
                }

            }
            if (tep != -1) {
                tep = compare(tep, nums);
                stringBuilder.append(tep);
            }

        }

        return stringBuilder.toString();
    }

    public int compare(int temp, int[] nums) {
        int len = nums.length;
        int delSeat = -1;
        for (int i = 0; i < len; i++) {
            String k = String.valueOf(nums[i]);
            if (myCompare.compare(String.valueOf(temp), k) <= 0) {
                temp = nums[i];
                delSeat = i;
            }
        }
        if (delSeat != -1) {
            nums[delSeat] = -1;
        }
        return temp;
    }


    public class MyCompare implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            char[] from = o1.toCharArray();
            char[] to = o2.toCharArray();
            int i = 0;
            for (i = 0; i < from.length && i < to.length; i++) {
                if (from[i] > to[i]) {
                    return 1;
                } else if (to[i] > from[i]) {
                    return -1;
                } else {
                    continue;
                }
            }
            if (i < to.length) {
                if (to[i] > from[i - 1]) {
                    return -1;
                } else {
                    return 1;
                }


            }
            if (i < from.length) {
                if (from[i] > to[i - 1]) {
                    return 1;
                } else {
                    return -1;
                }


            }
            return 0;
        }
    }

    /**
     * 将数组转换成char，在自定义的对比方法中将需要的按位对比写好，然后循环每一个数据 将符合的数据放到buffer中。 时间复杂度O(n2) 空间复杂度O(n)
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        //9534330
        int[] nums = new int[]{3, 30, 34, 5, 9};

        int[] nums_1 = new int[]{3, 300, 34, 5, 9};
        int[] num_2 = new int[]{128, 12};
        int[] num_3 = new int[]{121,12};
        String str = solution.largestNumber(num_3);
        System.out.println(str);
    }
}
