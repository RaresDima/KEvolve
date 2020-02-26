package population

import individual.BaseIndividual
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals

class PopulationBuilderTest {

    companion object {

        @JvmStatic
        fun popSizeValueProvider(): Stream<Arguments> = Stream.of(
            Arguments.of(0),
            Arguments.of(1),
            Arguments.of(10),
            Arguments.of(1000)
        )

        class Individual: BaseIndividual()

    }

    @ParameterizedTest
    @MethodSource("popSizeValueProvider")
    fun `initialize n`(size: Int) = assertEquals(PopulationBuilder(::Individual).spawn(size).size, size)
}