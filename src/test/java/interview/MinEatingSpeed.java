package interview;

/**
 * 珂珂喜欢吃香蕉。这里有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 H 小时后回来。
 *
 * 珂珂可以决定她吃香蕉的速度 K （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。  
 *
 * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 *
 * 返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/koko-eating-bananas
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * */
public class MinEatingSpeed {

    public int minEatingSpeed(int[] piles, int H) {
        int max = getMax(piles);
        int min = 1;
        for(int i=1;i<max;i++){
            if(canFinish(piles,i,H)){
                return i;
            }
        }
        return max;
    }

    public int minEatingSpeedV2(int[] piles, int H){
        //二分法来降低搜索时间
        int left = 1;
        int right =getMax(piles)+1;
        while(left<right){
            int mid = left +(right-left)/2;
            if(canFinish(piles,mid,H)){
                right = mid;
            }else {
                left = mid+1;
            }
        }
        return left;
    }

    private boolean canFinish(int[] piles, int i, int h) {
        int time=0;
        for(int n:piles){
            time+= timeOf(n,i);
        }
        return time<=h;
    }

    private int timeOf(int pile, int i) {
        return (pile/i)+(pile%i>0?1:0);
    }

    private int getMax(int[] piles) {
       int max =0;
        for(int i=0;i<piles.length;i++){
            if(piles[i]>=max){
                max = piles[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] piles = {30,11,23,4,20};
        int H = 6;
        MinEatingSpeed minEatingSpeed = new MinEatingSpeed();
        System.out.println(minEatingSpeed.minEatingSpeed(piles,H));
        System.out.println(minEatingSpeed.minEatingSpeedV2(piles,H));
    }
}
