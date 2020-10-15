package com.hyssop.algorithm.leetcode.questions.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author jie.zhang
 * @create_time 2020/7/13 14:49
 * @updater
 * @update_time
 * LeetCode 第 247 题：找到所有长度为 n 的中心对称数。
 *
 *  
 *
 * 示例
 *
 * 输入:  n = 2
 *
 * 输出: ["11","69","88","96"]
 **/
public class Leecode247 {
    List<String> helper(int n, int m) {
    // 第一步：判断输入或者状态是否非法？
    if (n < 0 || m < 0 || n > m) {
        throw new IllegalArgumentException("invalid input");
  }

    // 第二步：判读递归是否应当结束?
    if (n == 0) return new ArrayList<String>(Arrays.asList(""));
    if (n == 1) return new ArrayList<String>(Arrays.asList("0", "1", "8"));
  
    // 第三步：缩小问题规模
    List<String> list = helper(n - 2, m); 

    // 第四步: 整合结果
    List<String> res = new ArrayList<String>();
  
    for (int i = 0; i < list.size(); i++) {
        String s = list.get(i);
    
        if (n != m) res.add("0" + s + "0");
    
        res.add("1" + s + "1");
        res.add("6" + s + "9");
        res.add("8" + s + "8");
        res.add("9" + s + "6");
    }
  
    return res;
    }
}