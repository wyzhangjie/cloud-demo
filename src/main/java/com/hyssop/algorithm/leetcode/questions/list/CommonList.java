package com.hyssop.algorithm.leetcode.questions.list;

/**
 * @Author jie.zhang
 * @create_time 2020/7/15 20:19
 * @updater
 * @update_time
 **/
public class CommonList<T> {
    public static class ListNode<T> {
        T data;
        ListNode<T> next;

        public ListNode(T t) {
            this.data = t;
        }
    }
    public static <T extends Comparable<? super T>> void printList(ListNode<T> list) {
        while (list != null) {
            System.out.println(list.data);
            list = list.next;
        }
        System.out.println("breautiful line=======================================");

    }

}