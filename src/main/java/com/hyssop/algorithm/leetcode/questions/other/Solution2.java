package com.hyssop.algorithm.leetcode.questions.other;

/**
 * @Description:    java类作用描述
 * @Author:         zhjie.zhang
 * @CreateDate:     2019/6/26$ 10:00$
 * @UpdateUser:     zhjie.zhang
 * @UpdateDate:     2019/6/26$ 10:00$
 * @Version:        1.0
 */
public class Solution2 {
    public static class List{
        String data;
        List next;
        public List(String data){
            this.data = data;

        }
    }

    /**递归算法
    *
    * */
    public static void recursion(List head){
     if(head.next!=null){
         recursion(head.next);
     }
        System.out.println(head.data);
    }

    /**
     * 非递归算法
    * */
    public static List notRecursion(List head){
        if(head==null||head.next==null){
            return head;
        }
        List begin =head;
        List middle = begin.next;
        List end =middle.next;
        /*第一个字符的next指针为空*/
        begin.next = null;
        if(end==null){
            middle.next = begin;
            begin = middle;
            return begin;
        }else {
            while(end!=null){
                middle.next = begin;
                begin =middle;
                middle=end;
                end = end.next;
            }
            middle.next = begin;
            begin =middle;
            return begin;
        }


    }
    /**
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
    *
    * */
    public static void main(String[] args) {

        List list1 = new List("1");
        List list2 = new List("2");
        List list3 = new List("3");
        List list4 = new List("4");
        List list5 = new List("5");
        list1.next = list2;
        list2.next = list3;
        list3.next = list4;
        list4.next = list5;
     //   recursion(list1);
        List printList =  notRecursion(list1);
        while(printList!=null){
            System.out.println(printList.data);
            printList= printList.next;
        }

    }
}
