package com.hyssop.algorithm.leetcode.questions.trie;

/**
 * @Author jie.zhang
 * @create_time 2020/7/17 17:41
 * @updater
 * @update_time 实现一个带有buildDict, 以及 search方法的魔法字典。
 * <p>
 * 对于buildDict方法，你将被给定一串不重复的单词来构建一个字典。
 * <p>
 * 对于search方法，你将被给定一个单词，并且判定能否只将这个单词中一个字母换成另一个字母，使得所形成的新单词存在于你构建的字典中。
 * <p>
 * 示例 1:
 * <p>
 * Input: buildDict(["hello", "leetcode"]), Output: Null
 * Input: search("hello"), Output: False
 * Input: search("hhllo"), Output: True
 * Input: search("hell"), Output: False
 * Input: search("leetcoded"), Output: False
 * 注意:
 * <p>
 * 你可以假设所有输入都是小写字母 a-z。
 * 为了便于竞赛，测试所用的数据量很小。你可以在竞赛结束后，考虑更高效的算法。
 * 请记住重置MagicDictionary类中声明的类变量，因为静态/类变量会在多个测试用例中保留。 请参阅这里了解更多详情。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-magic-dictionary
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class Leecode676 {
    private Node root;

    /**
     * Initialize your data structure here.
     */
    public Leecode676() {
        root = new Node();
        root.val = ' ';
    }

    /**
     * Build a dictionary through a list of words
     */
    public void buildDict(String[] dict) {
        for (int i = 0; i < dict.length; i++) {
            insert(dict[i]);
        }
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        Node cur = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.children[c - 'a'] == null) {
                cur.children[c - 'a'] = new Node(c);
            }
            cur = cur.children[c - 'a'];
        }
        cur.isWord = true;
    }


    /**
     * Returns if there is any word in the trie that equals to the given word after modifying exactly one character
     */
    public boolean search(String word) {
        boolean isModified = false;
        Node cur = root;
        int lastIndex=0;
        for (int i = 0; i < word.length(); i++) {
            lastIndex =i;
            if (cur.children[word.charAt(i) - 'a'] == null) {
                if (!isModified) {
                    isModified = true;
                    continue;
                } else {
                    if (isModified) {
                        for (int j = 0; j < cur.children.length; j++) {
                            if(cur.children[j]==null){
                                continue;
                            }
                            cur = cur.children[j];
                                cur = cur.children[word.charAt(i) - 'a'];
                               if(cur==null){
                                   return false;
                               }else {
                                   break;
                               }
                        }
                    } else {
                        isModified = true;
                        continue;
                    }
                }


            } else {
                cur = cur.children[word.charAt(i) - 'a'];
                if(allNull(cur.children)){
                    break;
                }
            }
        }
        return cur.isWord && isModified|| isModified ;
    }

    private boolean allNull(Node[] children) {
        for(int j=0;j<children.length;j++){
            if(children[j]!=null){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        /**
         * ["MagicDictionary", "buildDict", "search", "search", "search", "search"]
         * [[], [["hello","leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
         *
         * [null,null,false,true,false,false]
         * */
        /**
         * ["MagicDictionary", "buildDict", "search", "search", "search", "search"]
         * [[], [["hello","hallo","leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
         * [null,null,true,true,false,false]
         * */
        Leecode676 leecode676 = new Leecode676();
        leecode676.buildDict(new String[]{"hello", "leetcode"});
        System.out.println(leecode676.search("hello"));
       System.out.println(leecode676.search("hhllo"));
       System.out.println(leecode676.search("hell"));
        System.out.println(leecode676.search("leetcoded"));
    }
}