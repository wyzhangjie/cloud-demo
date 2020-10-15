package algorithm.design.visitor;

/**
 * @Author jie.zhang
 * @create_time 2020/4/21 16:22
 * @updater
 * @update_time
 **/

public class ParentClass {
    public void f() {
        System.out.println("I am ParentClass's f().");
    }
}

class ChildClass extends ParentClass {
   public void f() {
       System.out.println("I am ChildClass's f().");
   }
}

 class SingleDispatchClass {
    public void polymorphismFunction(ParentClass p) {
        p.f();
    }
    //编译阶段是根据直接类型来找方法，找到后运行时候p还是指向对象的
    public void overloadFunction(ParentClass p) {
        p.f();
    }

    public void overloadFunction(ChildClass c) {
        c.f();
    }
}

 class DemoMain {
    public static void main(String[] args) {
        //Java 支持多态特性，代码可以在运行时获得对象的实际类型（也就是前面提到的运行时类型），然后根据实际类型决定调用哪个方法。尽管 Java 支持函数重载，但 Java 设计的函数重载的语法规则是，并不是在运行时，根据传递进函数的参数的实际类型，来决定调用哪个重载函数，而是在编译时，根据传递进函数的参数的声明类型（也就是前面提到的编译时类型），
        // 来决定调用哪个重载函数。也就是说，具体执行哪个对象的哪个方法，只跟对象的运行时类型有关，跟参数的运行时类型无关。所以，Java 语言只支持 Single Dispatch。
        SingleDispatchClass demo = new SingleDispatchClass();
        ParentClass p = new ChildClass();
        demo.polymorphismFunction(p);//执行哪个对象的方法，由对象的实际类型决定
        demo.overloadFunction(p);//执行对象的哪个方法，由参数对象的声明类型决定
    }
}

