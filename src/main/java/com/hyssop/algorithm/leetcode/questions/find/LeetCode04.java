package com.hyssop.algorithm.leetcode.questions.find;

/**
 * @Author jie.zhang
 * @create_time 2020/7/14 12:04
 * @updater
 * @update_time LeetCode 第 04 题：给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m+n))。你可以假设 nums1 和 nums2 不会同时为空。
 * <p>
 * <p>
 * <p>
 * 示例1
 * <p>
 * nums1 = [1, 3]
 * <p>
 * nums2 = [2]
 * <p>
 * 则中位数是 2.0
 * <p>
 * <p>
 * <p>
 * 示例2
 * <p>
 * nums1 = [1, 2]
 * <p>
 * nums2 = [3, 4]
 * <p>
 * 则中位数是 (2 + 3)/2 = 2.5
 * <p>
 * 1. 当 a2 = b1 时，可以肯定  a2 和 b1 就是第 5 小的数。
 * <p>
 * 2. 当 a2 < b1 的时候，无法肯定 a2 和 b1 是不是第 5 小的数。举例如下。
 * 而最终第 5 小的数是 a3 5 这个数。因此，在这种情况下，我们不能得出第 5 小的数是哪个。
 * <p>
 * <p>
 * <p>
 * 但是，在这种情况下，至少我们可以肯定的是，我们要找的结果肯定不会在 a0，a1，a2 之间，即不会出现在 nums1 数组的前半段里。为什么呢？很简单，因为如果第 5 小的数是 a0，a1，a2 其中一个的话，意味着 k1+k2 必然大于 5，这就跟我们的假设不符了。
 * <p>
 * <p>
 * <p>
 * 那么结果会不会在 nums2 的后半段呢？不可能，加入第 5 小的数在 nums2 的后半段，那么意味着，这个数要大于 b1（即  7），也会大于 a2（即 3），但是 k1 + k2 已经等于 5了，所以就和假设冲突了。
 * 在这样的情况下，我们可以把搜索的范围缩小，从 nums1 的后半段以及 nums2 的前半段中继续寻找。
 * <p>
 * <p>
 * <p>
 * 当 a2 > b1 的时候，无法肯定 a2 和 b1 是不是第 5 小的数。举例如下。
 * <p>
 * nums1[] = {5, 6, 7, 8, 9}
 * <p>
 * nums2[] = {1, 2, 3, 4}
 * <p>
 * <p>
 * <p>
 * a2 = 7，b1 = 2
 * <p>
 * <p>
 * <p>
 * 而最终第 5 小的数是 a0 5 这个数。因此，在这种情况下，我们也不能得出第 5 小的数是哪个。
 * <p>
 * <p>
 * <p>
 * 但是，在这种情况下，至少我们可以肯定的是，我们要找的结果肯定不会是 b0，或者
 * <p>
 * nums2 数组的前半段里。为什么呢？因为如果第 5 小的数是 b0 的话，意味着 k1+k2 必然大
 * <p>
 * 于 5，这也跟我们的假设不符了。同样的，结果也不可能在 nums1 的后半段里。
 * <p>
 * <p>
 * <p>
 * 在这样的情况下，我们可以把搜索的范围缩小，从 nums2 的后半段以及 nums1 中继续寻找。
 **/
public class LeetCode04 {
    double findMedianSortedArrays(int nums1[], int nums2[]) {
        int m = nums1.length;
        int n = nums2.length;

        int k = (m + n) / 2;
        //如果是基数个数，那么就找最中间那一个值就可以了
        if ((m + n) % 2 == 1) {
            return findKth(nums1, 0, m - 1, nums2, 0, n - 1, k + 1);
        } else {
            //如果个数是偶数个，则去中间两个值的平均值
            return (
                    findKth(nums1, 0, m - 1, nums2, 0, n - 1, k) +
                            findKth(nums1, 0, m - 1, nums2, 0, n - 1, k + 1)
            ) / 2.0;
        }
    }

    double findKth(int[] nums1, int l1, int h1, int[] nums2, int l2, int h2, int k) {
        int m = h1 - l1 + 1;
        int n = h2 - l2 + 1;

        if (m > n) {
            return findKth(nums2, l2, h2, nums1, l1, h1, k);
        }

        if (m == 0) {
            return nums2[l2 + k - 1];
        }

        if (k == 1) {
            return Math.min(nums1[l1], nums2[l2]);
        }

        int na = Math.min(k / 2, m);
        int nb = k - na;
        int va = nums1[l1 + na - 1];
        int vb = nums2[l2 + nb - 1];

        if (va == vb) {
            return va;
        } else if (va < vb) {
            return findKth(nums1, l1 + na, h1, nums2, l2, l2 + nb - 1, k - na);
        } else {
            return findKth(nums1, l1, l1 + na - 1, nums2, l2 + nb, h2, k - nb);
        }

    }
}