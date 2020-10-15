package com.hyssop.algorithm.leetcode.questions.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @Author jie.zhang
 * @create_time 2020/7/13 16:19
 * @updater
 * @update_time n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 上图为 8 皇后问题的一种解法。
 * <p>
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * <p>
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * <p>
 * 示例:
 * <p>
 * 输入: 4
 * 输出: [
 * [".Q..",  // 解法 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * <p>
 * ["..Q.",  // 解法 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-queens
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 待处理 （打印不正确）
 **/
public class Leecode51 {
    static List<String[][]> endResult = new ArrayList<>();

    public static void main(String[] args) {
        int n = 4;
        String[][] midResult = new String[n][n];
        quenN(n, 0, midResult);
        printf(endResult);

    }

    private static void quenN(int size, int beginIndex, String[][] midResult) {
        if (beginIndex==size) {
            String[][] rewMidResult = midResult.clone();
            endResult.add(rewMidResult);

        }

            for (int j = 0; j < size; j++) {
                if (canQune(midResult, beginIndex, j)) {
                    midResult[beginIndex][j] = "Q";
                    quenN(size, beginIndex + 1, midResult);
                    midResult[beginIndex][j] = ".";
                }

            }
    }

    private static boolean hasQune(String[][] midResult, int size) {
        for (int i = 0; i < size; i++) {
            if ("Q".equalsIgnoreCase(midResult[size - 1][i])) {
                return true;
            }
        }
        return false;
    }

    private static boolean canQune(String[][] midResult, int hang, int lie) {
        for (int i = 0; i < hang; i++) {
            for (int j = 0; j < 4; j++) {
                if ("Q".equalsIgnoreCase(midResult[i][lie]) || ("Q".equalsIgnoreCase(midResult[i][j]) && Math.abs(j - i) == Math.abs(lie - hang))) {
                    return false;
                }

            }
        }
        for (int j = 0; j < lie; j++) {
            if ("Q".equalsIgnoreCase(midResult[hang][j])) {
                return false;
            }
        }
        return true;
    }


    private static void printf(List<String[][]> midResult) {

        Iterator<String[][]> iterators = midResult.iterator();
        while (iterators.hasNext()) {
            System.out.println(Arrays.deepToString(iterators.next()));
        }

    }

}