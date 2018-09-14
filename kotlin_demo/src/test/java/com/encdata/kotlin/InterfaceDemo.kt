package com.encdata.kotlin

import org.junit.Test

/**
 * Created by zhaoyuejun on 2018/9/13.
 */

class InterfaceDemo {
    /**
     * Kotlin 接口
     */
    @Test
    fun main() {
        //一、实现接口  一个类或者对象可以实现一个或多个接口。
        interfaceBasic()
        //二、接口中的属性
        interfaceProperty()
        //三、函数重写
        overrideFunction()
    }

    /*Kotlin 接口与 Java 8 类似，使用 interface 关键字定义接口，允许方法有默认实现：*/
    /**
     * 一、实现接口  一个类或者对象可以实现一个或多个接口。
     */
    interface MyInterface {
        fun bar()
        fun foo() {
            // 可选的方法体
            println("foo")
        }
    }

    class Child : MyInterface {
        override fun bar() {
            // 方法体
            println("bar")
        }
    }

    private fun interfaceBasic() {
        println("一、实现接口")
        val c = Child()
        c.foo()
        c.bar()
        //        输出结果为：
        //        foo
        //        bar
    }

    /**
     * 二、接口中的属性
     */
    interface MyInterfaceX {
        var name: String //name 属性, 抽象的
        var age: Int
        fun bar()
        fun foo() {
            // 可选的方法体
            println("foo")
        }
    }

    class ChildX : MyInterfaceX {
        override var name: String = "runoob" //重载属性
        override var age: Int = 1
        override fun bar() {
            // 方法体
            println("bar")
        }
    }

    private fun interfaceProperty() {
        println("二、接口中的属性")
        val c = ChildX()
        c.foo()
        c.bar()
        println(c.name)
        //        输出结果为：
        //        foo
        //        bar
        //        runoob
    }

    /**
     * 三、函数重写
     */
    /*实现多个接口时，可能会遇到同一方法继承多个实现的问题。例如:*/

    interface A {
        fun foo() {
            print("A")
        }   // 已实现

        fun bar()// 未实现，没有方法体，是抽象的
    }

    interface B {
        fun foo() {
            print("B")
        }   // 已实现

        fun bar() {
            print("bar")
        } // 已实现
    }

    class C : A {
        override fun bar() {
            print("bar")
        }   // 重写
    }

    class D : A, B {
        override fun foo() {
            super<A>.foo()
            super<B>.foo()
        }

        override fun bar() {
            super<B>.bar()
        }
    }

    private fun overrideFunction() {
        println("三、函数重写")
        val d = D()
        d.foo()
        d.bar()
        //----
        println()
        var c=C()
        c.bar()
        //        输出结果为：
        //        ABbar
        /*实例中接口 A 和 B 都定义了方法 foo() 和 bar()， 两者都实现了 foo(), B 实现了 bar()。
        因为 C 是一个实现了 A 的具体类，所以必须要重写 bar() 并实现这个抽象方法。
        然而，如果我们从 A 和 B 派生 D，我们需要实现多个接口继承的所有方法，并指明 D 应该如何实现它们。
        这一规则 既适用于继承单个实现（bar()）的方法也适用于继承多个实现（foo()）的方法。*/
    }
}
