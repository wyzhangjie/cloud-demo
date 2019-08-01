package leetcode.stack;

import java.util.AbstractQueue;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;

/**
 * @Description:    java类作用描述
 * @Author:         zhjie.zhang
 * @CreateDate:     2019/7/11$ 10:54$
 * @UpdateUser:     zhjie.zhang
 * @UpdateDate:     2019/7/11$ 10:54$
 * @Version:        1.0
 */
public class LeetcodeStack {
    public static void main(String[] args) {
        // testToPass1();
   //     testToPass2();
        testToPass3();
    }



    private static void testToPass1() {
        String s1 = "()";
        String s2 = "()[]{}";
        String s3 = "(]";
        String s4 = "([)]";
        String s5 = "{[]}";
        String s6 = ")";
        System.out.println(isParentheses(s1));
        System.out.println(isParentheses(s2));
        System.out.println(isParentheses(s3));
        System.out.println(isParentheses(s4));
        System.out.println(isParentheses(s5));
    }

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     *
     * 有效字符串需满足：
     *
     * 左括号必须用相同类型的右括号闭合。 左括号必须以正确的顺序闭合。 注意空字符串可被认为是有效字符串。
     *
     * 示例 1:
     *
     * 输入: "()" 输出: true 示例 2:
     *
     * 输入: "()[]{}" 输出: true 示例 3:
     *
     * 输入: "(]" 输出: false 示例 4:
     *
     * 输入: "([)]" 输出: false 示例 5:
     *
     * 输入: "{[]}" 输出: true
     *
     * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/valid-parentheses
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static String left = "{[(";
    public static String right = "]})";
    public static Map<String, String> mashMap = new HashMap();

    static {
        mashMap.put("{", "}");
        mashMap.put("[", "]");
        mashMap.put("(", ")");
    }

    public static boolean isParentheses(String s) {
        if (s.length() == 0) {
            return true;
        }
        Stack<String> stack = new Stack<>();
        String[] ss = s.split("");
        for (String i : ss) {
            if (left.contains(i)) {
                stack.push(i);
            } else {
                if (stack.empty()) {
                    return false;
                }
                if (isMatch(i, stack.pop())) {
                    continue;
                } else if (right.contains(i)) {
                    return false;
                } else {
                    return false;
                }
            }

        }
        if (stack.empty()) {
            return true;
        }
        return false;
    }

    public static boolean isMatch(String right, String left) {
        if (right.equalsIgnoreCase(mashMap.get(left))) {
            return true;
        }
        return false;
    }

    /**
     * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
     *
     * push(x) -- 将元素 x 推入栈中。 pop() -- 删除栈顶的元素。 top() -- 获取栈顶元素。 getMin() -- 检索栈中的最小元素。 示例:
     *
     * MinStack minStack = new MinStack(); minStack.push(-2); minStack.push(0); minStack.push(-3);
     * minStack.getMin();   --> 返回 -3. minStack.pop(); minStack.top();      --> 返回 0.
     * minStack.getMin();   --> 返回 -2.
     *
     * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/min-stack 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    private static void testToPass2() {

        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-1);
        System.out.println(minStack.getMin()); //--> 返回 -3.
        System.out.println(minStack.top());
        minStack.pop();
     // --> 返回 0.
        System.out.println(minStack.getMin());  // --> 返回 -2.
    }
    static class MinStack extends java.util.Stack<java.lang.Integer> {
        private java.util.Stack<Integer> book;
        private java.util.Stack<Integer> result;

        public MinStack() {
            book = new java.util.Stack<>();
            result = new  java.util.Stack<>();
        }

        public Integer push(Integer item) {
            if (result.empty()){
                book.push(item);
            }else {
                Integer top = result.peek();

                if (top==null ||book.peek().compareTo(item) <= 0) {
                    book.push(book.peek());
                } else {
                    book.push(item);
                }
            }

            result.push(item);
            return item;

        }

        public synchronized java.lang.Integer pop() {
            book.pop();

            return result.pop();
        }

        public Integer getMin() {
            return book.peek();
        }

        public Integer top() {
            return result.peek();
        }
    }


    /**
     * 使用栈实现队列的下列操作：
     *
     * push(x) -- 将一个元素放入队列的尾部。 pop() -- 从队列首部移除元素。 peek() -- 返回队列首部的元素。 empty() -- 返回队列是否为空。 示例:
     *
     * MyQueue queue = new MyQueue();
     *
     * queue.push(1); queue.push(2); queue.peek();  // 返回 1 queue.pop();   // 返回 1 queue.empty(); //
     * 返回 false 说明:
     *
     * 你只能使用标准的栈操作 -- 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
     * 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。 假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者
     * peek 操作）。
     *
     * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/implement-queue-using-stacks
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public class MyQueue extends AbstractQueue {


        @Override
        public Iterator iterator() {
            return null;
        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean offer(Object o) {
            return false;
        }

        @Override
        public Object poll() {
            return null;
        }

        @Override
        public Object peek() {
            return null;
        }
    }

    /**
     * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
     *
     * 字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格  。
     *
     * 示例 1:
     *
     * 输入: "1 + 1"
     * 输出: 2
     * 示例 2:
     *
     * 输入: " 2-1 + 2 "
     * 输出: 3
     * 示例 3:
     *
     * 输入: "(1+(4+5+2)-3)+(6+8)"
     * 输出: 23
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/basic-calculator
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    *
    * */

    private static void testToPass3() {
     String text ="(2+6* 3+5- (3*14/7+2)*5)+3";
        System.out.println(calculate(text));
    }

    public int calculate1(String s) {

        return 1;

    }
    private static Stack<Long> numbers = new Stack<>();
    private static Stack<Character> ops = new Stack<>();


    public static int calculate(String s) {
        String num = "";

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == ' ') continue;
            if(Character.isDigit(c)) {
                num = num + c;
                // If no next char, or next char is not digit, then this is the number
                if(i == s.length() - 1 || !Character.isDigit(s.charAt(i + 1))) {
                    numbers.push(Long.parseLong(num));
                    num = "";
                }
            }
            else if(c == '(') {
                ops.push(c);
            }
            else if(c == ')') {
                // Keep calculating until we see (
                while(ops.peek() != '(') {
                    char op = ops.peek();
                    long second = numbers.pop();
                    long first = numbers.pop();
                    operation(ops.pop(), first, second);
                }
                ops.pop();
            }
            else {
                if(!ops.empty() && !isHigherPriority(c, ops.peek()) && ops.peek() != '(') {
                    long second = numbers.pop();
                    long first = numbers.pop();
                    operation(ops.pop(), first, second);
                }

                ops.push(c);
            }
        }

        while(numbers.size() > 1) {
            long second = numbers.pop();
            long first = numbers.pop();
            operation(ops.pop(), first, second);
        }
        return numbers.pop().intValue();
    }

    public static void operation(char op, long first, long second) {
        if(op == '+') numbers.push(first + second);
        if(op == '-') numbers.push(first - second);
        if(op == '*') numbers.push(first * second);
        if(op == '/') numbers.push(first / second);
    }

    public static boolean isHigherPriority(char op1, char op2) {
        //if(op2 == '(' || op2 == ')') return false;
        if((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) return true;
        return false;
    }
}
