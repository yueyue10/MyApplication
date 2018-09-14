package com.encdata.kotlin

import android.content.Context
import android.util.AttributeSet
import org.junit.Test

/**
 * Created by zhaoyuejun on 2018/9/14.
 */
class ExtendsDemo {
    /**
     * Kotlin 继承
     */
    @Test
    fun main() {
        //一、继承介绍
        basicTest()
        //二、构造函数
        constructionFunction()
        //三、重写
        overrideTest()
        //四、属性重写
        propertyOverride()
    }

    /**
     * 一、继承介绍
     */
    private fun basicTest() {
        //Kotlin 中所有类都继承该 Any 类，它是所有类的超类，对于没有超类型声明的类是默认超类：
        class Example // 从 Any 隐式继承
        /*Any 默认提供了三个函数：
        equals()
        hashCode()
        toString()*/

        /*注意：Any 不是 java.lang.Object。
        如果一个类要被继承，可以使用 open 关键字进行修饰。*/
        open class Base(p: Int)           // 定义基类

        class Derived(p: Int) : Base(p)
    }

    /**
     * 二、构造函数
     */
    private fun constructionFunction() {
        println("二、构造函数")
        constructionFunction1()
        constructionFunction2()
    }
    private fun constructionFunction1() {
        //子类有主构造函数
        /*如果子类有主构造函数， 则基类必须在主构造函数中立即初始化。*/
        open class Person(var name: String, var age: Int)// 基类

        class Student(name: String, age: Int, var no: String, var score: Int) : Person(name, age) //

        // 测试
        fun test() {
            val s = Student("Runoob", 18, "S12346", 89)
            println("学生名： ${s.name}")
            println("年龄： ${s.age}")
            println("学生号： ${s.no}")
            println("成绩： ${s.score}")
        }
        test()
        // 输出结果：
        // 学生名： Runoob
        // 年龄： 18
        // 学生号： S12346
        // 成绩： 89
    }

    private fun constructionFunction2() {
        //子类没有主构造函数
        /*如果子类没有主构造函数，则必须在每一个二级构造函数中用 super 关键字初始化基类，
        或者在代理另一个构造函数。初始化基类时，可以调用基类的不同构造方法。*/
        open class Person(var name: Context, var age: AttributeSet)// 基类

        class StudentB : Person {

            //  constructor(ctx: Context) : super(ctx) {
            //  }

            constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs) {
            }
        }
        /*实例*/
        /**用户基类**/
        open class PersonB(name: String) {
            /**次级构造函数**/
            constructor(name: String, age: Int) : this(name) {
                //初始化
                println("-------基类次级构造函数---------")
            }
        }

        /**子类继承 Person 类**/
        class StudentC : PersonB {
            /**次级构造函数**/
            constructor(name: String, age: Int, no: String, score: Int) : super(name, age) {
                println("-------继承类次级构造函数---------")
                println("学生名： ${name}")
                println("年龄： ${age}")
                println("学生号： ${no}")
                println("成绩： ${score}")
            }
        }

        var s = StudentC("Runoob", 18, "S12345", 89)
        //        输出结果：
        //        -------基类次级构造函数-------- -
        //        -------继承类次级构造函数-------- -
        //        学生名： Runoob
        //        年龄： 18
        //        学生号： S12345
        //        成绩： 89
    }

    /**
     * 三、重写
     */
    private fun overrideTest() {
        println("三、重写")
        //在基类中，使用fun声明函数时，此函数默认为final修饰，不能被子类重写。
        //如果允许子类重写该函数，那么就要手动添加 open 修饰它, 子类重写方法使用 override 关键词：
        /**用户基类**/
        open class Person {
            open fun study() {       // 允许子类重写
                println("我毕业了")
            }
        }

        /**子类继承 Person 类**/
        class Student : Person() {

            override fun study() {    // 重写方法
                println("我在读大学")
            }
        }

        val s = Student()
        s.study()
        //  输出结果为:
        //  我在读大学
        overrideTest1()
    }

    /*如果有多个相同的方法（继承或者实现自其他类，如A、B类），则必须要重写该方法，使用super范型去选择性地调用父类的实现。*/
    open class A {
        open fun f() {
            print("A")
        }

        fun a() {
            print("a")
        }
    }

    interface B {
        fun f() {
            print("B")
        } //接口的成员变量默认是 open 的

        fun b() {
            print("b")
        }
    }

    class C() : A(), B {
        override fun f() {
            super<A>.f()//调用 A.f()
            super<B>.f()//调用 B.f()
        }
    }

    private fun overrideTest1() {
        val c = C()
        c.f()
        /* C 继承自 a() 或 b(), C 不仅可以从 A 或则 B 中继承函数，而且 C 可以继承 A()、B() 中共有的函数。
         此时该函数在中只有一个实现，为了消除歧义，该函数必须调用A()和B()中该函数的实现，并提供自己的实现。*/
        //  输出结果为:
        //  AB
    }

    /**
     * 四、属性重写
     */
    /*属性重写使用 override 关键字，属性必须具有兼容类型，每一个声明的属性都可以通过初始化程序或者getter方法被重写：*/
    open class Foo {
        open val x: Int
            get () {
                return 1
            }
    }

    class Bar0 : Foo() {
        override val x: Int = 1
    }
    /*你可以用一个var属性重写一个val属性，但是反过来不行。
        因为val属性本身定义了getter方法，重写为var属性会在衍生类中额外声明一个setter方法*/

    //你可以在主构造函数中使用 override 关键字作为属性声明的一部分:

    interface Foo1 {
        val count: Int
    }

    class Bar1(override val count: Int) : Foo1

    class Bar2 : Foo1 {
        override var count: Int = 0
    }

    private fun propertyOverride() {
        Foo()
        Bar1(1)
        Bar2()
    }

}