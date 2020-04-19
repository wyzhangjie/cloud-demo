package interview;

import leetcode.tree.base.Node;

/**
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。
 *
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 给你这个链表：1->2->3->4->5
 *
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 *
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 *
 * */
public class ReverseKGroup {
    //普通的反转链表写法
    List reverse(List a) {
        List pre;
        List cur;
        List next;
        pre=null;
        cur=a;
        next=a;
        while (cur!=null){
            next = cur.next;
            cur.next=pre;
            pre=cur;
            cur=cur.next;
        }
        return pre;
    }
    /** 反转区间 [a, b) 的元素，注意是左闭右开 */
    List reverse(List a, List b) {
        List pre, cur, nxt;
        pre = null; cur = a; nxt = a;
        // while 终止的条件改一下就行了
        while (cur != b) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        // 返回反转后的头结点
        return pre;
    }
    public List reverseKGroup(List head, int k) {
        //bad case 链表为空
        if(head==null){
            return null;
        }
        List a;
        List b;
        a = b = head;
        //每次都循环遍历k个
        for(int i=0;i<k;i++){
            if(b==null) return head;
            b = b.next;
        }
        //反转前k个
        List newHead = reverse(a,b);
        // 递归反转后续链表并连接起来
        a.next = reverseKGroup(b,k);
        return newHead;
    }

    public static void main(String[] args) {
        List a1 = new List("1");
        List a2 = new List("2");
        List a3 = new List("3");
        List a4 = new List("4");
        List a5 = new List("5");
        a1.next =a2;
        a2.next = a3;
        a3.next=a4;
        a4.next =a5;
        ReverseKGroup reverseKGroup = new ReverseKGroup();
        List newHead = reverseKGroup.reverseKGroup(a1,3);
        while(newHead!=null){
            System.out.println(newHead.data);
            newHead=newHead.next;
        }
    }

    public static class List{
        String data;
        List next;
        public List(String data){
            this.data = data;

        }
    }
}
