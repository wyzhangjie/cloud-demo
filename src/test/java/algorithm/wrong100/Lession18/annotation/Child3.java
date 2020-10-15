package algorithm.wrong100.Lession18.annotation;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author jie.zhang
 * @create_time 2020/4/23 14:17
 * @updater
 * @update_time
 **/
@Slf4j
public class Child3 extends Parent {
    @Override
    public void foo() {
    }

//子类要想获得父类的注解可以使用spring官方提供的类方法  AnnotatedElementUtils.findMergedAnnotation
    /**
     * 第一，反射调用方法并不是通过调用时的传参确定方法重载，而是在获取方法的时候通过方法名和参数类型来确定的。遇到方法有包装类型和基本类型重载的时候，你需要特别注意这一点。
     * 第二，反射获取类成员，需要注意 getXXX 和 getDeclaredXXX 方法的区别，其中 XXX 包括 Methods、Fields、Constructors、Annotations。这两类方法，针对不同的成员类型 XXX 和对象，在实现上都有一些细节差异，详情请查看官方文档。今天提到的 getDeclaredMethods 方法无法获得父类定义的方法，而 getMethods 方法可以，只是差异之一，不能适用于所有的 XXX。
     * 第三，泛型因为类型擦除会导致泛型方法 T 占位符被替换为 Object，子类如果使用具体类型覆盖父类实现，编译器会生成桥接方法。这样既满足子类方法重写父类方法的定义，又满足子类实现的方法有具体的类型。使用反射来获取方法清单时，你需要特别注意这一点。第四，自定义注解可以通过标记元注解 @Inherited 实现注解的继承，不过这只适用于类。如果要继承定义在接口或方法上的注解，可以使用 Spring 的工具类 AnnotatedElementUtils，并注意各种 getXXX 方法和 findXXX 方法的区别，详情查看Spring 的文档。最后，我要说的是。编译后的代码和原始代码并不完全一致，编译器可能会做一些优化，加上还有诸如 AspectJ 等编译时增强框架，使用反射动态获取类型的元数据可能会和我们编写的源码有差异，这点需要特别注意。你可以在反射中多写断言，遇到非预期的情况直接抛异常，避免通过反射实现的业务逻辑不符合预期。
     * */
    private static String getAnnotationValue(MyAnnotation annotation) {
        if (annotation == null) return "";
        return annotation.value();
    }


    public static void wrong() throws NoSuchMethodException {
        //获取父类的类和方法上的注解
        Parent parent = new Parent();
        log.info("ParentClass:{}", getAnnotationValue(parent.getClass().getAnnotation(MyAnnotation.class)));
        log.info("ParentMethod:{}", getAnnotationValue(parent.getClass().getMethod("foo").getAnnotation(MyAnnotation.class)));

        //获取子类的类和方法上的注解
        Child child = new Child();
        log.info("ChildClass:{}", getAnnotationValue(child.getClass().getAnnotation(MyAnnotation.class)));
        log.info("ChildMethod:{}", getAnnotationValue(child.getClass().getMethod("foo").getAnnotation(MyAnnotation.class)));
    }

    public static void main(String[] args) throws NoSuchMethodException {
        wrong();
    }
}