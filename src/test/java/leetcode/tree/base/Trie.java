package leetcode.tree.base;

/**
 * @Description:    Trie简单Trie树构建和查询
 * @Author:         zhjie.zhang
 * @CreateDate:     2019/7/30$ 17:31$
 * @UpdateUser:     zhjie.zhang
 * @UpdateDate:     2019/7/30$ 17:31$
 * @Version:        1.0
 */
public class Trie {
    private TrieNode root = new TrieNode('/');

    public void insert(char[] text) {
        TrieNode p = root;
        for (int i = 0; i < text.length; i++) {
            int index = text[i] - 'a';
            if (p.children[index] == null) {
                TrieNode tmp = new TrieNode(text[i]);
                p.children[index] = tmp;
            }
            p = p.children[index];
        }
        p.isEndingChar = true;

    }

    public boolean find(char[] pattern) {
        TrieNode p = root;
        for (int i = 0; i < pattern.length; i++) {
            if (p.children[(pattern[i] - 'a')] == null) {
                return false;
            }
            p = p.children[pattern[i] - 'a'];
        }
        if (p.isEndingChar == false) return false;
        else return true;
    }

    public static void main(String[] args) {
        char[] strs = {'a', 'b', 'c'};
        char[] strs1 = {'b', 'c', 'd'};
        char[] a = {'a', 'b'};
        Trie trie = new Trie();
        trie.insert(strs);
        trie.insert(strs1);
        System.out.println(trie.find(a));
    }
}
