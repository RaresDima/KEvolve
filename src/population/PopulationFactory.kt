package population

import fitness.MultiObjectiveFitness
import fitness.Fitness
import individual.BaseIndividual
import individual.Individual
import individual.MultiObjectiveIndividual
import java.lang.IllegalStateException

/**
 * Handles instantiation of individuals to be used in single-objective
 * optimization.
 *
 * @param INDIVIDUAL The class representing an individual.
 *
 * @property createIndividual
 *  A function that takes no arguments and creates an individual.
 *  I.e. an instance of [INDIVIDUAL].
 *
 * @constructor
 *  Stores the function (passed as a parameter) that will be used to instantiate
 *  the individuals.
 */
class PopulationFactory<out INDIVIDUAL: BaseIndividual<*>>(val createIndividual: () -> INDIVIDUAL) {

    /**
     * Spawns (instantiates) the specified number of individuals.
     *
     * @param n The number of individuals to create.
     *
     * @return A [List] with the created individuals.
     */
    fun spawn(n: Int): List<INDIVIDUAL> =
        List(n) {
            createIndividual().apply {
                when (this) {
                    is Individual -> fitness = Fitness()
                    is MultiObjectiveIndividual -> fitness = MultiObjectiveFitness(listOf(1.0))
                    else -> throw IllegalStateException("yay")
                }

            }
        }
}
