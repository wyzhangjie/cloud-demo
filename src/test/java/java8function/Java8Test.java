package java8function;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Description:    java类作用描述
 * @Author:         zhjie.zhang
 * @CreateDate:     2019/7/7$ 10:35$
 * @UpdateUser:     zhjie.zhang
 * @UpdateDate:     2019/7/7$ 10:35$
 * @Version:        1.0
 */
public class Java8Test {
    public static void testIntSupplier(){
        IntSupplier fib = new IntSupplier() {
            private int previous =0;
            private int current =1;

            @Override
            public int getAsInt() {
                int oloPrevious = this.previous;
                int nextValue = this.previous +this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oloPrevious;
            }
        };
        IntStream.generate(fib).limit(10).forEach(System.out::println);
    }

    public static void testFlatMap(){
        List<String> word = Arrays.asList("java 8","Lamda","In","Action");
        List<Integer> wordLengths = word.stream().map(String::length).collect(Collectors.toList());
     //   wordLengths.forEach(System.out::println);
         word.stream().map(wod -> wod.split("")).flatMap(Arrays::stream).forEach(System.out::println);
    }
    public static void main(String[] args) {
     //   testIntSupplier();
        testFlatMap();
    }
}
