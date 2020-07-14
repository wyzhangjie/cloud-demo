package algorithm.wrong100.Lession18.annotation;

import java.lang.annotation.*;

/**
 * @Author jie.zhang
 * @create_time 2020/4/23 13:50
 * @updater
 * @update_time
 **/

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface MyAnnotation {
    String value();
}