package com.hyssop.algorithm.leetcode.questions.sort;


/**
 * @Description:    力扣（LeetCode） -147 对链表进行插入排序
 * 对链表进行插入排序。
 * 插入排序算法：
 * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * 重复直到所有输入数据插入完为止。
 * 示例1:
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例2:
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 * 题目思考
 * 思路很直观，遍历链表中的每一个结点，通过逐个比较将每个结点插入到正确的位置。注意结点处于开头和结尾等特殊情况。
 *
 * @CreateDate:     2019/7/14$ 19:03$
 * @UpdateUser:     zhjie.zhang
 * @UpdateDate:     2019/7/14$ 19:03$
 * @Version:        1.0
 */
public class InsertSort {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode insertionSortList(ListNode head) {
        //虚拟头结点
        ListNode dummy = new ListNode(-1);
        //当前指针指向虚拟头结点
        ListNode cur = dummy;
        //遍历旧链表
        while(head!=null){
            //为了链表向前进做的伏笔
            ListNode next =head.next;
            //遍历新的链表，获得head数字的插入位置。规则就是遍历cur找到head大于cur.next的位置，即为插入位置。
            while (cur.next!=null && cur.next.val<=head.val){
                cur =cur.next;
            }
            //插入方式是倒序
            //1，断开head和旧链表的连接
            head.next = cur.next;
            //2、将head插入到刚刚遍历过得最小位置。
            cur.next = head;
            //head向前进。
            head = next;
        }
        return dummy.next;
    }

    public static void insertSort(int[] a, int n) {
        int i, j, k;

        for (i = 1; i < n; i++) {

            //为a[i]在前面的a[0...i-1]有序区间中找一个合适的位置
            for (j = i - 1; j >= 0; j--)
                if (a[j] < a[i])
                    break;

            //如找到了一个合适的位置
            if (j != i - 1) {
                //将比a[i]大的数据向后移
                int temp = a[i];
                for (k = i - 1; k > j; k--)
                    a[k + 1] = a[k];
                //将a[i]放到正确位置上
                a[k + 1] = temp;
            }
        }
    }

    public int[] insertionForArray(int[] a){
        //考虑下边界
        if(a==null||a.length<1){
            return a;
        }else {
            //插入排序，就是找到一个后面的元素在已排序队列的位置，将找到的位置后移给这个找到位置的元素腾地方放进去
            int len = a.length;
          for(int i=1;i<len;i++){
              int j =i-1;
              for( ;j>=0;j--){
                  if(a[j]<a[i])
                      break;
              }
              if(i!=j+1){
                  int tep = a[i];
                  for(int k=i-1;k>j;k--){
                      a[k+1] =a[k];
                  }
                  a[j+1] =tep;
              }
          }
        }
        return a;
    }

    private void swap(int[] a, int i, int i1) {
        a[i] =a[i]^a[i1];
        a[i1] =a[i]^a[i1];
        a[i]= a[i1]^a[i];
    }


    public void test1(){
        int a[] = new int[]{4,2,1,3};
        a = new int[]{1};
       a = insertionForArray(a);
        insertionForArray(a);
        //insertSort(a,4);
     /*   for(int i=0;i<a.length;i++){
            System.out.println(a[i]);
        }*/

        ListNode a1= new ListNode(4);
        ListNode a2= new ListNode(2);
        ListNode a3= new ListNode(1);
        ListNode a4= new ListNode(3);
        a1.next = a2;
        a2.next =a3;
        a3.next = a4;
        ListNode result = insertionSortList(a1);
        while(result!=null){
            System.out.println(result.val);
            result = result.next;
        }
    }

    public static void main(String[] args) {

           InsertSort sort = new InsertSort();
           sort.test1();

    }
}
