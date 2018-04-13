package com.encdata.zyj.myapplication;

import org.junit.Test;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Java8 lambda表达式10个示例
 * 引用自：http://www.importnew.com/16436.html
 * Created by zhaoyuejun on 2018/4/11.
 */

public class java8LambdaTest {

    @Test
    public void main() {
        tes10();
    }

    /**
     * 例1 用lambda表达式实现Runnable
     */
    public void test() {
        // Java 8之前：
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Before Java8, too much code for too little to do");
            }
        }).start();
        //Java 8方式：
        new Thread(() -> System.out.println("In Java8, Lambda expression rocks !!")).start();
//        输出：
//        Before Java8, too much code for too little to do
//        In Java8, Lambda expression rocks !!
    }

    /**
     * 例2 使用Java 8 lambda表达式进行事件处理
     */
    public void test1() {
        // Java 8之前：
//        JButton show = new JButton("Show");
//        show.addActionListener(new WifiP2pManager.ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("Event handling without lambda expression is boring");
//            }
//        });
//        // Java 8方式：
//        show.addActionListener((e) -> {
//            System.out.println("Light, Camera, Action !! Lambda expressions Rocks");
//        });
    }

    /**
     * 例3、使用lambda表达式对列表进行迭代
     */
    public void test2() {
        // Java 8之前：
        List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        for (String feature : features) {
            //输出一
            System.out.println(feature);
        }
        // Java 8之后：
        List<String> features1 = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        //输出二
        features1.forEach(n -> System.out.println(n));
        // 使用Java 8的方法引用更方便，方法引用由::双冒号操作符标示，
        // 看起来像C++的作用域解析运算符
        //输出三
        features1.forEach(System.out::println);
//        输出：
//        Lambdas
//        Default Method
//        Stream API
//        Date and Time API
    }

    /**
     * 例4、使用lambda表达式和函数式接口Predicate
     * Predicate接口非常适用于做过滤。
     */
    public void test3() {
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        System.out.print("Languages which starts with J :-----");
        filter(languages, (str) -> str.startsWith("J"));

        System.out.print("Languages which ends with a :-----");
        filter(languages, (str) -> str.endsWith("a"));

        System.out.print("Print all languages :-----");
        filter(languages, (str) -> true);

        System.out.print("Print no language : -----");
        filter(languages, (str) -> false);

        System.out.print("Print language whose length greater than 4 :-----");
        filter(languages, (str) -> str.length() > 4);
//        输出：
//        Languages which starts with J :-----Java
//        Languages which ends with a :-----Java Scala
//        Print all languages :-----Java Scala C++ Haskell Lisp
//        Print no language : -----
//        Print language whose length greater than 4 :-----Scala Haskell
    }

    public static void filter(List<String> names, Predicate<String> condition) {
        StringBuffer stringBuffer = new StringBuffer();
        for (String name : names) {
            if (condition.test(name)) {
                stringBuffer.append(name + " ");
            }
        }
        System.out.println(stringBuffer.toString() + " ");
    }

    /**
     * 例5、如何在lambda表达式中加入Predicate
     * java.util.function.Predicate 允许将两个或更多的 Predicate 合成一个。
     * 它提供类似于逻辑操作符AND和OR的方法，名字叫做and()、or()和xor()，用于将传入 filter() 方法的条件合并起来。
     */
    public void test4() {
        List<String> names = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        // 甚至可以用and()、or()和xor()逻辑函数来合并Predicate，
        // 例如要找到所有以J开始，长度为四个字母的名字，你可以合并两个Predicate并传入
        Predicate<String> startsWithJ = (n) -> n.startsWith("J");
        Predicate<String> fourLetterLong = (n) -> n.length() == 4;
        names.stream()
                .filter(startsWithJ.and(fourLetterLong))
                .forEach((n) -> System.out.print("nName, which starts with 'J' and four letter long is : " + n));
//        输出：
//        nName, which starts with 'J' and four letter long is : Java
    }

    /**
     * 例6、Java 8中使用lambda表达式的Map和Reduce示例
     * 最广为人知的函数式编程概念map.它允许你将对象进行转换。例如在本例中，我们将 costBeforeTax 列表的每个元素转换成为税后的值。
     * 我们将 x -> x*x lambda表达式传到 map() 方法，后者将其应用到流中的每一个元素。然后用 forEach() 将列表元素打印出来。
     */
    public void test5() {
        // 不使用lambda表达式为每个订单加上12%的税
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        for (Integer cost : costBeforeTax) {
            double price = cost + .12 * cost;
            System.out.println(price);
        }

        // 使用lambda表达式
        List<Integer> costBeforeTax1 = Arrays.asList(100, 200, 300, 400, 500);
        costBeforeTax1.stream().map((cost) -> cost + .12 * cost).forEach(System.out::println);
//        输出：
//        112.0
//        224.0
//        336.0
//        448.0
//        560.0
    }

    /**
     * 例6.2、Java 8中使用lambda表达式的Map和Reduce示例
     * 还有一个 reduce() 函数可以将所有值合并成一个。Map和Reduce操作是函数式编程的核心操作，因为其功能，reduce 又被称为折叠操作。
     * IntStream这样的类有类似 average()、count()、sum() 的内建方法来做 reduce 操作，也有mapToLong()、mapToDouble() 方法来做转换。这并不会限制你，你可以用内建方法，也可以自己定义。
     */
    public void test6() {
        // 为每个订单加上12%的税
        // 老方法：
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        double total = 0;
        for (Integer cost : costBeforeTax) {
            double price = cost + .12 * cost;
            total = total + price;
        }
        System.out.println("Total : " + total);
        // 新方法：
        List<Integer> costBeforeTax1 = Arrays.asList(100, 200, 300, 400, 500);
        double bill = costBeforeTax1.stream().map((cost) -> cost + .12 * cost).reduce((sum, cost) -> sum + cost).get();
        System.out.println("Total : " + bill);
//        输出：
//        Total : 1680.0
    }

    /**
     * 例7、通过过滤创建一个String列表
     * 过滤是Java开发者在大规模集合上的一个常用操作，而现在使用lambda表达式和流API过滤大规模数据集合是惊人的简单。
     * 流提供了一个 filter() 方法，接受一个 Predicate 对象，即可以传入一个lambda表达式作为过滤逻辑。
     */
    public void test7() {
        List<String> strList = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        // 创建一个字符串列表，每个字符串长度大于2
        List<String> filtered = strList.stream().filter(x -> x.length() > 4).collect(Collectors.toList());
        System.out.printf("Original List : %s, filtered list : %s %n", strList, filtered);
//        输出：
//        Original List : [Java, Scala, C++, Haskell, Lisp], filtered list : [Scala, Haskell]
    }

    /**
     * 例8、对列表的每个元素应用函数
     * 我们通常需要对列表的每个元素使用某个函数，例如逐一乘以某个数、除以某个数或者做其它操作。这些操作都很适合用 map() 方法，
     * 可以将转换逻辑以lambda表达式的形式放在 map() 方法里，就可以对集合的各个元素进行转换了
     */
    public void test8() {
        // 将字符串换成大写并用逗号链接起来
        List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.", "Canada");
        String G7Countries = G7.stream().map(x -> x.toUpperCase()).collect(Collectors.joining(", "));
        System.out.println(G7Countries);
//        输出：
//        USA, JAPAN, FRANCE, GERMANY, ITALY, U.K., CANADA
    }

    /**
     * 例9、复制不同的值，创建一个子列表
     * 本例展示了如何利用流的 distinct() 方法来对集合进行去重。
     */
    public void test9() {
        // 用所有不同的数字创建一个正方形列表
        List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
        List<Integer> distinct = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        System.out.printf("Original List : %s,  Square Without duplicates : %s %n", numbers, distinct);
    }

    /**
     * 例10、计算集合元素的最大值、最小值、总和以及平均值
     */
    public void tes10() {
        //获取数字的个数、最小值、最大值、总和以及平均值
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("Highest prime number in List : " + stats.getMax());
        System.out.println("Lowest prime number in List : " + stats.getMin());
        System.out.println("Sum of all prime numbers : " + stats.getSum());
        System.out.println("Average of all prime numbers : " + stats.getAverage());
//        输出：
//        Highest prime number in List : 29
//        Lowest prime number in List : 2
//        Sum of all prime numbers : 129
//        Average of all prime numbers : 12.9
    }

    public void test11() {
        List<Integer> primes = Arrays.asList(new Integer[]{2, 3, 5, 7});
        final int factor = 2;
        primes.forEach(element -> {
            System.out.println(factor * element);
        });
    }
}
