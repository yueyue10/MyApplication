package com.encdata.kotlin.`try`.kotlinlang.org.helloworld

import org.junit.Test

/**
 * Created by zhaoyuejun on 2018/9/17.
 */
/**
 * We declare a package-level function main which returns Unit and takes
 * an Array of strings as a parameter. Note that semicolons are optional.
 */
class SimplestVersion {
    @Test
    fun main() {
        println("Hello, world!")
        val month = "(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)"

        fun getPattern(): String = """\d{2}\ ${month} \d{4}"""
    }

}
