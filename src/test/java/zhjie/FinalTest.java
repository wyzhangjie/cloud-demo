package zhjie;


import java.util.*;

/**
 * @Author jie.zhang
 * @create_time 2019/12/18 16:10
 * @updater
 * @update_time
 **/
public class FinalTest {
    public static void main(String[] args) {
        //final 只能约束 strList 这个引用不可以被赋值，但是 strList 对象行为不被 final 影响，添加元素等操作是完全正常的。
        final List<String> strList = new ArrayList<>();
        strList.add("Hello");
        strList.add("world");
     //   strList = new ArrayList<>();
      //  List<String> unmodifiableStrList = List.of("hello", "world");
     //   unmodifiableStrList.add("again");
        Set<String> set = new HashSet<>();
        set.add("a");
        set.add("b");
        set.add("c");
        set = Collections.unmodifiableSet(set);
        set.add("d");
    }
}