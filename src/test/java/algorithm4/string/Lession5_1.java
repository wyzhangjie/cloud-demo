package algorithm4.string;

public class Lession5_1 {
    //低位优先排序（字符串长度相同）
    public static void sort(String[] a ,int W){
        int N = a.length; //表示有多少个待排序的字符串
        int R = 256;
        String[] aux = new String[N];
        for(int d= W-1;d>=0;d--){
            int[] count = new int[R+1];
            for(int i=0;i<N;i++){
                count[a[i].charAt(d)+1]++;
            }
            for(int r=0;r<R;r++){
                count[r+1]+=count[r];
            }

            for(int i=0;i<N;i++){
                aux[count[a[i].charAt(d)]++] = a[i];
            }
            for(int i=0;i<N;i++){
                a[i] = aux[i];

            }
        }
        for(int i=0;i<N;i++){
            System.out.println(a[i]);
        }
    }
    public static void main(String[] args) {
        String a1="14356abc";
        String a2="jtg34567";
        String a3="3tg34567";
        String[] s = {a2,a1,a3};
        sort(s,8);
    }
}
