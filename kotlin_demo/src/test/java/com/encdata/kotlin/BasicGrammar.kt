package com.encdata.kotlin

import org.junit.Test

/**
 * Kotlin 基础语法
 * Created by zhaoyuejun on 2018/6/6.
 * 参考：http://www.runoob.com/kotlin/kotlin-basic-syntax.html
 */
class BasicGrammar {

    @Test
    fun test() {
        var a = sum(1, 2)
        var b = sum1(2, 3)
        var c = sum2(4, 5)
        println("函数定义:")
        println(a)
        println(b)
        println(c)
        printSum(6, 7)
        printSum1(8, 9)
    }

    /***************函数定义******************/

    /**
     * 函数定义使用关键字 fun，参数格式为：参数 : 类型
     */
    fun sum(a: Int, b: Int): Int {// Int 参数，返回值 Int
        return a + b
    }

    /**
     * 表达式作为函数体，返回类型自动推断
     */
    fun sum1(a: Int, b: Int) = a + b

    public fun sum2(a: Int, b: Int): Int = a + b  //public方法则必须明确写出返回类型

    /**
     * 无返回值的函数(类似Java中的void)
     */
    fun printSum(a: Int, b: Int): Unit {
        println(a + b)
    }

    //如果是返回Unit类型，则可以省略(对于public方法也是这样的)
    public fun printSum1(a: Int, b: Int) {
        println(a + b)
    }

    /**
     * 可变长参数函数
     * 函数的变长参数可以用 vararg 关键字进行标识：
     */
    fun vars(vararg v: Int) {
        print("可变长参数函数:")
        for (vt in v) {
            print(vt)
        }
        println()
    }

    @Test
    fun test1() {//如果方法名是main的话会优先执行
        vars(1, 2, 3, 4, 5, 6)//输出123456
    }

    /**
     * lambda(匿名函数)
     * lambda表达式使用实例：
     */
    @Test
    fun tset2() {
        val sumLaubda: (Int, Int) -> Int = { x, y -> x + y }
        print("lambda(匿名函数):")
        println(sumLaubda(3, 3))//输出6
    }

    /***************定义常量和变量******************/
    /**
     * 可变变量定义：var关键字
     * var <标识符> : <类型> = <初始值>
     * 不可变变量定义：val关键字，只能赋值一次的变量(类似Java中final修饰的变量)
     * val <标识符> : <类型> = <初始化值>
     * 常量和变量都可以没有初始值，但是在引用前必须初始化
     *  编译器支持自动类型判断，即声明时可以不指定类型，由编译器判断。
     */

    val a: Int = 1
    val b = 1   // 系统自动推断变量类型为Int

    var x = 5   //系统自动推断变量类型为Int

    /***************注释(与 Java 不同, Kotlin 中的块注释允许嵌套。)******************/
    // 这是一个单行注释

    /* 这是一个多行的
         块注释。 */

    /***************字符串模板******************/

    /**
     * $表示一个变量名或者变量值
     * $varName表示变量值
     * ${varName.fun()}表示变量的方法返回值
     */
    var c = 111
    //模板中的简单名称：
    val s1 = "a is $c"

    var d = 222
    //模板中的任意表达式：
    val s2 = "${s1.replace("is", "was")},but now is $d"

    @Test
    fun test3() {
        println(s1)
        println(s2)
    }

    /***************Null检查机制******************/
    /**
     * Kotlin的空安全设计对于声明可为空的参数，在使用时要进行空判断处理，有两种处理方法：
     * 一、字段后加!!像Java一样抛出空异常
     * 二、另一种字段后加?可不做处理返回值为null或者配合?:做空判断处理
     */
    //类型后面加?表示可为空
    var age: String? = null
    //抛出空指针异常
    /*kotlin.KotlinNullPointerException
	  at com.encdata.kotlin.BasicGrammar.<init>(BasicGrammar.kt:132)*/
//    val ages = age!!.toInt()
    //不做处理返回null
    val ages1 = age?.toInt()
    //age为空返回-1
    val ages2 = age?.toInt() ?: -1

    //当一个引用可能为 null 值时, 对应的类型声明必须明确地标记为可为 null。
    //当 str 中的字符串内容不是一个整数时, 返回 null:
    fun parseInt(str: String): Int? {
        return str.toInt()
    }

    //以下实例演示如何使用一个返回值可为 null 的函数:
    fun test5(args: Array<String>) {
        if (args.size < 2) {
            println("Two integers expected")
            return
        }
        val x = parseInt(args[0])
        val y = parseInt(args[1])
        // 直接使用 `x * y` 会导致错误, 因为它们可能为 null.
        if (x != null && y != null) {
            // 在进行过 null 值检查之后, x 和 y 的类型会被自动转换为非 null 变量
            println(x * y)
        }
    }

    @Test
    fun test4() {
        println("Null检查机制:")
        println(age)
        println(ages1)
        println(ages2)
        println(parseInt("66666"))
        test5(arrayOf("1", "2", "3"))
    }

    /***************类型检测及自动类型转换******************/
    /**
     * 我们可以使用 is 运算符检测一个表达式是否某类型的一个实例(类似于Java中的instanceof关键字)。
     */
    fun getStringLength(obj: Any): Int? {
        if (obj is String) {
            //做过类型判断以后，obj会被系统自动转换为String类型
            return obj.length
        }
        //在这里还有一种写法，与java中的instanceof不同，使用!is
        if (obj !is String) {
            println("notString")
        }
        //这里的obj依然时Any类型的引用
        return null
    }

    fun getStringLength1(obj: Any): Int? {
        if (obj !is String)
            return null
        //在这个分支中，obj的类型会自动被转换成String
        return obj.length
    }

    fun getStringLength2(obj: Any): Int? {
        // 在 `&&` 运算符的右侧, `obj` 的类型会被自动转换为 `String`
        if (obj is String && obj.length > 0)
            return obj.length
        return null
    }

    @Test
    fun test6() {
        println("类型检测及自动类型转换")
        var a = "你好吗啊啊啊啊啊啊"
        var c = getStringLength(a)
        var d = getStringLength1(a)
        var e = getStringLength2(a)
        println(a + "的长度为:" + c + "另一种算法:" + d + "第二种算法:" + e)
        getStringLength(122)
    }

    /***************区间******************/

    /**
     * 区间表达式由具有操作符形式..的rangeTo函数辅以in和!in形成
     * 区间是为任何可比较类型定义的，但对于整型原生类型，它有一个优化的实现。以下是使用区间的一些示例
     */

    fun block() {
        for (i in 1..4) {
            print(i)
        }
        println()
        for (i in 4..1) {
            print(i)
        }
//        if (i in 1..10) {
//            println()
//        }
    }

    @Test
    fun blockTest() {
        println("区间测试:")
        block()
    }
}