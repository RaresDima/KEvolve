package fitness

import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals

internal class FitnessTest {

    companion object {

        @JvmStatic
        fun fitnessValueProvider(): Stream<Arguments> = Stream.of(
            Arguments.of(Double.NEGATIVE_INFINITY),
            Arguments.of(-Double.MAX_VALUE),
            Arguments.of(-1.0),
            Arguments.of(-Double.MIN_VALUE),
            Arguments.of(-0.0),
            Arguments.of(Double.MIN_VALUE),
            Arguments.of(+1.0),
            Arguments.of(Double.MAX_VALUE),
            Arguments.of(Double.POSITIVE_INFINITY)
        )

        @JvmStatic
        fun fitnessComparisonValueProvider(): Stream<Arguments> = Stream.of(

            Arguments.of(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, 0),
            Arguments.of(Double.NEGATIVE_INFINITY, -Double.MAX_VALUE, -1),
            Arguments.of(Double.NEGATIVE_INFINITY, -1.0, -1),
            Arguments.of(Double.NEGATIVE_INFINITY, -Double.MIN_VALUE, -1),
            Arguments.of(Double.NEGATIVE_INFINITY, 0.0, -1),
            Arguments.of(Double.NEGATIVE_INFINITY, Double.MIN_VALUE, -1),
            Arguments.of(Double.NEGATIVE_INFINITY, +1.0, -1),
            Arguments.of(Double.NEGATIVE_INFINITY, Double.MAX_VALUE, -1),
            Arguments.of(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, -1),

            Arguments.of(-Double.MAX_VALUE, Double.NEGATIVE_INFINITY, +1),
            Arguments.of(-Double.MAX_VALUE, -Double.MAX_VALUE, 0),
            Arguments.of(-Double.MAX_VALUE, -1.0, -1),
            Arguments.of(-Double.MAX_VALUE, -Double.MIN_VALUE, -1),
            Arguments.of(-Double.MAX_VALUE, 0.0, -1),
            Arguments.of(-Double.MAX_VALUE, Double.MIN_VALUE, -1),
            Arguments.of(-Double.MAX_VALUE, +1.0, -1),
            Arguments.of(-Double.MAX_VALUE, Double.MAX_VALUE, -1),
            Arguments.of(-Double.MAX_VALUE, Double.POSITIVE_INFINITY, -1),

            Arguments.of(-1.0, Double.NEGATIVE_INFINITY, +1),
            Arguments.of(-1.0, -Double.MAX_VALUE, +1),
            Arguments.of(-1.0, -1.0, 0),
            Arguments.of(-1.0, -Double.MIN_VALUE, -1),
            Arguments.of(-1.0, 0.0, -1),
            Arguments.of(-1.0, Double.MIN_VALUE, -1),
            Arguments.of(-1.0, +1.0, -1),
            Arguments.of(-1.0, Double.MAX_VALUE, -1),
            Arguments.of(-1.0, Double.POSITIVE_INFINITY, -1),

            Arguments.of(-Double.MIN_VALUE, Double.NEGATIVE_INFINITY, +1),
            Arguments.of(-Double.MIN_VALUE, -Double.MAX_VALUE, +1),
            Arguments.of(-Double.MIN_VALUE, -1.0, +1),
            Arguments.of(-Double.MIN_VALUE, -Double.MIN_VALUE, 0),
            Arguments.of(-Double.MIN_VALUE, 0.0, -1),
            Arguments.of(-Double.MIN_VALUE, Double.MIN_VALUE, -1),
            Arguments.of(-Double.MIN_VALUE, +1.0, -1),
            Arguments.of(-Double.MIN_VALUE, Double.MAX_VALUE, -1),
            Arguments.of(-Double.MIN_VALUE, Double.POSITIVE_INFINITY, -1),

            Arguments.of(0.0, Double.NEGATIVE_INFINITY, +1),
            Arguments.of(0.0, -Double.MAX_VALUE, +1),
            Arguments.of(0.0, -1.0, +1),
            Arguments.of(0.0, -Double.MIN_VALUE, +1),
            Arguments.of(0.0, 0.0, 0),
            Arguments.of(0.0, Double.MIN_VALUE, -1),
            Arguments.of(0.0, +1.0, -1),
            Arguments.of(0.0, Double.MAX_VALUE, -1),
            Arguments.of(0.0, Double.POSITIVE_INFINITY, -1),

            Arguments.of(Double.MIN_VALUE, Double.NEGATIVE_INFINITY, +1),
            Arguments.of(Double.MIN_VALUE, -Double.MAX_VALUE, +1),
            Arguments.of(Double.MIN_VALUE, -1.0, +1),
            Arguments.of(Double.MIN_VALUE, -Double.MIN_VALUE, +1),
            Arguments.of(Double.MIN_VALUE, 0.0, +1),
            Arguments.of(Double.MIN_VALUE, Double.MIN_VALUE, 0),
            Arguments.of(Double.MIN_VALUE, +1.0, -1),
            Arguments.of(Double.MIN_VALUE, Double.MAX_VALUE, -1),
            Arguments.of(Double.MIN_VALUE, Double.POSITIVE_INFINITY, -1),


            Arguments.of(+1.0, Double.NEGATIVE_INFINITY, +1),
            Arguments.of(+1.0, -Double.MAX_VALUE, +1),
            Arguments.of(+1.0, -1.0, +1),
            Arguments.of(+1.0, -Double.MIN_VALUE, +1),
            Arguments.of(+1.0, 0.0, +1),
            Arguments.of(+1.0, Double.MIN_VALUE, +1),
            Arguments.of(+1.0, +1.0, 0),
            Arguments.of(+1.0, Double.MAX_VALUE, -1),
            Arguments.of(+1.0, Double.POSITIVE_INFINITY, -1),

            Arguments.of(Double.MAX_VALUE, Double.NEGATIVE_INFINITY, +1),
            Arguments.of(Double.MAX_VALUE, -Double.MAX_VALUE, +1),
            Arguments.of(Double.MAX_VALUE, -1.0, +1),
            Arguments.of(Double.MAX_VALUE, -Double.MIN_VALUE, +1),
            Arguments.of(Double.MAX_VALUE, 0.0, +1),
            Arguments.of(Double.MAX_VALUE, Double.MIN_VALUE, +1),
            Arguments.of(Double.MAX_VALUE, +1.0, +1),
            Arguments.of(Double.MAX_VALUE, Double.MAX_VALUE, 0),
            Arguments.of(Double.MAX_VALUE, Double.POSITIVE_INFINITY, -1),

            Arguments.of(Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, +1),
            Arguments.of(Double.POSITIVE_INFINITY, -Double.MAX_VALUE, +1),
            Arguments.of(Double.POSITIVE_INFINITY, -1.0, +1),
            Arguments.of(Double.POSITIVE_INFINITY, -Double.MIN_VALUE, +1),
            Arguments.of(Double.POSITIVE_INFINITY, 0.0, +1),
            Arguments.of(Double.POSITIVE_INFINITY, -Double.MIN_VALUE, +1),
            Arguments.of(Double.POSITIVE_INFINITY, +1.0, +1),
            Arguments.of(Double.POSITIVE_INFINITY, Double.MAX_VALUE, +1),
            Arguments.of(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 0)
        )

    }


    @Test
    fun `value uninitialized`() = assertEquals(Fitness().value, Double.NEGATIVE_INFINITY)

    @ParameterizedTest
    @MethodSource("fitnessValueProvider")
    fun `value initialized`(v: Double) = assertEquals(Fitness().apply { value = v }.value, v)


    @Test
    fun `valid uninitialized`() = assertEquals(Fitness().valid, false)

    @ParameterizedTest
    @MethodSource("fitnessValueProvider")
    fun `valid initialized`(v: Double) = assertEquals(Fitness().apply { value = v }.valid, true)

    @ParameterizedTest
    @MethodSource("fitnessValueProvider")
    fun invalidate(v: Double) {
        val fit = Fitness().apply { value = v }
        assertEquals(fit.valid, true)
        fit.invalidate()
        assertEquals(fit.valid, false)
    }


    @ParameterizedTest
    @MethodSource("fitnessComparisonValueProvider")
    fun compareTo(v1: Double, v2: Double, result: Int) {
        val fit1 = Fitness()
        val fit2 = Fitness()
        fit1.value = v1
        fit2.value = v2
        assertEquals(fit1.compareTo(fit2), result)
    }

    @ParameterizedTest
    @MethodSource("fitnessComparisonValueProvider")
    fun equals(v1: Double, v2: Double, result: Int) {
        val fit1 = Fitness()
        val fit2 = Fitness()
        fit1.value = v1
        fit2.value = v2
        assertEquals(fit1 == fit2, v1 == v2)
    }


    @ParameterizedTest
    @MethodSource("fitnessValueProvider")
    fun `copy produces same value`(f: Double) {
        val fit = Fitness()
        fit.value = f
        assert(fit == fit.copy())
    }

    @ParameterizedTest
    @MethodSource("fitnessValueProvider")
    fun `copy produces different object`(f: Double) {
        val fit = Fitness()
        fit.value = f
        assert(fit !== fit.copy())
    }

}
