package fitness

import exceptions.IncorrectFitnessValuesLengthException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.random.Random
import kotlin.test.assertEquals

internal class MultiObjectiveFitnessTest {

    companion object {

        @JvmStatic
        fun ctorWeightsAndValuesProvider(): Stream<Arguments> = Stream.of(
            Arguments.of(listOf(1.0, 0.5, 0.1, -0.3, 0.7)),
            Arguments.of(listOf(0.0, -0.001, 0.2 + 0.3)),
            Arguments.of(List(size = Random.nextInt(100, 1000), init = { Random.nextDouble() }))
        )

    }


    @ParameterizedTest
    @MethodSource("ctorWeightsAndValuesProvider")
    fun `get weights`(w: List<Double>) = assertEquals(MultiObjectiveFitness(w).weights, w)


    @Test
    fun `get values uninitialized`() = assertEquals(
        MultiObjectiveFitness(List(5) { 0.0 }).values,
                                                   List(5) { Double.NEGATIVE_INFINITY})

    @ParameterizedTest
    @MethodSource("ctorWeightsAndValuesProvider")
    fun `get and set values initialized`(v: List<Double>) {
        val fit = MultiObjectiveFitness(List(v.size) { 0.0 }).apply { values = v }
        assertEquals(fit.values, v)
    }

    @Test
    fun `set values incorrect length`() {
        assertThrows<IncorrectFitnessValuesLengthException> {
            MultiObjectiveFitness(List(5) { 0.0 })
                .apply { values = List(10) { 0.0 } }
        }
    }


    @ParameterizedTest
    @MethodSource("ctorWeightsAndValuesProvider")
    fun weightedValues(wv: List<Double>) {
        val fit = MultiObjectiveFitness(wv).apply { values = wv }
        assertEquals(
            fit.weightedValues(),
            (0..wv.lastIndex).map { i -> wv[i] * wv[i] }
        )
    }

    @ParameterizedTest
    @MethodSource("ctorWeightsAndValuesProvider")
    fun weightedValue(wv: List<Double>) {
        val fit = MultiObjectiveFitness(wv).apply { values = wv }
        assertEquals(
            fit.weightedValue(),
            (0..wv.lastIndex).sumByDouble { i -> wv[i] * wv[i] }
        )
    }


    @ParameterizedTest
    @MethodSource("ctorWeightsAndValuesProvider")
    fun `valid uninitialized`(w: List<Double>) = assertEquals(MultiObjectiveFitness(w).valid, false)

    @ParameterizedTest
    @MethodSource("ctorWeightsAndValuesProvider")
    fun `valid initialized`(wv: List<Double>) {
        val fit = MultiObjectiveFitness(wv).apply { values = wv }
        assertEquals(fit.valid, true)
    }

    @ParameterizedTest
    @MethodSource("ctorWeightsAndValuesProvider")
    fun invalidate(wv: List<Double>) {
        val fit = MultiObjectiveFitness(wv).apply { values = wv }
        assertEquals(fit.valid, true)
        fit.invalidate()
        assertEquals(fit.valid, false)
    }

}
