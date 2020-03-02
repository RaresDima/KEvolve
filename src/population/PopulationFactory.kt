package population

import com.rits.cloning.Cloner
import exceptions.population.NotAnIndividualException
import fitness.MultiObjectiveFitness
import fitness.Fitness
import individual.BaseIndividual
import individual.Individual
import individual.MultiObjectiveIndividual

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
 * @property weights
 *  The weights to use for multi-objective optimization. I.e. individuals deriving
 *  [MultiObjectiveIndividual].
 *
 * @constructor
 * @param createIndividual
 *  A function that takes no arguments and creates an individual.
 *  I.e. an instance of [INDIVIDUAL].
 *
 * @param weights The list of weights to use for [MultiObjectiveIndividual]s
 *
 *  Stores the function (passed as a parameter) that will be used to instantiate
 *  the individuals.
 */
class PopulationFactory<INDIVIDUAL: BaseIndividual<*>>(
    val createIndividual: () -> INDIVIDUAL,
    val weights: List<Double>
) {

    /**
     * @param createIndividual
     *  A function that takes no arguments and creates an individual.
     *  I.e. an instance of [INDIVIDUAL].
     */
    constructor(createIndividual: () -> INDIVIDUAL): this(createIndividual, listOf())

    private val cloner = Cloner()

    /**
     * Spawns (instantiates) an individual.
     *
     * @return The created individual.
     *
     * @throws NotAnIndividualException If [INDIVIDUAL] does not inherit an Individual class.
     */
    fun spawn(): INDIVIDUAL =
        createIndividual().apply {
            when (this) {
                is Individual -> fitness = Fitness()
                is MultiObjectiveIndividual -> fitness = MultiObjectiveFitness(weights)
                else -> throw NotAnIndividualException("$this does not inherit from an individual.")
            }
        }

    /**
     * Spawns (instantiates) the specified number of individuals.
     *
     * @param n The number of individuals to create.
     *
     * @return A [List] with the created individuals.
     *
     * @throws NotAnIndividualException If [INDIVIDUAL] does not inherit an Individual class.
     */
    fun spawn(n: Int): List<INDIVIDUAL> = List(n) { spawn() }

    /**
     * Clones (deep copies) an individual.
     *
     * @param ind The individual to clone.
     *
     * @return The cloned individual.
     */
    fun clone(ind: INDIVIDUAL): INDIVIDUAL = cloner.deepClone(ind)

    /**
     * Clones (deep copies) the provided individuals.
     *
     * @param inds The individuals to clone.
     *
     * @return A [List] with cloned individuals.
     */
    fun clone(inds: List<INDIVIDUAL>): List<INDIVIDUAL> = inds.map { clone(it) }
}
