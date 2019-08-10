package leetcode.agri;

/**
 * @Description:    归并排序 也叫分支排序，将数据分解到可以排序的大小，然后再俩俩合并
 * @Author:         zhjie.zhang
 * @CreateDate:     2019/7/16$ 9:58$
 * @UpdateUser:     zhjie.zhang
 * @UpdateDate:     2019/7/16$ 9:58$
 * @Version:        1.0
 */
public class MergeSort {

    public static void mergeSort(int[] a, int begin, int end) {
        if (begin >= end) return;
        int mid = (begin + end) / 2;
        mergeSort(a, begin, mid);
        mergeSort(a, mid + 1, end);
        merge(a, begin, mid, end);
    }

    private static void merge(int[] a, int begin, int mid, int end) {
        int[] temp = new int[end - begin + 1];
        int i = begin;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= end) {
            if (a[i] <= a[j]) {
                temp[k] = a[i];
                i++;
                k++;
            }
            if (a[j] < a[i]) {
                temp[k] = a[j];
                j++;
                k++;
            }
        }
        while (i <= mid) {
            temp[k++] = a[i++];
        }
        while (j <= end) {
            temp[k++] = a[j++];
        }
        i = 0;
        for (int t = begin; t <= end; t++) {
            a[t] = temp[i++];
        }
    }

    public static void main(String[] args) {
        int a[] = new int[]{1, 2, 7, 4, 6, 3};
        mergeSort(a, 0, a.length - 1);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }
}
