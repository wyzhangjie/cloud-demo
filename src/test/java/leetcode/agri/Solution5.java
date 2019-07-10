package leetcode.agri;


/**
 * @Description:    通过中心扩展发找到最长的回文子串(低效)
 * @Author:         zhjie.zhang
 * @CreateDate:     2019/7/9$ 10:03$
 * @UpdateUser:     zhjie.zhang
 * @UpdateDate:     2019/7/9$ 10:03$
 * @Version:        1.0
 */
public class Solution5 {

    public static void getLongestSubstr(String[] test) {
        for (String str : test) {
            String longest = "";
            String[] ss = str.split("");
            ss = preProcess(ss);
            int len = ss.length;
            if (len == 2 && ss[0].equalsIgnoreCase("#") && ss[1].equalsIgnoreCase("#")) {
                System.out.println("字符串：" + str + " 沒有回文子串");
            } else {

                for (int i = 1; i < len - 1; i++) {
                    String longestSubstr = getLongest(i, ss);
                    if (longestSubstr.length() >= longest.length()) {
                        longest = longestSubstr;
                    }

                }
                longest = longest.replace("#", "");
                if (longest.length() == 1) {
                    System.out.println("字符串：" + str + " 沒有回文子串");
                } else {
                    System.out.println("字符串： " + str.replace("#", "") + "最长回文子串：" + longest);
                }


            }
        }
    }

    public static String longestPalindrome(String s) {
        String longest = "";
        String[] ss = s.split("");
        ss = preProcess(ss);
        int len = ss.length;
        if (len == 2 && ss[0].equalsIgnoreCase("#") && ss[1].equalsIgnoreCase("#")) {
            System.out.println("字符串：" + s + " 沒有回文子串");
        } else {

            for (int i = 1; i < len - 1; i++) {
                String longestSubstr = getLongest(i, ss);
                if (longestSubstr.length() >= longest.length()) {
                    longest = longestSubstr;
                }

            }
            longest = longest.replace("#", "");
            if (longest.length() == 1 && longest.equalsIgnoreCase("")) {
                return "";
            } else {
                return longest;
            }


        }
        return longest;
    }

    private static String[] preProcess(String[] ss) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("#");
        for (String s : ss) {
            stringBuffer.append(s);
            stringBuffer.append("#");
        }
        return stringBuffer.toString().split("");
    }

    private static String getLongest(int mid, String[] ss) {
        StringBuffer sb = new StringBuffer();
        int l = mid - 1;
        int r = mid + 1;
        int len = ss.length;
        int scope = 0;
        while (l >= 0 && r < len) {
            if (ss[l].equalsIgnoreCase(ss[r])) {
                l--;
                r++;
                scope++;
            } else {
                break;
            }

        }
        for (int i = mid - scope; i <= mid + scope; i++) {
            sb.append(ss[i]);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String[] test = new String[]{"aba", "abc", "cabadabae", "a"};
        getLongestSubstr(test);
        System.out.println("ttt:" + longestPalindrome("a"));
    }
}
