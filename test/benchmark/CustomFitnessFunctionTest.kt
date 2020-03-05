package benchmark

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.random.Random
import kotlin.test.assertEquals

class CustomFitnessFunctionTest {

    companion object {

        @JvmStatic
        fun doubleValueProvider(): Stream<Arguments> = Stream.of(
            Arguments.of(List(10) { 0.0 }),
            Arguments.of(List(1000) { 0.0 }),
            Arguments.of(List(10) { Random.nextDouble(-1000.0, +1000.0) }),
            Arguments.of(List(1000) { Random.nextDouble(-1000.0, +1000.0) })
            )

    }

    @ParameterizedTest
    @MethodSource("doubleValueProvider")
    fun `computes correctly`(x: List<Double>) {
        val f = FitnessFunctionCustom("d" to 3.5, "n" to 13.13) { ind: List<Double>, data ->
            ind.sumByDouble { it / data["d"] as Double + data["n"] as Double }
        }
        assertEquals(f(x), x.sumByDouble { it / 3.5 + 13.13 })
    }
}
