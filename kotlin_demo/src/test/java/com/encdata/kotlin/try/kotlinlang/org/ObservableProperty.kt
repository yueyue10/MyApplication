package com.encdata.kotlin.`try`.kotlinlang.org

/**
 * Created by zhaoyuejun on 2018/9/17.
 */
import org.junit.Test
import kotlin.properties.Delegates

class ObservableProperty {
    class User {
        var name: String by Delegates.observable("no name") { d, old, new ->
            println("$old - $new")
        }
    }

    @Test
    fun main() {
        val user = User()
        user.name = "Carl"
    }
}
