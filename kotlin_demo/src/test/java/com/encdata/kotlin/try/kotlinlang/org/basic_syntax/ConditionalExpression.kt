import org.junit.Test

/**
 * `if` is an expression, i.e. it returns a value.
 * Therefore there is no ternary operator (condition ? then : else),
 * because ordinary `if` works fine in this role.
 * See http://kotlinlang.org/docs/reference/control-flow.html#if-expression
 * Created by zhaoyuejun on 2018/9/17.
 */
class ConditionalExpression {
    @Test
    fun main() {
        var args = arrayListOf<String>()
        args.add("1")
        args.add("2")
        args.add("3")
        println(max(args[0].toInt(), args[1].toInt()))
    }

    private fun max(a: Int, b: Int) = if (a > b) a else b
}