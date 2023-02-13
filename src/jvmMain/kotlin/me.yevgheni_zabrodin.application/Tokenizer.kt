class Tokenizer {

    private fun extractByFunction(value: String, index: Int, f: (Char) -> Boolean): Pair<Int, String> {
        var currentIndex = index
        var buffer = ""
        var element = value[index]

        do {
            buffer += element
            ++currentIndex

            if (currentIndex == value.length) break

            element = value[currentIndex]
        } while (f(element))

        return currentIndex to buffer
    }

    private fun extractNumber(value: String, index: Int): Pair<Int, String> {
        return extractByFunction(value, index, fun(c: Char): Boolean { return c.isDigit() })
    }

    private fun extractVariable(value: String, index: Int): Pair<Int, String> {
        return extractByFunction(value, index, fun(c: Char): Boolean { return c.isLetter() })
    }

    fun tokenize(value: String): MutableList<Token> {
        val tokens = mutableListOf<Token>()
        var i = 0
        while (i < value.length) {
            val it = value[i]
            if (it.isDigit()) {
                val result = extractNumber(value, i)
                i = result.first
                tokens.add(
                    Token(TokenType.NUMBER, result.second)
                )
                continue
            } else if (it == '+') {
                tokens.add(
                    Token(TokenType.PLUS, "+")
                )
                i++
                continue
            } else if (it == '-') {
                tokens.add(
                    Token(TokenType.MINUS, "-")
                )
                i++
                continue
            } else if (it == '=') {
                tokens.add(
                    Token(TokenType.EQUAL, "=")
                )
                i++
                continue
            } else if (it.isLetter()) {
                val result = extractVariable(value, i)
                i = result.first
                tokens.add(
                    Token(TokenType.VARIABLE, result.second)
                )
                i++
                continue
            } else {
                tokens.clear()
                break
            }
        }

        // workaround with negative numbers
        val type = tokens.first().type
        if (type == TokenType.MINUS || type == TokenType.PLUS) {
            tokens.add(0, Token(TokenType.NUMBER, "0"))
        }

        // workaround with invalid expressions
        if (tokens.size == 2) {
            if (type == TokenType.PLUS) {
                tokens.add(0, Token(TokenType.NUMBER, "0"))
            } else {
                println("Invalid expression")
                tokens.clear()
            }
        }

        return tokens
    }
}