package com.encdata.kotlin.`try`.kotlinlang.org

import org.junit.Test

/**
 * Created by zhaoyuejun on 2018/9/17.
 */
class ReferenceToAFunction{
    @Test
    fun main() {
        val numbers = listOf(1, 2, 3)
        println(numbers.filter(::isOdd))
    }

    fun isOdd(x: Int) = x % 2 != 0
}
