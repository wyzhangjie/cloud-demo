package leetcode.agri;


/**
 * @Description:    快排
 * @Author:         zhjie.zhang
 * @CreateDate:     2019/7/14$ 23:22$
 * @UpdateUser:     zhjie.zhang
 * @UpdateDate:     2019/7/14$ 23:22$
 * @Version:        1.0
 */
public class QuickSort {
    public int[] quickSort(int[] a, int begin, int end) {
        if (end - begin <= 1) {
            return a;
        }
        int prior = a[begin];
        int left = begin ;
        int right = end;
        while (left < right) {
            //从右面开始往前找，找到小于哨兵的数，交换，交换后分别将前后指针移动一个位置。
            while (left < right&&a[right] > prior) {
                right--;
            }
            if (left != right) {
                swap(a, left, right);
                left++;
                right--;
            }

        }
        if (a[begin] > a[left]) {
            swap(a, begin, left);
            quickSort(a, begin, left - 1);
            quickSort(a, left + 1, end);
        } else {
            quickSort(a, begin + 1, end);
        }


        return a;

    }

    public void swap(int[] a, int from, int to) {
        a[from] = a[from] ^ a[to];
        a[to] = a[from] ^ a[to];
        a[from] = a[from] ^ a[to];
    }

    public static void main(String[] args) {
        QuickSort sort1 = new QuickSort();
        sort1.test1();
    }

    private void test1() {
        int[] a = new int[]{8, 3, 9, 5, 2};
            quickSort(a, 0, a.length - 1);
    /*    a = new int[]{1, 3, 9, 5, 2};
        *//*    a = new  int[] {7};*//*
        quickSort(a, 0, a.length - 1);*/
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }

    }
}
