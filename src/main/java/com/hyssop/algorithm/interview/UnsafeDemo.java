package com.hyssop.algorithm.interview;

import java.util.Arrays;

/**
 * @Author jie.zhang
 * @create_time 2020/7/3 14:20
 * @updater
 * @update_time
 **/
public class UnsafeDemo {
    public  static  final  int  _1MB  =  1024  *  1024;

        public  static  void  main(String[]  args)  throws  Exception  {
                /*Field field  =  Unsafe.class.getDeclaredField("theUnsafe");
                field.setAccessible(true);
                Unsafe  unsafe  =  (Unsafe)  field.get(null);
                for  (;  ;  )  {
                        unsafe.allocateMemory(_1MB);
                }*/
            LeakExample[] a = new LeakExample[1];
            LeakExample a1 = new LeakExample();
            a1.setA("a");
            a[0]=a1;
            LeakExample[] copystr= Arrays.copyOf(a,a.length);
            copystr[0].setA("b");
            for(int i=0;i<copystr.length;i++){
                System.out.println(copystr[i]);
            }

        }


}
