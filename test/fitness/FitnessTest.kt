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
            Arguments.of(Double.MIN_VALUE),
            Arguments.of(-1.0),
            Arguments.of(-0.000001),
            Arguments.of(-0.0),
            Arguments.of(+0.000001),
            Arguments.of(+1.0),
            Arguments.of(Double.MAX_VALUE),
            Arguments.of(Double.POSITIVE_INFINITY)
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

}
