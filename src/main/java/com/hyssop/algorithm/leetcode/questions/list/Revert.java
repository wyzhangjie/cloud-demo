package com.hyssop.algorithm.leetcode.questions.list;

/**
 * @Author jie.zhang
 * @create_time 2020/7/15 20:18
 * @updater
 * @update_time
 **/
public class Revert extends CommonList<Integer>{

    public static ListNode<Integer> getRevert(ListNode<Integer> listNode){
        if(listNode==null || listNode.next==null){
            return listNode;
        }
       ListNode<Integer> newHead = getRevert(listNode.next);
        listNode.next.next=listNode;
        listNode.next=null;


        return newHead;
    }
    private static void case5Test() {
        ListNode<Integer> list1 = new ListNode<>(1);
        ListNode<Integer> list2 = new ListNode<>(2);
        ListNode<Integer> list3 = new ListNode<>(3);
        ListNode<Integer> list4 = new ListNode<>(4);
        ListNode<Integer> list5 = new ListNode<>(5);
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
        list2.next = list3;
        list3.next = list4;
        printList(getRevert(list1));
        /*    printList(getMid(null));*/
    }
    public static void main(String[] args) {

        case5Test();
    }
}