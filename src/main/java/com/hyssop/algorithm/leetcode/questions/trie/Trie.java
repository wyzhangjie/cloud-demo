package com.hyssop.algorithm.leetcode.questions.trie;

/**
 * @Author jie.zhang
 * @create_time 2020/7/17 17:33
 * @updater
 * @update_time
 **/
public class Trie {
    private Node root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new Node();
        root.val = ' ';
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Node cur = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.children[c - 'a'] == null){
                cur.children[c - 'a'] = new Node(c);
            }
            cur = cur.children[c - 'a'];
        }
        cur.isWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            if (cur.children[word.charAt(i) - 'a'] == null) {
                return false;
            }
            cur = cur.children[word.charAt(i) - 'a'];
        }
        return cur.isWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            if (cur.children[prefix.charAt(i) - 'a'] == null) {
                return false;
            }
            cur = cur.children[prefix.charAt(i) - 'a'];
        }
        return true;
    }
}