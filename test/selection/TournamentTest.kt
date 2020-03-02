package selection

import exceptions.population.NotAnIndividualException
import fitness.Fitness
import individual.BaseIndividual
import individual.Individual
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import population.PopulationFactory
import java.util.stream.Stream
import kotlin.random.Random
import kotlin.test.assertEquals

class TournamentTest {

    companion object {

        @JvmStatic
        fun popSizeValueProvider(): Stream<Arguments> = Stream.of(
            Arguments.of(0),
            Arguments.of(1),
            Arguments.of(10),
            Arguments.of(100)
        )

        class MyIndividual(var l: List<Int> = listOf(1)): Individual()

    }

    @Test
    fun `initialize small tourn size`() { Tournament<MyIndividual>(1) }

    @Test
    fun `initialize ok tourn size`() { Tournament<MyIndividual>(3) }

    @ParameterizedTest
    @MethodSource("popSizeValueProvider")
    fun `select correct num`(size: Int) {
        val pop = PopulationFactory { MyIndividual() }.spawn(1000)
        pop.forEach { it.fitness.value = Random.nextDouble() }
        val select = Tournament<MyIndividual>(3)
        assertEquals(select(pop, size).size, size)
    }
}
