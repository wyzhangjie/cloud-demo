package com.hyssop.algorithm.leetcode.questions.find;

import com.alibaba.druid.sql.visitor.functions.Char;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author jie.zhang
 * @create_time 2020/7/14 11:05
 * @updater
 * @update_time
 * LeetCode 第 03 题：给定一个字符串，请你找出其中不含有重复字符的最长子串的长度。
 *
 *  
 *
 * 示例 1
 *
 * 输入："abcabcbb"
 *
 * 输出：3
 *
 * 解释：因为无重复字符的最长子串是"abc"，其长度为3。
 *
 *  
 *
 * 示例 2
 *
 * 输入："bbbbb"
 *
 * 输出：1
 *
 * 解释：因为无重复字符的最长子串是 "b"，其长度为 1。
 *
 *  
 *
 * 示例 3
 *
 * 输入："pwwkew"
 *
 * 输出：3
 *
 * 解释：因为无重复字符的最长子串是 "wke"，其长度为 3。
 *
 * 注意：答案必须是子串的长度，"pwke" 是一个子序列，不是子串。
 * 子序列可以因为中途有重复字符而中断，子串不可用 像pwwkew ww重复，所以p不能跟k形成子串，被这两个重复的ww阻隔
 * 算法思想也是这样，遍历字符串，遇到重复的就要把之前重复的从集合中删除掉，继续找下一个能形成子串（也就是连续挨着，没有重复字符阻隔）。
 **/
public class Leccode03 {
    /**
     * 一步一步的移动慢指针效率低下，改进方式使用HashMap一步到位的知道该到哪个位置
     * */
    int lengthOfLongestSubstring(String s) {
        //记录不重复子串的最长串
        int scharsLen = s.toCharArray().length;
        int max=0;
        Set<Character> sets = new HashSet<>();
        //慢指针，用于遇到重复字符
        int i =0;
        //快指针，遇到重复字符的时候添加集合
        int j=0;
        for(;j<scharsLen;j++){
            while (sets.contains(s.charAt(j))){
              sets.remove(s.charAt(i));
              i++;
            }
            sets.add(s.charAt(j));
            max=Math.max(max,sets.size());

        }
        return max;
    }


    int lengthOfLongestSubstringoptimization(String s){
        int scharsLen = s.toCharArray().length;
        int max=0;
        Map<Character,Integer> sets = new HashMap<>();
        //慢指针，用于遇到重复字符
        int i =0;
        //快指针，遇到重复字符的时候添加集合
        int j=0;
        for(;j<scharsLen;j++){
            if(sets.containsKey(s.charAt(j))){
                i= Math.max(i,sets.get(s.charAt(j))+1);
            }
            //放值
            sets.put(s.charAt(j),j);
            //维护max最大值
            max=Math.max(max,j-i+1);
        }
        return max;
    }

    public static void main(String[] args) {
        Leccode03 leccode03 = new Leccode03();
        int max = leccode03.lengthOfLongestSubstring("pwwkew");
        max = leccode03.lengthOfLongestSubstringoptimization("pwwkew");
        System.out.println(max);
    }
}