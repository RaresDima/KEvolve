package utils.extensions.delegates

import exceptions.utils.extensions.delegates.AlreadyAssignedPropertyAssignmentException
import fitness.Fitness
import fitness.MultiObjectiveFitness
import individual.Individual
import individual.MultiObjectiveIndividual
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class AssignOnceTest {

    companion object {
        class MyIndividual: Individual()
        class MyMultiObjectiveIndividual: MultiObjectiveIndividual()
    }


    @Test
    fun `Fitness uninitialized`() {
        val ind = MyIndividual()
        assertThrows<UninitializedPropertyAccessException>("Individual.fitness has not been set.") {
            ind.fitness
        }
    }

    @Test
    fun `Fitness initialized`() {
        val ind = MyIndividual()
        ind.fitness = Fitness()
        assertThrows<AlreadyAssignedPropertyAssignmentException>("Individual.fitness can only be set once.") {
            ind.fitness = Fitness()
        }
    }


    @Test
    fun `MultiObjectiveFitness uninitialized`() {
        val ind = MyMultiObjectiveIndividual()
        assertThrows<UninitializedPropertyAccessException>("MultiObjectiveIndividual.fitness has not been set.") {
            ind.fitness
        }
    }

    @Test
    fun `MultiObjectiveFitness initialized`() {
        val ind = MyMultiObjectiveIndividual()
        ind.fitness = MultiObjectiveFitness(listOf(0.1, 0.2, 0.3))
        assertThrows<AlreadyAssignedPropertyAssignmentException>("MultiObjectiveIndividual.fitness can only be set once.") {
            ind.fitness = MultiObjectiveFitness(listOf(0.2, 0.4, 0.6))
        }
    }


}
