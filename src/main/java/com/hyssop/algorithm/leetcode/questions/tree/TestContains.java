package com.hyssop.algorithm.leetcode.questions.tree;

import org.apache.commons.collections4.trie.PatriciaTrie;
import org.junit.Test;

/**
 * @Description:    java类作用描述
 * @Author:         zhjie.zhang
 * @CreateDate:     2019/7/30$ 18:05$
 * @UpdateUser:     zhjie.zhang
 * @UpdateDate:     2019/7/30$ 18:05$
 * @Version:        1.0
 */
public class TestContains {

    @Test
    public void testTrie(){
        PatriciaTrie<Double> t = new PatriciaTrie<>();

        t.put("ronak", 100.0);
        t.put("ronald", 90.0);
        t.put("rat", 50.0);
        t.put("robert", 200.0);
        t.put("bat", 44.0);
        t.put("batman", 440.0);


        System.out.println(t.containsKey("ronak"));
        System.out.println(t.selectKey("ro"));
        System.out.println(t.prefixMap("r"));
        System.out.println(t.prefixMap("ro"));
        System.out.println(t.prefixMap("ron"));
    }

}
