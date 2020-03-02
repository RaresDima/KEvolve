package population

import exceptions.population.NotAnIndividualException
import fitness.Fitness
import individual.BaseIndividual
import individual.Individual
import individual.MultiObjectiveIndividual
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals

class PopulationFactoryTest {

    companion object {

        @JvmStatic
        fun popSizeValueProvider(): Stream<Arguments> = Stream.of(
            Arguments.of(0),
            Arguments.of(1),
            Arguments.of(10),
            Arguments.of(1000)
        )

        class MyIndividual: Individual()
        class MyMultiObjectiveIndividual: MultiObjectiveIndividual()

        class MyNotAnIndividual: BaseIndividual<Fitness>()

    }

    @Test
    fun `initialize 1 single-objective`() { PopulationFactory(::MyIndividual).spawn() }

    @ParameterizedTest
    @MethodSource("popSizeValueProvider")
    fun `initialize n single-objective`(size: Int) = assertEquals(PopulationFactory(::MyIndividual).spawn(size).size, size)


    @Test
    fun `initialize 1 multi-objective`() { PopulationFactory(::MyMultiObjectiveIndividual).spawn() }

    @ParameterizedTest
    @MethodSource("popSizeValueProvider")
    fun `initialize n multi-objective`(size: Int) = assertEquals(PopulationFactory(::MyMultiObjectiveIndividual).spawn(size).size, size)


    @Test
    fun `initialize 1 non_individual`() = assertThrows<NotAnIndividualException> { PopulationFactory(::MyNotAnIndividual).spawn() }

    @ParameterizedTest
    @MethodSource("popSizeValueProvider")
    fun `initialize n non-individual`(size: Int) = assertThrows<NotAnIndividualException> { PopulationFactory(::MyNotAnIndividual).spawn(size) }

}