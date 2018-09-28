import org.junit.Test

/**
 * A reference must be explicitly marked as nullable to be able hold a null.
 * See http://kotlinlang.org/docs/reference/null-safety.html#null-safety
 * Created by zhaoyuejun on 2018/9/17.
 */
class NullChecks{
    // Return null if str does not hold a number
    private fun parseInt(str: String): Int? {
        try {
            return str.toInt()
        } catch (e: NumberFormatException) {
            println("One of the arguments isn't Int")
        }
        return null
    }
    @Test
    fun main() {
        var args = arrayListOf<String>()
        args.add("2")
        args.add("3")
        if (args.size < 2) {
            println("No number supplied");
        } else {
            val x = parseInt(args[0])
            val y = parseInt(args[1])

            // We cannot say 'x * y' now because they may hold nulls
            if (x != null && y != null) {
                print(x * y) // Now we can
            } else {
                println("One of the arguments is null")
            }
        }
    }
}
