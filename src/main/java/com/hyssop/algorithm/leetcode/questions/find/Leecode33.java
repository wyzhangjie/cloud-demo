package com.hyssop.algorithm.leetcode.questions.find;

/**
 * @Author jie.zhang
 * @create_time 2020/7/16 16:20
 * @updater
 * @update_time
 *
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
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例 2:
 *
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class Leecode33 {
    public static int search(int[] nums, int target) {
        int left = 0;
        int right=nums.length-1;
        int mid = left;
        while(left<=right){
            mid = left+((right-left)>>1);
            if(target==nums[mid]){
                return mid;
            }
            if(mid==0){
                left = mid+1;
                continue;
            }
            if(mid==(nums.length-1)){
                return -1;
            }
            if(nums[mid]<nums[mid-1] && nums[mid]>nums[mid+1]){
                if(target==nums[left]){
                    return left;
                }
                if(target==nums[right]){
                    return right;
                }
                if(target<nums[left] && target<nums[right]){

                    left= mid+1;
                    continue;
                }
                if(target>nums[left] &&target>nums[right]){
                    if(target<nums[left] && target<nums[right]){
                        left = mid +1;
                        continue;
                    }
                    if(target>nums[right] && target<nums[left]){
                        right=mid-1;
                        continue;
                    }else {
                        return -1;
                    }
                }
            }

            if(nums[mid]>nums[mid-1] && nums[mid]>nums[mid+1]){
                if(target==nums[left]){
                    return left;
                }
                if(target==nums[right]){
                    return right;
                }
               if(target<nums[left] && target<nums[right]){
                   left= mid+1;
                   continue;
               }
               if(target>nums[left] &&target>nums[right]){
                   if(target<nums[mid]){
                       right = mid -1;

                       continue;
                   }
                   if(target>nums[mid]){
                       left= mid+1;
                       continue;
                   }
               }else {
                   return -1;
               }

            }else {
                if(target>nums[mid]){
                    left=mid+1;
                    continue;

                }
                if(target<nums[mid]){
                    right=mid-1;
                    continue;

                }
            }
        }
        return  -1;
    }
    public  int searchForOld(int[] nums, int target) {
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
        int[] nums =new int[]{5,3,1};
       // int[] nums =new int[]{4,5,6,7,0,1,2};
        System.out.println(search(nums,5));;
        System.out.println(search(nums,3));;
        System.out.println(search(nums,1));;
        System.out.println(search(nums,2));;
       /* System.out.println(search(nums,4));
        System.out.println(search(nums,5));;
        System.out.println(search(nums,6));
        System.out.println(search(nums,7));
        System.out.println(search(nums,0));
        System.out.println(search(nums,1));
        System.out.println(search(nums,2));*/
    }
}