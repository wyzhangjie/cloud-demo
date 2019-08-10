package leetcode.agri;

/**
 * @Description:    java类作用描述
 * @Author:         zhjie.zhang
 * @CreateDate:     2019/7/17$ 19:50$
 * @UpdateUser:     zhjie.zhang
 * @UpdateDate:     2019/7/17$ 19:50$
 * @Version:        1.0
 */
public abstract class Leetcode {

    public static void print(int[] data) {
        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i]);
        }
    }

    public static void swap(int[] a, int from, int to) {
        a[from] = a[from] ^ a[to];
        a[to] = a[from] ^ a[to];
        a[from] = a[from] ^ a[to];
    }
}
