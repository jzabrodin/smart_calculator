class Operation {
    private var left: Int? = null
    private var right: Int? = null
    private var operand: Char? = null

    fun setLeft(buffer: String) {
        this.left = buffer.toInt()
    }

    fun setRight(buffer: String) {
        this.right = buffer.toInt()
    }

    fun setOperand(buffer: Char) {
        this.operand = buffer
    }

    fun canExecute(): Boolean {
        return this.left != null && this.right != null && this.operand != null
    }

    fun execute(): Int {
        val result = when (operand) {
            '-' -> {
                this.left?.minus(this.right!!) ?: 0
            }

            '+' -> {
                this.left?.plus(this.right!!) ?: 0
            }

            else -> {
                0
            }
        }

        this.reset()

        return result
    }

    private fun reset() {
        this.left = null
        this.right = null
        this.operand = null
    }

    fun getLeft(): Int? {
        return this.left
    }

    override fun toString(): String {
        return "Operation(left=$left, right=$right, operand=$operand)"
    }
}
