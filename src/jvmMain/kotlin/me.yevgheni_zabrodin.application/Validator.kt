
class Validator() {

    private val stringsThatMustBeReplaced = listOf("--", "++", "+-", "-+", " ")

    fun validate(value: String): Boolean {
        var result = true

        for (element in value) {
            if (!(element.isDigit() || element == '-' || element == '+')) {
                result = false
                println("Invalid expression")
                break
            }
        }
        return result
    }

    fun prepare(value: String): String {

        var prepared = value

        while (prepared.findAnyOf(this.stringsThatMustBeReplaced) != null) {
            prepared = value.replace("--", "+")
            prepared = prepared.replace(Regex("\\++"), "+")
            prepared = prepared.replace("+-", "-")
            prepared = prepared.replace("-+", "-")
            prepared = prepared.replace(" ", "")
            prepared = prepare(prepared)
        }

        return prepared
    }
}
