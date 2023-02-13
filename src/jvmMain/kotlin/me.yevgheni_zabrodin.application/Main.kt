private const val HELP_COMMAND = "/help"

private const val EXIT_COMMAND = "/exit"

enum class TokenType {
    PLUS, MINUS, NUMBER, EQUAL, VARIABLE
}

data class Token(val type: TokenType, val value: String)

//fun main() {
//    val evaluator = Evaluator()
//    println(evaluator.evaluate("a = 2"))
//    println(evaluator.evaluate("bar = 3"))
//    println(evaluator.evaluate("x = bar"))
//    println(evaluator.evaluate("7 + 1 + 4 + a - bar"))
//    println(evaluator.evaluate("5 - x"))
////    println(Evaluator().evaluate("223+"))
//
//    while (true) {
//        val data = readln().trim()
//
//        when {
//            data.isEmpty() -> {
//                continue
//            }
//
//            data == HELP_COMMAND -> {
//                println("The program calculates the sum of numbers")
//                continue
//            }
//
//            data == EXIT_COMMAND -> {
//                println("Bye!")
//                break
//            }
//
//            data.startsWith('/') -> {
//                println("Unknown command")
//            }
//
//            data.isNotEmpty() -> {
//                val result = evaluator.evaluate(data.trim())
//                if (result != null) println(result)
//            }
//        }
//    }
//}
