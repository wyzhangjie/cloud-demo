package com.hyssop.algorithm.leetcode.questions.find;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @Author jie.zhang
 * @create_time 2020/7/15 10:50
 * @updater
 * @update_time 给定一组唯一的单词， 找出所有不同  的索引对(i, j)，使得列表中的两个单词，  words[i] + words[j]  ，可拼接成回文串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["abcd","dcba","lls","s","sssll"]
 * 输出: [[0,1],[1,0],[3,2],[2,4]]
 * 解释: 可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
 * 示例 2:
 * <p>
 * 输入: ["bat","tab","cat"]
 * 输出: [[0,1],[1,0]]
 * 解释: 可拼接成的回文串为 ["battab","tabbat"]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class Leecode336 {
    boolean isPalindrome(String word, int i, int j) {
        while (i < j) {
            if (word.charAt(i++) != word.charAt(j--)) {
                return false;
            }
        }

        return true;

    }

    //  修改  TrieNode  结构，用  index  替换  isEnd
    class TrieNode {
        int index;
        List<Integer> palindromes;
        HashMap<Character, TrieNode> children;

        //  添加一个  palindromes  列表，用来记录从该节点往下的能构成回文的所有输入字符串的下标
        TrieNode() {
            index = -1;
            children = new HashMap<>();
            palindromes = new ArrayList<>();
        }
    }

    List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();  //  定义一个空的列表，用来记录找到的配对

        TrieNode root = new TrieNode();  //  定义一个  Trie  的根节点  root

        for (int i = 0; i < words.length; i++) {
            addWord(root, words[i], i);
        }  //  创建  Trie

        for (int i = 0; i < words.length; i++) {
            search(words, i, root, res);
        }//  利用  Trie，找出所有的配对

        return res;
    }

    //  创建  Trie  的时候，从每个字符串的末尾开始遍历
    void addWord(TrieNode root, String word, int index) {
        for (int i = word.length() - 1; i >= 0; i--) {
            char ch = word.charAt(i);

            //  对于每个当前字符，如果它还没有被添加到  children  哈希表里，就创建一个新的节点
            if (!root.children.containsKey(ch)) {
                root.children.put(ch, new TrieNode());
            }
            //  若该字符串从头开始到当前位置能成为回文的话，把这个字符串的下标添加到这个  Trie  节点的回文列表里
            if (isPalindrome(word, 0, i)) {
                root.palindromes.add(index);
            }

            root = root.children.get(ch);
        }

        //  当对该字符串创建完  Trie  之后，将字符串的下标添加到回文列表里，并且将它赋给  index
        root.palindromes.add(index);
        root.index = index;

    }

    //  处理查找，从头遍历每个字符串，然后从  Trie  里寻找匹配的字符串
    void search(String[] words, int i, TrieNode root, List<List<Integer>> res) {

        //  k1  >  k2，且  s1  剩下的字符能构成回文，就把这对组合添加到结果中
        //  k1=k2  或  k1<k2，只需要把回文列表里的字符串都和  s1  组合即可        
        for (int j = 0; j < words[i].length(); j++) {
            if (root.index >= 0 && root.index != i &&
                    isPalindrome(words[i], j, words[i].length() - 1)) {
                res.add(Arrays.asList(i, root.index));
            }

            root = root.children.get(words[i].charAt(j));
            if (root == null) {
                return;
            }
        }

        for (int j : root.palindromes) {
            if (i == j) {
                continue;
            }
            res.add(Arrays.asList(i, j));
        }
    }

    public static void main(String[] args) {
        String[] words = new String[]{"abcd","dcba","lls","s","sssll"};
        Leecode336 leecode336 = new Leecode336();
        List<List<Integer>> l = leecode336.palindromePairs(words);
        l.stream().forEach((a)->a.stream().forEach((b)->{
            System.out.print(b);
        }));
    }
}