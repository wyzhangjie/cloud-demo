package com.hyssop.algorithm.leetcode.questions.dpproblem;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * Note:
 *
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * Example 1:
 *
 * Input: s = "leetcode", wordDict = ["leet", "code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * Example 2:
 *
 * Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 *              Note that you are allowed to reuse a dictionary word.
 * Example 3:
 *
 * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: false
 * */
public class Lession139 {

    public boolean wordBreak(String s, List<String> wordDict) {
    char[] chars = s.toCharArray();
        int len = chars.length+1;
        Boolean[] booleans = new Boolean[len];
        booleans[0] = true;
        for(int i=1;i<len;i++){
            for(int j=0;j<i;j++){
                booleans[i] = booleans[j]&& wordDict.contains(s.substring(j,i));
                if(booleans[i]) break;
            }
        }
        return booleans[len-1];
    }

    public static void main(String[] args) {
        List<String> wordDict = new ArrayList<>();
        wordDict.add("cats");
        wordDict.add("dog");
        wordDict.add("sand");
        wordDict.add("and");
        wordDict.add("cat");
        String s= "dogsand";
        Lession139 lession139 = new Lession139();
        System.out.println(lession139.wordBreak(s,wordDict));

    }
}
