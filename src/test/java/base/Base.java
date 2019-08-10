package base;

/**
 * @Description:    java类作用描述
 * @Author:         zhjie.zhang
 * @CreateDate:     2019/7/11$ 11:42$
 * @UpdateUser:     zhjie.zhang
 * @UpdateDate:     2019/7/11$ 11:42$
 * @Version:        1.0
 */
public class Base {

    public static void main(String[] args) {
        short a = 128 ;
        byte b = (byte)a ;
        System.out.println(b);
        Math.sqrt(2);
        double k = gen(5);
        System.out.println(k);
    }
/**
* 求根號2
* */
    public static double gen(int k) {
        double left = 0;
        double right = 2;
        double middle = (left+right)/2;
        while(String.valueOf(middle).length()<=k+1) {
            if(Math.pow(middle, 2)<=2) {
                left = middle;
            }
            if(Math.pow(middle, 2)>2) {
                right = middle;
            }
            middle = (left + right)/2;
        }
        return middle;
    }

}
