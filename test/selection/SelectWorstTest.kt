package selection

import exceptions.selection.PopulationTooSmallException
import exceptions.selection.SelectionTooSmallException
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

class SelectWorstTest {

    companion object {

        @JvmStatic
        fun popSizeValueProvider(): Stream<Arguments> = Stream.of(
            Arguments.of(1),
            Arguments.of(10),
            Arguments.of(100)
        )

        class MyIndividual(var l: List<Int> = listOf(1)): Individual()

    }

    @Test
    fun initialize() { SelectWorst() }

    @ParameterizedTest
    @MethodSource("popSizeValueProvider")
    fun `select correct num`(size: Int) {
        val pop = PopulationFactory { MyIndividual() }.spawn(1000)
        pop.forEach { it.fitness.value = Random.nextDouble() }
        val select = SelectWorst()
        assertEquals(select(pop, size).size, size)
    }

    @Test
    fun `select 0`() {
        val pop = PopulationFactory { MyIndividual() }.spawn(1000)
        pop.forEach { it.fitness.value = Random.nextDouble() }
        val select = SelectWorst()
        assertThrows<SelectionTooSmallException> { select(pop, 0) }
    }

    @Test
    fun `select from small pop`() {
        val pop = PopulationFactory { MyIndividual() }.spawn(3)
        pop.forEach { it.fitness.value = Random.nextDouble() }
        val select = SelectWorst()
        assertThrows<PopulationTooSmallException> { select(pop, 5) }
    }
}
