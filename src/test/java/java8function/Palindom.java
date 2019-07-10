package java8function;

import java.util.HashMap;
import java.util.Map;

/**
 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

 示例 1：

 输入: "babad"
 输出: "bab"
 注意: "aba" 也是一个有效答案。
 示例 2：

 输入: "cbbd"
 输出: "bb"

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Palindom {
    public String longestPalindrome(String s) {
    return "a";
    }

    public static void main(String[] args) {
        String s = "cabadabae";
        String[] ss = s.split("");
        ss = preFix(ss);
        recordss(ss);

    }

    private static void recordss(String[] ss) {
       int[] record = new int[ss.length];
        int len = ss.length/2;
        for(int i=0;i<len;i++){
            record[i] = Rightest(i,ss);
        }

    }

    private static Integer Rightest(int i, String[] ss) {
        int l=i-1;
        int r = i+1;
        int rightest = 0;
        if(l<0||r>ss.length){
            return 0;
        }else {
            while(l>=0&&r<ss.length){
                if(ss[l].equalsIgnoreCase(ss[r])){
                    rightest++;
                    l--;
                    r++;
                }else {

                    break;
                }
            }
        }
        return rightest;
    }


    private static String[] preFix(String[] ss) {
        StringBuffer sb = new StringBuffer();
        int len = ss.length;
        for(int i=0;i<len;i++){
            sb.append(ss[i]);
            sb.append("#");

        }
        sb =sb.deleteCharAt(sb.length()-1);
        return sb.toString().split("");
    }
}
