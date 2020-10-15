package com.hyssop.algorithm.leetcode.questions.find;


/**
 * @Author jie.zhang
 * @create_time 2020/7/14 14:31
 * @updater
 * @update_time 找到一个无序两个数组里面第K大的值
 **/
public class FindTopK {

    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, k);
    }

    // 随机取一个基准值，这里取最后一个数作为基准值
    int quickSelect(int[] nums, int low, int high, int k) {
        int pivot = low;

        // 比基准值小的数放左边，把比基准值大的数放右边
        for (int j = low; j < high; j++) {
            if (nums[j] <= nums[high]) {
                swap(nums, pivot++, j);
            }
        }
        swap(nums, pivot, high);

        // 判断基准值的位置是不是第 k 大的元素
        int count = high - pivot + 1;
        // 如果是，就返回结果。
        if (count == k) {
            return nums[pivot];
        }
        // 如果发现基准值小了，继续往右边搜索
        if (count > k) {
            return quickSelect(nums, pivot + 1, high, k);
        }
        // 如果发现基准值大了，就往左边搜索
        return quickSelect(nums, low, pivot - 1, k - count);

    }

    public static void main(String[] args) {

    }


    double findMedianArrays(int[] nums1, int[] nums2) {
    int m = nums1.length;
    int n = nums2.length;
  
    int k = (m + n) / 2;
  
    return (m + n) % 2 == 1 ?
        findKthLargest(nums1, nums2, k + 1) :
        (findKthLargest(nums1, nums2, k) + findKthLargest(nums1, nums2, k + 1)) / 2.0;
    }

    double findKthLargest(int[] nums1, int[] nums2, int k) {
    return quickSelect(nums1, nums2, 0, nums1.length + nums2.length - 1, k);
    }

    double quickSelect(int[] nums1, int[] nums2, int low, int high, int k) {
    int pivot = low;

    // use quick sort's idea
    // put nums that are <= pivot to the left
    // put nums that are  > pivot to the right
    for (int j = low; j < high; j++) {
        if (getNum(nums1, nums2, j) <= getNum(nums1, nums2, high)) {
            swap(nums1, nums2, pivot++, j);
        }
    }
    swap(nums1, nums2, pivot, high);
  
    // count the nums that are > pivot from high
    int count = high - pivot + 1;
    // pivot is the one!
    if (count == k) return getNum(nums1, nums2, pivot);
    // pivot is too small, so it must be on the right
    if (count > k) return quickSelect(nums1, nums2, pivot + 1, high, k);
    // pivot is too big, so it must be on the left
    return quickSelect(nums1, nums2, low, pivot - 1, k - count);
    }

    int getNum(int[] nums1, int[] nums2, int index) {
    return (index < nums1.length) ? nums1[index] : nums2[index - nums1.length];
    }

    void swap(int[] nums1, int[] nums2, int i, int j) {
    int m = nums1.length;
  
    if (i < m && j < m) {
        swap(nums1, i, j);
    } else if (i >= m && j >= m) {
        swap(nums2, i - m, j - m);
    } else if (i < m && j >= m) {
        int temp = nums1[i];
        nums1[i] = nums2[j - m];
        nums2[j - m] = temp;
    }
    }

    void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
    }
}