package selection

import individual.BaseIndividual

/**
 * Base Selection class that exposes functionalities all Selection algorithms
 * should have.
 */
abstract class BaseSelection {

    /**
     * Select the desired number of individuals.
     *
     * [getFitness] is only needed if your individuals are highly customized and do
     * not implement any predefined Individual class from KEvolve (hence the need for
     * a user-defined method of getting the fitness).
     *
     * If you are using a class that implements an Individual class then
     * `Individual.fitness` will be used as a default way to compare fitness scores.
     *
     * @param pop The population to select from.
     * @param k How many individuals to select.
     * @param getFitness
     *  A function that takes an individual and return a comparable value (an object
     *  that implements [Comparable]).
     *
     * @return A [List] with the selected individuals.
     */
    abstract operator fun <INDIVIDUAL, FITNESS: Comparable<FITNESS>>
            invoke(pop: List<INDIVIDUAL>,
                   k: Int,
                   getFitness: (INDIVIDUAL) -> FITNESS): List<INDIVIDUAL>

    /**
     * Select the desired number of individuals.
     *
     * @param pop The population to select from.
     * @param k How many individuals to select.
     *
     * @return A [List] with the selected individuals.
     */
    operator fun <INDIVIDUAL: BaseIndividual<*>> invoke(pop: List<INDIVIDUAL>, k: Int): List<INDIVIDUAL> =
        invoke(pop, k) { it.fitness }
}
