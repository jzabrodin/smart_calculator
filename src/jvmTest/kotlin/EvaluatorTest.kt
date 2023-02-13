import kotlin.test.Test
import kotlin.test.assertEquals
import com.github.stefanbirkner.systemlambda.SystemLambda.*


class EvaluatorTest {

    private val testSample: Evaluator = Evaluator()

    @Test
    fun testSum() {
        val expected = 4
        assertEquals(expected, testSample.evaluate("2+2"))
    }

    @Test
    fun testSumTwoAndMore() {
        val expected = 14
        assertEquals(expected, testSample.evaluate("2 + 2 + 10"))
    }

    @Test
    fun testMinusTwo() {
        val expected = 40
        assertEquals(expected, testSample.evaluate("120 - 80"))
    }

    @Test
    fun testMinusTwoAndMore() {
        val expected = 80
        assertEquals(expected, testSample.evaluate("120 - 20 -+ 20"))
    }

    @Test
    fun testPlusAndMinus() {
        val expected = 100
        assertEquals(expected, testSample.evaluate("100 - 20 + 20"))
    }

    @Test
    fun testUnknownVariable() {
        assertEquals(null, testSample.evaluate("a = 100"))
        val output = tapSystemOut {
            assertEquals(null, testSample.evaluate("b = c"))
        }
    }

    @Test
    fun testVariable() {

        val expected = 200
        assertEquals(null, testSample.evaluate("a = 100"))
        assertEquals(null, testSample.evaluate("b = 100"))
        assertEquals(expected, testSample.evaluate("a+  b"))
    }
}