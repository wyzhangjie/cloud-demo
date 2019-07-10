package com.hyssop.framework.spi;

/**
 * @Description:    java类作用描述
 * @Author:         zhjie.zhang
 * @CreateDate:     2019/7/8$ 20:22$
 * @UpdateUser:     zhjie.zhang
 * @UpdateDate:     2019/7/8$ 20:22$
 * @Version:        1.0
 */
public class RacingCar implements Car {
    @Override
    public void run() {
        System.out.println("RacingCar Running...");
    }
}
