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

        class MyIndividual(var l: List<Int> = listOf(1)): Individual()
        class MyMultiObjectiveIndividual(var l: List<Int> = listOf(1)): MultiObjectiveIndividual()

        class MyNotAnIndividual: BaseIndividual<Fitness>()

    }

    @Test
    fun `initialize 1 single-objective`() { PopulationFactory { MyIndividual() }.spawn() }

    @Test
    fun `initialize 1 multi-objective`() { PopulationFactory { MyMultiObjectiveIndividual() }.spawn() }

    @Test
    fun `initialize 1 non_individual`() = assertThrows<NotAnIndividualException> { PopulationFactory(::MyNotAnIndividual).spawn() }


    @ParameterizedTest
    @MethodSource("popSizeValueProvider")
    fun `initialize n single-objective`(size: Int) = assertEquals(PopulationFactory { MyIndividual() }.spawn(size).size, size)

    @ParameterizedTest
    @MethodSource("popSizeValueProvider")
    fun `initialize n multi-objective`(size: Int) = assertEquals(PopulationFactory { MyMultiObjectiveIndividual() }.spawn(size).size, size)

    @ParameterizedTest
    @MethodSource("popSizeValueProvider")
    fun `initialize n non-individual`(size: Int) = assertThrows<NotAnIndividualException> { PopulationFactory(::MyNotAnIndividual).spawn(size) }


    @Test
    fun `clone 1 single-objective contents equal`() {
        val pf = PopulationFactory { MyIndividual() }
        val ind = pf.spawn()
        val clone = pf.clone(ind)
        assertEquals(ind.l, clone.l)
    }

    @Test
    fun `clone 1 single-objective objects different`() {
        val pf = PopulationFactory { MyIndividual() }
        val ind = pf.spawn()
        val clone = pf.clone(ind)
        assert(ind !== clone)
        assert(ind.l !== clone.l)
    }

    @ParameterizedTest
    @MethodSource("popSizeValueProvider")
    fun `clone n single-objective contents equal`(size: Int) {
        val pf = PopulationFactory { MyIndividual() }
        val pop = pf.spawn(size)
        val popClone = pf.clone(pop)
        pop.zip(popClone).forEach { (ind, clone) -> assertEquals(ind.l, clone.l) }
    }

    @ParameterizedTest
    @MethodSource("popSizeValueProvider")
    fun `clone n single-objective objects different`(size: Int) {
        val pf = PopulationFactory { MyIndividual() }
        val pop = pf.spawn(size)
        val popClone = pf.clone(pop)
        pop.zip(popClone).forEach { (ind, clone) -> assert(ind.l !== clone.l) }
        pop.zip(popClone).forEach { (ind, clone) -> assert(ind !== clone) }
    }


    @Test
    fun `clone 1 multi-objective contents equal`() {
        val pf = PopulationFactory { MyMultiObjectiveIndividual() }
        val ind = pf.spawn()
        val clone = pf.clone(ind)
        assertEquals(ind.l, clone.l)
    }

    @Test
    fun `clone 1 multi-objective objects different`() {
        val pf = PopulationFactory { MyMultiObjectiveIndividual() }
        val ind = pf.spawn()
        val clone = pf.clone(ind)
        assert(ind !== clone)
        assert(ind.l !== clone.l)
    }

    @ParameterizedTest
    @MethodSource("popSizeValueProvider")
    fun `clone n multi-objective contents equal`(size: Int) {
        val pf = PopulationFactory { MyMultiObjectiveIndividual() }
        val pop = pf.spawn(size)
        val popClone = pf.clone(pop)
        pop.zip(popClone).forEach { (ind, clone) -> assertEquals(ind.l, clone.l) }
    }

    @ParameterizedTest
    @MethodSource("popSizeValueProvider")
    fun `clone n multi-objective objects different`(size: Int) {
        val pf = PopulationFactory { MyMultiObjectiveIndividual() }
        val pop = pf.spawn(size)
        val popClone = pf.clone(pop)
        pop.zip(popClone).forEach { (ind, clone) -> assert(ind.l !== clone.l) }
        pop.zip(popClone).forEach { (ind, clone) -> assert(ind !== clone) }
    }

}