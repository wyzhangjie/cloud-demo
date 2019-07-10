package com.hyssop.framework.spi;

import java.util.ServiceLoader;

/**
 * @Description:    java类作用描述
 * @Author:         zhjie.zhang
 * @CreateDate:     2019/7/8$ 20:26$
 * @UpdateUser:     zhjie.zhang
 * @UpdateDate:     2019/7/8$ 20:26$
 * @Version:        1.0
 */
public class Test {

    public static void main(String[] agrs) {
        ServiceLoader<Car> serviceLoader = ServiceLoader.load(Car.class);
        serviceLoader.forEach(car -> {
            car.run();
        });
    }

}
