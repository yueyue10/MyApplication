package com.encdata.kotlin

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {
    @Test
    @Throws(Exception::class)
    fun addition_isCorrect() {
        assertEquals(4, (2 + 2).toLong())
        //Kotlin 基础语法
        BasicGrammar()
        //Kotlin 基本数据类型
        BasicDataType()
        //Kotlin 条件控制
        CaseControl()
        // Kotlin 循环控制
        CirculateControl()
        //Kotlin 类和对象
        ClassAndObject()
        //Kotlin 继承
        ExtendsDemo()
        //Kotlin 接口
        InterfaceDemo()
        //Kotlin 扩展
        ExpandDemo()
        //Kotlin 数据类与密封类
        DataAndSealedClass()
        //Kotlin 泛型
        GenericDemo()
        //Kotlin 枚举类
        EnumDemo()
        //Kotlin 对象表达式
        ObjectExpDeclare()
        //kotlin 委托
        ProxyDemo()
    }

}