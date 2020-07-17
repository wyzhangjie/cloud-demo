package com.hyssop.algorithm.leetcode.questions.trie;

/**
 * @Author jie.zhang
 * @create_time 2020/7/17 17:32
 * @updater
 * @update_time
 **/
public class Node {
    public char val;
    public boolean isWord = false;
    public Node[] children = new Node[26];
    public Node() {
    }
    public Node(char c) {
        Node node = new Node();
        node.val = c;
    }
}