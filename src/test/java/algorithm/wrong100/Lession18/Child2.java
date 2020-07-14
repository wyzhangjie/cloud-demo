package algorithm.wrong100.Lession18;

import java.util.Arrays;

/**
 * @Author jie.zhang
 * @create_time 2020/4/23 13:26
 * @updater
 * @update_time
 **/

class Child2 extends Parent<String> {
    @Override
    public void setValue(String value) {
        System.out.println("Child2.setValue called");
        super.setValue(value);
    }
    //javap -c Child2.class能看到有两个setValue方法
    //通过工具jclasslib 可以看到泛型给我们桥接的一个擦除类型的一个Object入参方法
    //最后小结下，使用反射查询类方法清单时，我们要注意两点：
    // getMethods 和 getDeclaredMethods 是有区别的，前者可以查询到父类方法，后者只能查询到当前类。
    // 反射进行方法调用要注意过滤桥接方法。
    public static void main(String[] args) {
        //反编译代码里面有两个setValue
     /*   Child2 child1 = new Child2();
        Arrays.stream(child1.getClass().getDeclaredMethods())
                .filter(method -> method.getName().equals("setValue"))
                .forEach(method -> {
                    try {
                        method.invoke(child1, "test");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        System.out.println(child1.toString());*/
        Child2 child2 = new Child2();
        Arrays.stream(child2.getClass().getDeclaredMethods())
                .filter(method -> method.getName().equals("setValue") && !method.isBridge())
                .findFirst().ifPresent(method -> {
            try {
                method.invoke(child2, "test");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}