package com.hyssop.algorithm.leetcode.questions.find;

import java.util.*;

/**
 * @Author jie.zhang
 * @create_time 2020/7/14 16:43
 * @updater
 * @update_time LeetCode   第 269 题，火星字典：现有一种使用字母的全新语言，
 * 这门语言的字母顺序与英语顺序不同。假设，您并不知道其中字母之间的先后顺序。
 * 但是，会收到词典中获得一个不为空的单词列表。因为是从词典中获得的，
 * 所以该单词列表内的单词已经按这门新语言的字母顺序进行了排序。您需要根据这个输入的列表，还原出此语言中已知的字母顺序。
 * <p>
 * 示例 1
 * <p>
 * 输入:
 * <p>
 * [  "wrt",  "wrf","er","ett",  "rftt"]
 * <p>
 * <p>
 * <p>
 * 输出: "wertf"
 * <p>
 * <p>
 * <p>
 * 示例 2
 * <p>
 * 输入:
 * <p>
 * [  "z",  "x"]
 * <p>
 * <p>
 * <p>
 * 输出: "zx"
 * <p>
 * <p>
 * <p>
 * 示例 3
 * <p>
 * 输入:
 * <p>
 * [  "z",    "x","z"]
 * <p>
 * <p>
 * <p>
 * 输出: ""
 * <p>
 * 包括两大步骤，第一步是根据输入构建一个有向图；第二步是对这个有向图进行拓扑排序。
 * <p>
 * 解释: 此顺序是非法的，因此返回 ""。
 **/


public class Leecode269 {
    //  基本情况处理，比如输入为空，或者输入的字符串只有一个
    String alienOrder(String[] words) {
        if (words == null || words.length == 0)
            return null;
        if (words.length == 1) {
            return words[0];
        }

        //  构建有向图：定义一个邻接链表  adjList，也可以用邻接矩阵
        Map<Character, List<Character>> adjList = new HashMap<>();

        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i], w2 = words[i + 1];
            int n1 = w1.length(), n2 = w2.length();

            boolean found = false;

            for (int j = 0; j < Math.max(w1.length(), w2.length()); j++) {
                Character c1 = j < n1 ? w1.charAt(j) : null;
                Character c2 = j < n2 ? w2.charAt(j) : null;

                if (c1 != null && !adjList.containsKey(c1)) {
                    adjList.put(c1, new ArrayList<Character>());
                }

                if (c2 != null && !adjList.containsKey(c2)) {
                    adjList.put(c2, new ArrayList<Character>());
                }

                if (c1 != null && c2 != null && !c1.equals(c2) && !found) {
                    adjList.get(c1).add(c2);
                    found = true;
                }
            }
        }

        Set<Character> visited = new HashSet<>();
        Set<Character> loop = new HashSet<>();
        Stack<Character> stack = new Stack<Character>();

        for (Character key : adjList.keySet()) {
            if (!visited.contains(key)) {
                if (!topologicalSort(adjList, key, visited, loop, stack)) {
                    return "";
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.toString();

    }

    //  将当前节点  u  加入到  visited  集合以及  loop  集合中。
    boolean topologicalSort(Map<Character, List<Character>> adjList, char u,
                            Set<Character> visited, Set<Character> loop, Stack<Character> stack) {
        visited.add(u);
        loop.add(u);
            //循环所有的跟u有关联的字符
            for (int i = 0; i < adjList.get(u).size(); i++) {
                char v = adjList.get(u).get(i);

                if (loop.contains(v))
                    return false;

                if (!visited.contains(v)) {
                    if (!topologicalSort(adjList, v, visited, loop, stack)) {
                        return false;
                    }
                }
            }


        loop.remove(u);

        stack.push(u);

        return true;
    }
}