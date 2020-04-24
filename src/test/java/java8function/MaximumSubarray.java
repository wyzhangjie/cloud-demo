package java8function;

/**给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 进阶:
 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 * @Author jie.zhang
 * @create_time 2020/2/20 17:23
 * @updater
 * @update_time
 **/
public class MaximumSubarray {
    /**
     * 贪婪算法
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums)  {
    int len = nums.length;
    int curSum=nums[0];
    int maxSum=nums[0];
    for(int i=1;i<len;i++){
    curSum= Math.max(nums[i],curSum+nums[i]);
    maxSum =Math.max(maxSum,curSum);
    }
    return maxSum;


    }

    public static void main(String[] args)  {
        int[] a = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        MaximumSubarray maximumSubarray = new MaximumSubarray();
        maximumSubarray.maxSubArray(a);
    }
}