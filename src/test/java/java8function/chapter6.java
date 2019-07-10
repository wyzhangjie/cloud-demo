package java8function;


import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.summingInt;
import static java8function.Dish.menu;

/**
 * @Description:    java类作用描述
 * @Author:         zhjie.zhang
 * @CreateDate:     2019/7/7$ 11:01$
 * @UpdateUser:     zhjie.zhang
 * @UpdateDate:     2019/7/7$ 11:01$
 * @Version:        1.0
 */
public class chapter6 {
    public static List<Transaction> transactions = Arrays.asList( new Transaction(Currency.EUR, 1500.0),
            new Transaction(Currency.USD, 2300.0),
            new Transaction(Currency.GBP, 9900.0),
            new Transaction(Currency.EUR, 1100.0),
            new Transaction(Currency.JPY, 7800.0),
            new Transaction(Currency.CHF, 6700.0),
            new Transaction(Currency.EUR, 5600.0),
            new Transaction(Currency.USD, 4500.0),
            new Transaction(Currency.CHF, 3400.0),
            new Transaction(Currency.GBP, 3200.0),
            new Transaction(Currency.USD, 4600.0),
            new Transaction(Currency.JPY, 5700.0),
            new Transaction(Currency.EUR, 6800.0) );


    public static class Transaction {
        private final Currency currency;
        private final double value;

        public Transaction(Currency currency, double value) {
            this.currency = currency;
            this.value = value;
        }

        public Currency getCurrency() {
            return currency;
        }

        public double getValue() {
            return value;
        }

        @Override
        public String toString() {
            return currency + " " + value;
        }
    }

    public enum Currency {
        EUR, USD, JPY, GBP, CHF
    }
    private static Dish findMostCaloricDishUsingComparator() {
        Comparator<Dish> dishCaloriesComparator = comparingInt(Dish::getCalories);
        BinaryOperator<Dish> moreCaloricOf = BinaryOperator.maxBy(dishCaloriesComparator);
        return menu.stream().collect(reducing(moreCaloricOf)).get();
    }

    private static Dish findLastCaloricDishUsingComparator() {
        Comparator<Dish> dishCaloriesComparator = comparingInt(Dish::getCalories);
        BinaryOperator<Dish> moreCaloricOf = BinaryOperator.minBy(dishCaloriesComparator);
        return menu.stream().collect(reducing(moreCaloricOf)).get();
    }

    private static int calculateTotalCalories() {
        return menu.stream().collect(summingInt(Dish::getCalories));
    }

    private static double calculateAvgCalories(){
        return menu.stream().collect(averagingInt(Dish::getCalories));
    }

    private static void joinTest(){
        String shortMenu = menu.stream().map(Dish::getName).collect(joining(","));
        System.out.println("短菜单："+shortMenu);
    }
    private static Dish findMostCaloricDish() {
        return menu.stream().collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)).get();
    }

    private static void reducingTest(){
        String shortMenu = menu.stream().map(Dish::getName).collect(reducing((s1,s2) ->s1+s2)).get();
        System.out.println(shortMenu);
    }

    private static void grouingTest(){
        Map<Dish.Type, Optional<Dish>> mostCaloriceByType = menu.stream().collect(groupingBy(Dish::getType,maxBy(comparingInt(Dish::getCalories))));

    }

    private static void grouingTest1(){
        Map<Dish.Type, Dish> mostCaloriceByType = menu.stream().collect(groupingBy(Dish::getType,collectingAndThen(maxBy(comparingInt(Dish::getCalories)),Optional::get)));

    }

    public static void main(String[] args) {
        Map<Currency,List<Transaction>> map = transactions.stream().collect(groupingBy(Transaction::getCurrency));
        long howmanyDeshies = menu.stream().collect(Collectors.counting());
        System.out.println(howmanyDeshies);
        howmanyDeshies = menu.stream().count();
        System.out.println(howmanyDeshies);
        //总数
        int all =calculateTotalCalories();
        System.out.println("总的卡路里："+all);
        //最大值
        Dish dish = findMostCaloricDishUsingComparator();
        System.out.println("最大卡路里："+dish.getCalories());
        //最小值
        dish = findLastCaloricDishUsingComparator();
        System.out.println("最小卡路里："+dish.getCalories());
        //平均值：
        double allCalaries = calculateAvgCalories();
        System.out.println("平均卡路里："+allCalaries);
        //将list to string
        joinTest();
        reducingTest();
        grouingTest();
        grouingTest1();

    }
}
