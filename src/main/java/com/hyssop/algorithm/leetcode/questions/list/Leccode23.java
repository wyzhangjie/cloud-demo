package com.hyssop.algorithm.leetcode.questions.list;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author jie.zhang
 * @create_time 2020/7/14 14:42
 * @updater
 * @update_time 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class Leccode23 {
    /**
     * 方法二
     */
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode fakeHead = new ListNode(null);
        ListNode p = fakeHead;
        int size = lists.length;
        PriorityQueue<ListNode> heap = new PriorityQueue(size, (Comparator<ListNode>) (o1, o2) -> o1.data - o2.data);
        for (int i = 0; i < size; i++) {
            if (lists[i] != null) {
                heap.offer(lists[i]);
            }
        }
        while (!heap.isEmpty()) {
            ListNode node = heap.poll();

            p.next = node;
            p = p.next;

            // 如果发现该节点后面还有后续节点，将后续节点加入到最小堆里。
            if (node.next != null) {
                heap.offer(node.next);
            }
        }
        return fakeHead.next;
    }

    /**
     * 解题思路三：分治法
     */
    public ListNode mergeKLists(ListNode[] lists, int low, int high) {
        if (low == high) {
            return lists[low];
        }

        int middle = low + (high - low) / 2; // 从中间切一刀

        return mergeTwoLists(
                mergeKLists(lists, low, middle),
                mergeKLists(lists, middle + 1, high)
        ); // 递归地处理左边和右边的链表，最后合并
    }

    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }

        if (a.data <= b.data) {
            a.next = mergeTwoLists(a.next, b);
            return a;
        }

        b.next = mergeTwoLists(a, b.next);
        return b;
    }

    public static class ListNode {
        Integer data;
        ListNode next;

        public ListNode(Integer t) {
            this.data = t;
        }
    }


}