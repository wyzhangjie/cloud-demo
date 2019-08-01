package java8function;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @Description:    lamda8 使用了设计模式，并且它已经集成很多东西，让设计模式更简单
 * @Author:         zhjie.zhang
 * @CreateDate:     2019/7/14$ 11:18$
 * @UpdateUser:     zhjie.zhang
 * @UpdateDate:     2019/7/14$ 11:18$
 * @Version:        1.0
 */
public class LamdaForDesighPattern {

    static private class Validator<T,R>{
        private final Function<T,R> strategy;
        public Validator(Function<T,R> v){
            this.strategy = v;
        }
        public R validate(T s){
            return strategy.apply(s); }
    }
    public static void main(String[] args) {
        // with lambdas
        Validator v3 = new Validator<String,Boolean>((String s) -> s.matches("\\d+"));
        System.out.println(v3.validate("aaaa"));
        Validator v4 = new Validator<String,Boolean>((String s) -> s.matches("[a-z]+"));
        System.out.println(v4.validate("bbbb"));
    }

}
