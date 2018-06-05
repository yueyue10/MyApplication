package com.example.zyj.myapplication;

import org.junit.Test;

/**
 * Java 8 Lambda 表达式
 * http://www.runoob.com/java/java8-lambda-expressions.html
 */
public class Java8Tester {

    /**
     * 使用 Lambda 表达式需要注意以下两点：
     * Lambda 表达式主要用来定义行内执行的方法类型接口，
     * 例如，一个简单方法接口。在上面例子中，我们使用各种类型的Lambda表达式来定义MathOperation接口的方法。然后我们定义了sayMessage的执行。
     * Lambda 表达式免去了使用匿名方法的麻烦，并且给予Java简单但是强大的函数化的编程能力
     */
    @Test
    public void main() {
        Java8Tester tester = new Java8Tester();
        // 类型声明
        MathOperation addition = (int a, int b) -> a + b;
        // 不用类型声明
        MathOperation subtraction = (a, b) -> a - b;
        // 大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> {
            return a * b;
        };

        // 没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b;

        System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
        System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + tester.operate(10, 5, division));
        // 不用括号
        GreetingService greetService1 = message -> System.out.println("Hello " + message);

        // 用括号
        GreetingService greetService2 = (message) -> System.out.println("Hello " + message);

        greetService1.sayMessage("Runoob");
        greetService2.sayMessage("Google");
    }

    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

    final static String salutation = "Hello! ";

    /**
     * lambda 表达式只能引用标记了 final 的外层局部变量，这就是说不能在 lambda 内部修改定义在域外的局部变量，否则会编译错误。
     */
    @Test
    public void test1() {
        Klog.pl("-------------------------------------------------");
        GreetingService greetService1 = message -> System.out.println(salutation + message);
        greetService1.sayMessage("Runoob");
    }

    /**
     * 我们也可以直接在 lambda 表达式中访问外层的局部变量：
     */
    @Test
    public void test2() {
        Klog.pl("-------------------------------------------------");
        final int num = 1;
        Converter<Integer, String> s = (param) -> System.out.println(String.valueOf(param + num));
        s.convert(2);  // 输出结果为 3
    }

    public interface Converter<T1, T2> {
        void convert(int i);
    }

    /**
     * lambda 表达式的局部变量可以不用声明为 final，但是必须不可被后面的代码修改（即隐性的具有 final 的语义）
     */
    @Test
    public void test3() {
        int num = 1;
//        Converter<Integer, String> s = (param) -> System.out.println(String.valueOf(param + num));//编译报错
//        s.convert(2);
        num = 5;
    }

    /**
     * 在 Lambda 表达式当中不允许声明一个与局部变量同名的参数或者局部变量。
     */
    public void test4() {
        String first = "";
//        Comparator<String> comparator = (first, second) -> Integer.compare(first.length(), second.length());  //编译会出错
    }
}