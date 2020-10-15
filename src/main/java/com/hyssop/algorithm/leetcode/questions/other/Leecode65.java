package com.hyssop.algorithm.leetcode.questions.other;

/**
 * @Author jie.zhang
 * @create_time 2020/7/16 9:49
 * @updater
 * @update_time
 * 剑指 Offer 65. 不用加减乘除做加法
 * 写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。
 *
 *  
 *
 * 示例:
 *
 * 输入: a = 1, b = 1
 * 输出: 2
 *  
 *
 * 提示：
 *
 * a, b 均可能是负数或 0
 * 结果不会溢出 32 位整数
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/bu-yong-jia-jian-cheng-chu-zuo-jia-fa-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * https://leetcode-cn.com/problems/bu-yong-jia-jian-cheng-chu-zuo-jia-fa-lcof/solution/mian-shi-ti-65-bu-yong-jia-jian-cheng-chu-zuo-ji-7/
 **/
public class Leecode65 {

    public int add(int a, int b) {
        while(b != 0) { // 当进位为 0 时跳出
            int c = (a & b) << 1;  // c = 进位
            a ^= b; // a = 非进位和
            b = c; // b = 进位
        }
        return a;
    }

    public static void main(String[] args) {
        int a = 7;
        int b=12;
        Leecode65 leecode65 = new Leecode65();
        System.out.println(leecode65.add(a,b));

    }
}