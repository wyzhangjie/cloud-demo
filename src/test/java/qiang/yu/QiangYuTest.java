package qiang.yu;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author jie.zhang
 * @create_time 2020/5/26 11:50
 * @updater
 * @update_time
 **/
public class QiangYuTest {
    public static List<List<Integer>> expressOrToAnd(String expression) {
        String[] expr1 = StringUtils.split(expression, "(");
        Map<Integer, List<List<Integer>>> map = new HashMap<>();
        for (int i = 0; i < expr1.length; i++) {
            String s1 = expr1[i];
            String[] expr2 = StringUtils.split(s1, ")");
            for (int j = 0; j < expr2.length; j++) {
                String s2 = expr2[j];
                if ("&&".equals(s2)) {
                    continue;
                }
                String[] expr3 = StringUtils.split(s2, "||");
                for (String s3 : expr3) {
                    String[] expr4 = StringUtils.split(s3, "&&");
                    List<Integer> integers = new ArrayList<>();
                    for (String s4 : expr4) {
                        integers.add(Integer.valueOf(s4));
                    }
                    map.computeIfAbsent(i + j, k -> new ArrayList<>()).add(integers);
                }
            }
        }

        List<List<Integer>> tmp = combiner(map.get(0), map.get(1));
        int size = map.size();
        for (int i = 2; i < size; i++) {
            tmp = combiner(tmp, map.get(i));
        }
        return tmp;
    }

    private static List<List<Integer>> combiner(List<List<Integer>> lists1, List<List<Integer>> lists2) {
        if (CollectionUtils.isEmpty(lists2)) {
            return lists1;
        }
        List<List<Integer>> lists = new ArrayList<>();
        for (List<Integer> list1 : lists1) {
            List<Integer> list;
            for (List<Integer> list2 : lists2) {
                list = new ArrayList<>();
                list.addAll(list1);
                list.addAll(list2);
                lists.add(list);
            }
        }
        return lists;
    }

    public static void main(String[] args) {
        String a ="(1&&2||1&&4||2&&4)&&(5||6)&&(7||8)&&9";
        String b="(1&&2||1&&4||2&&4)&&(5||6)&&(7||8||9)&&10";
        String c="(1&&2||1&&4||2&&4)&&(5||6)&&3";
        System.out.println(JSON.toJSONString(expressOrToAnd(a)));

    }
}