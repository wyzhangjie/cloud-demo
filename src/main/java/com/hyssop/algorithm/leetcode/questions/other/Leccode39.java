package com.hyssop.algorithm.leetcode.questions.other;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author jie.zhang
 * @create_time 2020/7/13 15:15
 * @updater
 * @update_time
 *
 *
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 *
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1：
 *
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * 示例 2：
 *
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 提示：
 *
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都是独一无二的。
 * 1 <= target <= 500
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 代码模板
 *
 * function fn(n) {
 *
 *     // 第一步：判断输入或者状态是否非法？
 *     if (input/state is invalid) {
 *         return;
 *   }
 *
 *     // 第二步：判读递归是否应当结束?
 *     if (match condition) {
 *         return some value;
 *   }
 *
 *     // 遍历所有可能出现的情况
 *     for (all possible cases) {
 *   
 *         // 第三步: 尝试下一步的可能性
 *         solution.push(case)
 *         // 递归
 *         result = fn(m)
 *
 *         // 第四步：回溯到上一步
 *         solution.pop(case)
 *     
 *     }
 *     
 * }
 **/
public class Leccode39 {
    static List<List<Integer>> endResult = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> midResult = new ArrayList<>();
        Arrays.sort(candidates);
          combinationSumSub(candidates,0,target,midResult);
        return endResult;
    }

    private void combinationSumSub(int[] candidates, int beginIndex,int target, List<Integer> midResult) {
        if(target<0){
            return ;
        }
        if(target==0){
            //必须保存快照，后面还会对这个数组做减员处理，会弄乱结果
            endResult.add(new ArrayList<>(midResult));
        }

        for(int i=beginIndex;i<candidates.length;i++){
            if(target<candidates[i]) break;
                midResult.add(candidates[i]);
                combinationSumSub(candidates,i,target-candidates[i],midResult);
               midResult.remove(Integer.valueOf(candidates[i]));
        }
    }

    public static void main(String[] args) {
        Leccode39 leccode39 = new Leccode39();
        int[] candidates={2,3,6,7};
       leccode39.combinationSum(candidates,7);
        System.out.println(JSON.toJSONString(endResult));
    }

}