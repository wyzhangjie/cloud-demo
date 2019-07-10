package com.hyssop.framework.spi;

/**
 * @Description:    java类作用描述
 * @Author:         zhjie.zhang
 * @CreateDate:     2019/7/8$ 20:23$
 * @UpdateUser:     zhjie.zhang
 * @UpdateDate:     2019/7/8$ 20:23$
 * @Version:        1.0
 */
public class SportCar implements Car {
    @Override
    public void run() {
        System.out.println("SportCar Running...");
    }
}
