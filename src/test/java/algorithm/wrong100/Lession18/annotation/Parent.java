package algorithm.wrong100.Lession18.annotation;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author jie.zhang
 * @create_time 2020/4/23 13:58
 * @updater
 * @update_time
 **/

@MyAnnotation(value = "Class")
@Slf4j
class Parent {

    @MyAnnotation(value = "Method")
    public void foo() {
    }
}

@Slf4j
class Child extends Parent {

}