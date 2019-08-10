package leetcode.list;

/**
 * @Description:    链表相关  https://www.cnblogs.com/xiaomiganfan/p/5390252.html
 * @Author:         zhjie.zhang
 * @CreateDate:     2019/7/10$ 11:12$
 * @UpdateUser:     zhjie.zhang
 * @UpdateDate:     2019/7/10$ 11:12$
 * @Version:        1.0
 */
public class ListTest {
    /**
     * 单链表反转
     *
     * 链表中环的检测
     *
     * 两个有序的链表合并
     *
     * 删除链表倒数第 n 个结点
     *
     * 求链表的中间结点
     */

    public static class List<T extends Comparable> {
        T data;
        List<T> next;

        public List(T t) {
            this.data = t;
        }
    }

    public static <T extends Comparable<? super T>> int getListLen(List<T> list) {
        int len = 0;
        while (list != null) {
            len++;
            list = list.next;
        }
        return len;
    }

    public static <T extends Comparable<? super T>> void printList(List<T> list) {
        while (list != null) {
            System.out.println(list.data);
            list = list.next;
        }
        System.out.println("breautiful line=======================================");

    }

    /**
     * 单链表反转
     */
    public static <T extends Comparable<? super T>> List<T> revertList(List<T> list) {
        List<T> head = list;
        if (head == null || head.next == null) {
            return head;
        } else {
            List<T> next = head.next;
            head.next = null;
            while (next != null) {
                List<T> tail = next.next;
                next.next = head;
                head = next;
                next = tail;

            }
        }
        return head;
    }


    /**
     * 链表中环的检测
     */
    public static <T extends Comparable<? super T>> boolean hasRound(List<T> list) {

        List<T> first = list;
        List<T> next = first.next;
        if (first == null || next == null) {
            return false;
        }
        while (first != null && next.next != null) {
            if (first == next) {
                return true;
            }
            first = first.next;
            next = next.next.next;
        }
        return false;
    }


    /**
     * 两个有序的链表合并
     */
    public static <T extends Comparable<? super T>> List<T> twoListMerge(List<T> first, List<T> second) {
        if (first == null && second == null) {
            return null;
        }
        if (first == null) {
            return second;
        }
        if (second == null) {
            return first;
        }
        List<T> firstHead = first;
        List<T> secondHead = second;
        List<T> walk;
        List<T> head = isSmaller(first, second) ? first : second;
        walk = head;
        if (head == first) {
            firstHead = firstHead.next;
        } else {
            secondHead = secondHead.next;
        }
        while (firstHead != null && secondHead != null) {
            if (isSmaller(firstHead, secondHead)) {
                walk.next = firstHead;
                firstHead = firstHead.next;
            } else {
                walk.next = secondHead;
                secondHead = secondHead.next;
            }
            walk = walk.next;
        }
        if (firstHead != null) {
            walk.next = firstHead;

        }
        if (secondHead != null) {
            walk.next = secondHead;
        }
        return head;
    }

    public static <T extends Comparable<? super T>> boolean isSmaller(List<T> first, List<T> second) {
        boolean result = first.data.compareTo(second.data) <= 0 ? true : false;
        return result;
    }

    /**
     * 删除链表倒数第 n 个结点 先求链表长度，用len-n就是要走的步数了
     */
    public static <T extends Comparable<? super T>> List<T> deleteBackwards(List<T> list, int n) {
        List<T> head = list;
        int len = getListLen(list);
        if (n > len) {
            System.out.println("链表长度低于需要删除的长度");
        } else {
            int step = len - n;
            int i = 1;
            if (step == 0) {
                head = head.next;
            } else {
                List<T> first = list;
                List<T> toDelte = list.next;
                while (i++ < step) {
                    first = first.next;
                    toDelte = toDelte.next;
                }
                first.next = toDelte.next;
            }

        }


        return head;
    }

    /**
     * 求链表的中间结点 偶数求中间两个 基数求中间一个。
     */
    public static <T extends Comparable<? super T>> List<T> getMid(List<T> first) {
        List<T> tail = getTail(first);
        List<T> midBefor = first;
        List<T> midAfter = first;
        List<T> doubleStep = first;
        while ((doubleStep != null && doubleStep.next != null) && doubleStep != tail) {
            midBefor = midAfter;
            midAfter = midAfter.next;
            doubleStep = doubleStep.next.next;
        }
        midAfter.next = null;
        if (doubleStep == tail) {
            return midAfter;
        } else {
            return midBefor;
        }

    }

    private static <T extends Comparable<? super T>> List<T> getTail(List<T> first) {
        List<T> tail = first;
        while (tail.next != null) {
            tail = tail.next;

        }
        return tail;
    }

    public static void main(String[] args) {
        //case1Test();
        //  case2Test();
        //   case3Test();
        // case4Test();
        case5Test();
    }

    private static void case5Test() {
        List<Integer> list1 = new List<>(1);
        List<Integer> list2 = new List<>(2);
        List<Integer> list3 = new List<>(3);
        List<Integer> list4 = new List<>(4);
        List<Integer> list5 = new List<>(5);
      /*  list1.next = list2;
        list2.next = list3;
        list3.next = list4;
        printList(getMid(list1));
        list1.next = list2;
        list2.next = list3;
        list3.next = list4;
        list4.next = list5;
        printList(getMid(list1));*/
        list1.next = list2;
        printList(getMid(list1));
    /*    printList(getMid(null));*/
    }

    private static void case4Test() {
        List<Integer> list1 = new List<>(1);
        List<Integer> list2 = new List<>(2);
        List<Integer> list3 = new List<>(3);
        List<Integer> list4 = new List<>(4);
        list1.next = list2;
        list2.next = list3;
        list3.next = list4;
        List<Integer> result;
        printList(deleteBackwards(list1, 1));
        list1.next = list2;
        list2.next = list3;
        list3.next = list4;
        printList(deleteBackwards(list1, 2));
        list1.next = list2;
        list2.next = list3;
        list3.next = list4;
        printList(deleteBackwards(list1, 3));
        list1.next = list2;
        list2.next = list3;
        list3.next = list4;
        printList(deleteBackwards(list1, 4));
        list1.next = list2;
        list2.next = list3;
        list3.next = list4;
        printList(deleteBackwards(list1, 5));
    }

    private static void case3Test() {
        List<Integer> list1 = new List<>(1);
        List<Integer> list2 = new List<>(3);
        List<Integer> list3 = new List<>(5);
        List<Integer> list4 = new List<>(7);
        list1.next = list2;
        list2.next = list3;
        list3.next = list4;

        List<Integer> list5 = new List<>(2);
        List<Integer> list6 = new List<>(4);
        List<Integer> list7 = new List<>(6);
        List<Integer> list8 = new List<>(8);
        list6.next = list7;
        list5.next = list6;
        list7.next = list8;
        list1 = twoListMerge(list1, list5);
        printList(list1);
        List<Integer> list9 = new List<>(1);
        List<Integer> list10 = new List<>(-1);
        list1 = twoListMerge(list9, list10);
        printList(list1);
    }

    private static void case2Test() {
        List<Integer> list1 = new List<>(5);
        List<Integer> list2 = new List<>(2);
        List<Integer> list3 = new List<>(3);
        List<Integer> list4 = new List<>(4);
        list1.next = list2;
        list2.next = list3;
        list3.next = list4;
        list4.next = list1;
        boolean result = hasRound(list1);
        String text = result == true ? "有" : "没有";
        System.out.println("有环儿吗？   " + text);
        List<Integer> list5 = new List<>(2);
        List<Integer> list6 = new List<>(3);
        list5.next = list6;
        result = hasRound(list5);
        text = result == true ? "有" : "没有";
        System.out.println("有环儿吗？   " + text);
        List<Integer> list7 = new List<>(2);
        result = hasRound(list7);
        text = result == true ? "有" : "没有";
        System.out.println("有环儿吗？   " + text);
    }


    private static void case1Test() {
        List<Integer> list1 = new List<>(5);
        List<Integer> list2 = new List<>(2);
        List<Integer> list3 = new List<>(3);
        List<Integer> list4 = new List<>(4);
        list1.next = list2;
        list2.next = list3;
        list3.next = list4;
        list1 = revertList(list1);
        printList(list1);
        List<Integer> list5 = new List<>(2);
        List<Integer> list6 = new List<>(3);
        list5.next = list6;
        list5 = revertList(list5);
        printList(list5);
    }
}
