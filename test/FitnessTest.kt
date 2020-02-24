import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.random.Random
import kotlin.test.assertEquals

internal class FitnessTest {

    companion object {

        @JvmStatic
        fun ctorWeightsAndValuesProvider(): Stream<Arguments> = Stream.of(

            Arguments.of(listOf(1.0, 0.5, 0.1, -0.3, 0.7)),
            Arguments.of(listOf(0.0, -0.001, 0.2 + 0.3)),

            Arguments.of(
                Random(System.currentTimeMillis())
                    .let { random ->
                        List(
                            size = random.nextInt(100, 1000),
                            init = { random.nextDouble() }
                        )
                    }
            )

        )

    }


    @ParameterizedTest
    @MethodSource("ctorWeightsAndValuesProvider")
    fun getWeights(w: List<Double>) = assertEquals(Fitness(w).weights, w)


    @Test
    fun `getValues uninitialized`() = assertEquals(Fitness(List(5) { 0.0 }).values,
                                                   List(5) { Double.NEGATIVE_INFINITY})

    @ParameterizedTest
    @MethodSource("ctorWeightsAndValuesProvider")
    fun `get and set values initialized`(v: List<Double>) {
        val fit = Fitness(List(v.size) { 0.0 }).apply { values = v }
        assertEquals(fit.values, v)
    }


    @ParameterizedTest
    @MethodSource("ctorWeightsAndValuesProvider")
    fun weightedValues(wv: List<Double>) {
        val fit = Fitness(wv).apply { values = wv }
        assertEquals(
            fit.weightedValues(),
            (0..wv.lastIndex).map { i -> wv[i] * wv[i] }
        )
    }

    @ParameterizedTest
    @MethodSource("ctorWeightsAndValuesProvider")
    fun weightedValue(wv: List<Double>) {
        val fit = Fitness(wv).apply { values = wv }
        assertEquals(
            fit.weightedValue(),
            (0..wv.lastIndex).sumByDouble { i -> wv[i] * wv[i] }
        )
    }


    @ParameterizedTest
    @MethodSource("ctorWeightsAndValuesProvider")
    fun `valid uninitialized`(w: List<Double>) = assertEquals(Fitness(w).valid, false)

    @ParameterizedTest
    @MethodSource("ctorWeightsAndValuesProvider")
    fun `valid initialized`(wv: List<Double>) {
        val fit = Fitness(wv).apply { values = wv }
        assertEquals(fit.valid, true)
    }

    @ParameterizedTest
    @MethodSource("ctorWeightsAndValuesProvider")
    fun invalidate(wv: List<Double>) {
        val fit = Fitness(wv).apply { values = wv }
        assertEquals(fit.valid, true)
        fit.invalidate()
        assertEquals(fit.valid, false)
    }

}
