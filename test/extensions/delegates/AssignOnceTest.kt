package extensions.delegates

import fitness.Fitness
import fitness.MultiObjectiveFitness
import individual.BaseIndividual
import individual.BaseMultiObjectiveIndividual
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.lang.IllegalStateException
import java.util.stream.Stream
import kotlin.test.assertEquals

internal class AssignOnceTest {

    companion object {
        class Individual: BaseIndividual()
        class MultiObjectiveIndividual: BaseMultiObjectiveIndividual()
    }


    @Test
    fun `Fitness uninitialized`() {
        val ind = Individual()
        assertThrows<IllegalStateException>("Individual.fitness has not been set.") {
            ind.fitness
        }
    }

    @Test
    fun `Fitness initialized`() {
        val ind = Individual()
        ind.fitness = Fitness()
        assertThrows<IllegalStateException>("Individual.fitness can only be set once.") {
            ind.fitness = Fitness()
        }
    }


    @Test
    fun `MultiObjectiveFitness uninitialized`() {
        val ind = MultiObjectiveIndividual()
        assertThrows<IllegalStateException>("MultiObjectiveIndividual.fitness has not been set.") {
            ind.fitness
        }
    }

    @Test
    fun `MultiObjectiveFitness initialized`() {
        val ind = MultiObjectiveIndividual()
        ind.fitness = MultiObjectiveFitness(listOf(0.1, 0.2, 0.3))
        assertThrows<IllegalStateException>("MultiObjectiveIndividual.fitness can only be set once.") {
            ind.fitness = MultiObjectiveFitness(listOf(0.2, 0.4, 0.6))
        }
    }


}
