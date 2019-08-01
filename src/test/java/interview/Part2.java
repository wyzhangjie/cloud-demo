package interview;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:    现场实现一个求两个长串的公共子串
 * @Author:         zhjie.zhang
 * @CreateDate:     2019/7/25$ 9:46$
 * @UpdateUser:     zhjie.zhang
 * @UpdateDate:     2019/7/25$ 9:46$
 * @Version:        1.0
 */
public class Part2 {

    public static void findCommonStrs(String sub1, String sub2) {
        int maxLen = 0;
        List<Integer> list = new ArrayList<>(5);
        int lenLarge = sub1.length();
        int lenSmall = sub2.length();
        if (lenLarge < lenSmall) {
            lenLarge = lenSmall;
            String temp = sub1;
            sub1 = sub2;
            sub2 = temp;
        }
        for (int i = 0; i < lenLarge; i++) {
            for (int j = 0; j < lenSmall; j++) {
                int subMax = 0;
                int step = j;
                int stepOrigin = i;
                while (stepOrigin < lenLarge && step < lenSmall) {
                    if (sub1.substring(stepOrigin, stepOrigin + 1).equals(sub2.substring(step, step + 1))) {
                        step++;
                        stepOrigin++;
                        subMax++;
                    } else {
                        break;
                    }
                }
                if (list.size() > 0) {
                    if (subMax == maxLen) {
                        list.add(i);
                    }
                }
                if (subMax > maxLen) {
                    maxLen = subMax;
                    list.add(i);
                }
            }
        }
        for (Integer s : list) {
            System.out.println(sub1.substring(s, s + maxLen));
        }
    }

    public static void main(String[] args) {
        String sub1 = "dabc";
        String sub2 = "cabc";
        findCommonStrs(sub1, sub2);
    }
}
