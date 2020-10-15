package leetcode.dpproblem;

public class Text1 {

    public int findMaxSub(int[] nums){
        int len = nums.length;
        int dp[] = new int[len+1];
        dp[0]=-1;
        for(int i=1;i<=len;i++){
            dp[i]=-1;
        }
        int max=0;
        for(int i=0;i<len;i++){
            dp[i+1]=Math.max(dp[i]+nums[i],nums[i]);
            max=Math.max(dp[i+1],max);

        }
        return max;

    }

    public static void main(String[] args) {
        int[] a = new int[]{-2,1,-3,1,-1,6,2,-5,4};
        Text1 text1 = new Text1();
        System.out.println(text1.findMaxSub(a));
    }
}
