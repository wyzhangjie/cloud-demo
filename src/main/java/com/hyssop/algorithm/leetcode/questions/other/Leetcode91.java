package com.hyssop.algorithm.leetcode.questions.other;

/**
 * @Author jie.zhang
 * @create_time 2020/7/13 14:15
 * @updater
 * @update_time 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "12"
 * 输出: 2
 * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2:
 * <p>
 * 输入: "226"
 * 输出: 3
 * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/decode-ways
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class Leetcode91 {
    public int numDecodings(String s) {
        if (s.charAt(0) == '0') return 0;
        char[] chars = s.toCharArray();
        return decodeChars(chars, chars.length - 1);
    }

    private int decodeChars(char[] chars, int lastIndex) {
        //中间结果
        int count = 0;
        if (lastIndex <= 0) {
            count = 1;
            return count;
        }
        char preChar = chars[lastIndex - 1];
        char curChar = chars[lastIndex];
        if (curChar > '0') {
            count = decodeChars(chars, lastIndex - 1);
        }
        if (preChar == '1' || (preChar == '2' && curChar <= '6')) {
            count += decodeChars(chars, lastIndex - 2);
        }
        return count;
    }


    public static void main(String[] args) {
        Leetcode91 leetcode91 = new Leetcode91();
        System.out.println(leetcode91.numDecodings("221"));
        System.out.println(leetcode91.numDecodings("218"));
    }
}