package benchmark.continuous.singleobjective

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.random.Random
import kotlin.test.assertEquals

class RastriginTest {

    companion object {

        @JvmStatic
        fun rastriginValueProvider(): Stream<Arguments> = Stream.of(
            Arguments.of(List(10) { 0.0 }, true),
            Arguments.of(List(1000) { 0.0 }, true),
            Arguments.of(List(10) { Random.nextDouble(-1000.0, +1000.0) }, false),
            Arguments.of(List(1000) { Random.nextDouble(-1000.0, +1000.0) }, false)
            )

    }

    @ParameterizedTest
    @MethodSource("rastriginValueProvider")
    fun `value initialized`(x: List<Double>, resultIsZero: Boolean) = assertEquals(Rastrigin()(x) == 0.0, resultIsZero)
}
