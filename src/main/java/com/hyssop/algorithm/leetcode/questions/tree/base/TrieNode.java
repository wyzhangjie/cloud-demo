package com.hyssop.algorithm.leetcode.questions.tree.base;

/**
 * @Description:    java类作用描述
 * @Author:         zhjie.zhang
 * @CreateDate:     2019/7/30$ 17:25$
 * @UpdateUser:     zhjie.zhang
 * @UpdateDate:     2019/7/30$ 17:25$
 * @Version:        1.0
 */
public class TrieNode {
    public TrieNode(char data) {
        this.data = data;
    }

    char data;
    TrieNode[] children = new TrieNode[26];
    public boolean isEndingChar = false;

}
