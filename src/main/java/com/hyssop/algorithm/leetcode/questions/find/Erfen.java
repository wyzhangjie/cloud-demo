package com.hyssop.algorithm.leetcode.questions.find;


import com.hyssop.algorithm.leetcode.questions.parent.Leetcode;

/**
 * @Description:    写一个二分查找 并且写几个二分查找的应用
 * @Author:         zhjie.zhang
 * @CreateDate:     2019/7/19$ 15:52$
 * @UpdateUser:     zhjie.zhang
 * @UpdateDate:     2019/7/19$ 15:52$
 * @Version:        1.0
 * 二分查找依赖的是顺序结构，就是数组。
 *
 * 二分查找依赖顺序结构的顺序性
 *
 * 太小的数据用二分不划算（r如果小的数组里面数据比较耗时比较长，二分查找能够减少比较次数）
 *
 */
public class Erfen extends Leetcode {

    public static int getInt(int[] a, int p) {
        int begin = 0;
        int end = a.length - 1;
        while (begin <= end) {
            int mid = begin + ((end - begin) >> 1);
            if (a[mid] == p)
                return mid;
            if (a[mid] < p) begin = mid + 1;
            if (a[mid] > p) end = mid - 1;
        }
        return -1;
    }


    //递归实现二分查找
    public static int getIntRever(int[] a, int n, int val) {
        return getIntRevertFor(a, 0, n - 1, val);
    }

    private static int getIntRevertFor(int[] a, int begin, int end, int val) {
        if (begin > end) return -1;
        int mid = begin + ((end - begin) >> 1);
        if (a[mid] == val) {
            return mid;
        }
        if (a[mid] < val) {
            return getIntRevertFor(a, mid + 1, end, val);
        }
        if (a[mid] > val) {
            return getIntRevertFor(a, begin, mid - 1, val);
        }
        return -1;
    }

    /**
     * 求一个数的根号
     */
    private static double accuracy = 0.000001;

    public static double getResult(double begin, double end, int result) {
        double mid = begin;
        while (begin <= end) {
            mid = (begin + end) / 2;
            if (mid * mid <= result + result * accuracy && result - result * accuracy <= mid * mid) {
                return mid;
            }
            if (mid * mid < result - result * accuracy) {
                begin = mid;
            }
            if (mid * mid > result + result * accuracy) {
                end = mid;
            }
        }
        return mid;
    }

    /**
     * 查找第一个值为给定值的元素
     */
    public static int findFirstEnent(int[] event, int t) {
        int find = -1;
        int begin = 0;
        int end = event.length - 1;
        while (begin <= end) {
            int mid = begin + ((end - begin) >> 1);
            if (event[mid] == t) {
                find = mid;
                end = mid - 1;
            }
            if (event[mid] > t) {
                end = mid - 1;
            }
            if (event[mid] < t) {
                begin = mid + 1;
            }
        }

        return find == -1 ? -1 : find;
    }


    /**
     * 查找最后一个值等于给定值的元素
     */
    public static int findLastEnent(int[] event, int t) {
        int find = -1;
        int begin = 0;
        int end = event.length - 1;
        while (begin <= end) {
            int mid = begin + ((end - begin) >> 1);
            if (event[mid] == t) {
                find = mid;
                begin = mid + 1;
            }
            if (event[mid] > t) {
                end = mid - 1;
            }
            if (event[mid] < t) {
                begin = mid + 1;
            }
        }

        return find == -1 ? -1 : find;
    }

    /**
     * 查找第一个大于等于给定值的元素
     */

    public static int findFirstLageEnent(int[] event, int t) {
        int find = -1;
        int begin = 0;
        int end = event.length - 1;
        while (begin <= end) {
            int mid = begin + ((end - begin) >> 1);
            if (event[mid] == t) {
                find = mid;
                begin = mid + 1;
            }
            if (event[mid] > t) {
                end = mid - 1;
            }
            if (event[mid] < t) {
                begin = mid + 1;
            }
        }
        int result = find == -1 ? -1 : find + 1;
        return result;
    }

    /**
     * 查找最后一个小于给定值的元素
     */
    public static int findLastSmallEnent(int[] event, int t) {
        int find = -1;
        int begin = 0;
        int end = event.length - 1;
        while (begin <= end) {
            int mid = begin + ((end - begin) >> 1);
            if (event[mid] == t) {
                find = mid;
                end = mid - 1;
            }
            if (event[mid] > t) {
                end = mid - 1;
            }
            if (event[mid] < t) {
                begin = mid + 1;
            }
        }

        int result = find == -1 ? -1 : find - 1;
        return result;
    }

    /**
     * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
     *
     * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
     *
     * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
     *
     * 你可以假设数组中不存在重复的元素。
     *
     * 你的算法时间复杂度必须是 O(log n) 级别。
     *
     * 示例 1:
     *
     * 输入: nums = [4,5,6,7,0,1,2], target = 0 输出: 4 示例 2:
     *
     * 输入: nums = [4,5,6,7,0,1,2], target = 3 输出: -1
     *
     * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static int searchWrong(int[] nums, int target) {
        //找到那个被旋转的点
        int begin = 0;
        int last = nums.length - 1;
        int mid = -1;
        while (begin <= last) {
            mid = begin + ((last - begin) >> 1);
            if (target == nums[mid]) {
                return mid;
            }
            if (nums[mid] >= nums[last]) {

                if (target < nums[mid] && target > nums[last]) {
                    last = mid - 1;
                    break;
                }
                if (target < nums[mid] && target <= nums[last]) {
                    begin = mid + 1;
                    break;
                }
            }
            if (nums[mid] < nums[last] && nums[mid] < nums[begin]) {
                if (target > nums[last]) {
                    last = mid - 1;
                    break;
                }
                if (target <= nums[last]) {
                    begin = mid + 1;
                    break;
                }

            } else {
                if (target <= nums[mid]) {
                    last = mid;
                } else {
                    begin = mid + 1;
                }
            }

        }

        //二分查找
        int result = getIntRevertFor(nums, begin, last, target);
        return result;


    }

    public static int searchRight(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) right = mid - 1;
                else left = mid + 1;

            } else if (nums[mid] < nums[right]) {
                if (nums[mid] < target && target <= nums[right]) left = mid + 1;
                else right = mid - 1;
            }
        }
        return nums[left] == target ? left : -1;

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
        System.out.println(searchWrong(f, 1));
    }
}
