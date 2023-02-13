class Evaluator() {
    val variable = mutableMapOf<String, Int>()

    fun evaluate(value: String): Int? {

        val validator = Validator()
        val prepared = validator.prepare(value)

        if (!validator.validate(prepared)) {
            return null
        }

        val tokensList = Tokenizer().tokenize(prepared)
        var result: Int
        val operation = Operation()
        var currentIndex = -1

        if (tokensList.size == 1) {
            return tokensList.first().value.toInt()
        }

        if (tokensList.isEmpty()) {
            return null
        }

        do {
            ++currentIndex
            val currentToken = tokensList[currentIndex]
            if (currentToken.type == TokenType.NUMBER && operation.getLeft() == null) {
                currentIndex = processNumber(currentIndex, tokensList, operation, currentToken)
            } else if (currentToken.type == TokenType.PLUS || currentToken.type == TokenType.MINUS) {
                operation.run {
                    val operand = currentToken.value
                    ++currentIndex
                    val number = if (currentIndex < tokensList.size) tokensList[currentIndex].value else "0"
                    setOperand(operand.toCharArray().first())
                    setRight(number)
                }
            } else if (currentToken.type == TokenType.VARIABLE) {

                val variable = currentToken.value

                ++currentIndex
                val equal = tokensList[currentIndex]

            }

            result = operation.execute()
            operation.setLeft(result.toString())
        } while (currentIndex < tokensList.size - 1)

        return result
    }

    private fun processNumber(
        index: Int,
        tokensList: MutableList<Token>,
        operation: Operation,
        currentToken: Token
    ): Int {
        var currentIndex = index
        ++currentIndex
        val token = tokensList.getOrElse(currentIndex) { Token(TokenType.PLUS, "+") }

        ++currentIndex
        val right = tokensList.getOrElse(currentIndex) { Token(TokenType.NUMBER, "0") }

        operation.run {
            setLeft(currentToken.value)
            setOperand(token.value.toCharArray().first())
            setRight(right.value)
        }
        return currentIndex
    }

}
