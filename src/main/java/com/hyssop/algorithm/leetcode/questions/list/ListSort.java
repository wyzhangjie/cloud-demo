package com.hyssop.algorithm.leetcode.questions.list;

/**
 * @Author jie.zhang
 * @create_time 2020/7/16 10:18
 * @updater
 * @update_time
 *
 * 链表实现插入排序 冒泡排序 选择排序(从小到大排序)
 **/
public class ListSort  extends CommonList<Integer>{

    //插入排序
    public static ListNode getInsertSort(ListNode<Integer> head){
        if(head==null || head.next==null){
            return head;
        }else {
            ListNode<Integer> dummy = new ListNode(null);
            dummy.next = head;
            ListNode<Integer> first = dummy.next;
            ListNode<Integer> second = first.next;
            while(second!=null){
                while(first!=second){
                    if(first.data>second.data){
                        Integer tep = second.data;
                        second.data = first.data;
                        first.data = tep;
                    }
                    first = first.next;
                }
                second =second.next;
                first = dummy.next;
            }
            return dummy.next;
        }


    }
    //冒泡排序
    public static ListNode getMaopao(ListNode<Integer> head){
        if(head==null || head.next==null){
            return head;
        }else {
            ListNode<Integer> dummy = new ListNode(null);
            dummy.next = head;
            ListNode<Integer> first = dummy.next;
            ListNode<Integer> pre = dummy.next;
            ListNode<Integer> endNode = first;
            while (endNode.next!=null) {
                endNode=endNode.next;
            }
            while (endNode!=dummy.next){
                while (first!=endNode){
                    if(first.data>first.next.data){
                        Integer tmp = first.data;
                        first.data = first.next.data;
                        first.next.data =tmp;
                    }
                    pre=first;
                    first =first.next;
                }
                endNode =pre;
                first=dummy.next;
            }
            return dummy.next;
        }
    }


    //选择排序
    public static ListNode getSelectSort(ListNode<Integer> head){
        if(head==null || head.next==null){
            return head;
        }else {
            ListNode<Integer> dummy = new ListNode(null);
            dummy.next = head;
            ListNode<Integer> first = dummy.next;
            ListNode<Integer> second = first.next;
            while (second!=null){
                while (first!=second){
                    if(first.data>second.data){
                        Integer tmp = first.data;
                        first.data = second.data;
                        second.data =tmp;
                    }
                    first=first.next;
                }
                first=dummy.next;
                second=second.next;
            }
        return dummy.next;
        }
    }

    public static void main(String[] args) {

            ListNode<Integer> list1 = new ListNode<>(1);
            ListNode<Integer> list2 = new ListNode<>(7);
            ListNode<Integer> list3 = new ListNode<>(2);
            ListNode<Integer> list4 = new ListNode<>(3);
            ListNode<Integer> list5 = new ListNode<>(6);
            list1.next = list2;
            list2.next = list3;
            list3.next = list4;
            printList(getSelectSort(list1));
    }
}