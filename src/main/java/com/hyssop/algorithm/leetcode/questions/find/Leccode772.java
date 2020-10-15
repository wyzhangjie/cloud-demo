package com.hyssop.algorithm.leetcode.questions.find;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author jie.zhang
 * @create_time 2020/7/14 17:13
 * @updater
 * @update_time LeetCode 第 772 题，基本计算器：实现一个基本的计算器来计算简单的表达式字符串。
 * <p>
 * <p>
 * <p>
 * 说明：
 * <p>
 * 表达式字符串可以包含左括号 ( 和右括号 )，加号 + 和减号 -，非负整数和空格。
 * <p>
 * 表达式字符串只包含非负整数， +   -   *   / 操作符，左括号 ( ，右括号 ) 和空格。整数除法需要向下截断。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * "1 + 1" = 2
 * <p>
 * " 6-4 / 2 " = 4
 * <p>
 * "2×(5+5×2)/3+(6/2+8)" = 21
 * <p>
 * "(2+6×3+5- (3×14/7+2)×5)+3" = -12
 **/
public class Leccode772 {
    //简单加法器
    //  转换，将字符串的字符放入到一个优先队列中
    int calculate(String s) {
        Queue<Character> queue = new LinkedList<>();
        for (char c : s.toCharArray()) {
            queue.offer(c);
        }

        //  定义两个变量，num  用来表示当前的数字，sum  用来记录最后的和  
        int num = 0, sum = 0;

        //  遍历优先队列，从队列中一个一个取出字符  
        while (!queue.isEmpty()) {
            char c = queue.poll();

            //  如果当前字符是数字，那么就更新  num  变量，如果遇到了加号，就把当前的  num  加入到  sum  里，num  清零
            if (Character.isDigit(c)) {
                num = 10 * num + c - '0';
            } else {
                sum += num;
                num = 0;
            }
        }

        sum += num;  //  最后没有加号，再加一次

        return sum;

    }

    //加减混合运算，可以转换成简单的加法器继续运算
    int calculateForAdd2(String s) {
        Queue<Character> queue = new LinkedList<>();
        for (char c : s.toCharArray()) {
            //将空格弹出，不参与运算
            if (c != ' ') {
                queue.offer(c);
            }
        }
        queue.add('+');

        char sign = '+';  //  添加一个符号标志变量

        int num = 0, sum = 0;

        while (!queue.isEmpty()) {
            char c = queue.poll();

            if (Character.isDigit(c)) {
                num = 10 * num + c - '0';
            } else {
                //  遇到了符号，表明我们要开始统计一下当前的结果了
                if (sign == '+') {  //数字的符号是  +
                    sum += num;
                } else if (sign == '-') {  //  数字的符号是  -
                    sum -= num;
                }

                num = 0;
                sign = c;
            }
        }

        return sum;
    }

    int calculateFor3(String s) {
        Queue<Character> queue = new LinkedList<>();
        for (char c : s.toCharArray()) {
            if (c != ' ') queue.offer(c);
        }
        queue.add('+');

        char sign = '+';
        int num = 0;

        //  定义一个新的变量  stack，用来记录要被处理的数
        Stack<Integer> stack = new Stack<>();

        while (!queue.isEmpty()) {
            char c = queue.poll();

            if (Character.isDigit(c)) {
                num = 10 * num + c - '0';
            } else {
                if (sign == '+') {
                    stack.push(num);  //  遇到加号，把当前的数压入到堆栈中
                } else if (sign == '-') {
                    stack.push(-num);  //  减号，把当前数的相反数压入到堆栈中
                } else if (sign == '*') {
                    stack.push(stack.pop() * num);  //  乘号，把前一个数从堆栈中取出，然后和当前的数相乘，再放回堆栈
                } else if (sign == '/') {
                    stack.push(stack.pop() / num);  //  除号，把前一个数从堆栈中取出，然后除以当前的数，再把结果放回堆栈
                }

                num = 0;
                sign = c;
            }
        }


        int sum = 0;

        //  堆栈里存储的都是各个需要相加起来的结果，把它们加起来，返回总和即可
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }
        return sum;

    }

    //  在主函数里调用一个递归函数
    int calculateFor4(String s) {
        Queue<Character> queue = new LinkedList<>();
        for (char c : s.toCharArray()) {
            if (c != ' ') {
                queue.offer(c);
            }
        }
        queue.offer('+');

        return calculateFor4(queue);
    }

    int calculateFor4(Queue<Character> queue) {
        char sign = '+';
        int num = 0;

        Stack<Integer> stack = new Stack<>();

        while (!queue.isEmpty()) {
            char c = queue.poll();

            if (Character.isDigit(c)) {
                num = 10 * num + c - '0';
            }
            //  遇到一个左括号，开始递归调用，求得括号里的计算结果，将它赋给当前的  num
            else if (c == '(') {
                num = calculateFor4(queue);
            } else {
                if (sign == '+') {
                    stack.push(num);
                } else if (sign == '-') {
                    stack.push(-num);
                } else if (sign == '*') {
                    stack.push(stack.pop() * num);
                } else if (sign == '/') {
                    stack.push(stack.pop() / num);
                }

                num = 0;
                sign = c;

                //  遇到右括号，就可以结束循环，直接返回当前的总和
                if (c == ')') {
                    break;
                }
            }
        }

        int sum = 0;
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }
        return sum;

    }

    public static void main(String[] args) {
        String s = "1+2-10";
        Leccode772 leccode772 = new Leccode772();
        System.out.println(leccode772.calculateForAdd2(s));
    }
}