package selection

import exceptions.selection.PopulationTooSmallException
import exceptions.selection.SelectionTooSmallException
import extensions.randomSequence
import individual.BaseIndividual

/**
 * Selects k random individuals in the population with replacement.
 *
 * Selecting the same individual multiple times is possible.
 */
class SelectRandomWithReplacement: BaseSelection() {

    /**
     * Selects [k] random individuals in the [pop] with replacement.
     *
     * Selecting the same individual multiple times is possible.
     *
     * [getFitness] is only needed if your individuals are highly customized and do
     * not implement any predefined Individual class from KEvolve (hence the need for
     * a user-defined method of getting the fitness).
     *
     * If you are using a class that implements an Individual class then
     * `Individual.fitness` will be used as a default way to compare fitness scores.
     *
     * @param pop The population to select from.
     * @param k The number of individuals to select.
     * @param getFitness
     *  A function that takes an individual and return a comparable value (an object
     *  that implements [Comparable]). In this case this function has no effect.
     *
     * @throws PopulationTooSmallException
     * If the population is empty.
     *
     * @throws SelectionTooSmallException
     * If [k] < 1 since it makes no sense to select 0 individuals.
     */
    override operator fun <INDIVIDUAL, FITNESS: Comparable<FITNESS>>
            invoke(pop: List<INDIVIDUAL>,
                   k: Int,
                   getFitness: (INDIVIDUAL) -> FITNESS): List<INDIVIDUAL> {

        if (pop.isEmpty())
            throw PopulationTooSmallException("population size = ${pop.size} (empty)")

        if (k < 1)
            throw SelectionTooSmallException("k = $k < 1")

        return List(k) { pop.random() }
    }
}
