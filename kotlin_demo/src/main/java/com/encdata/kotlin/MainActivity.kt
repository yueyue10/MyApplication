package com.encdata.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        process()
    }

    private fun process() {
        println(sum(1, 2))
    }

    private fun sum(a: Int, b: Int): Int {   // Int 参数，返回值 Int
        return a + b
    }
}
