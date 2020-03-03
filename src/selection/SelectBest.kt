package selection

import exceptions.selection.InvalidTournamentSizeException
import exceptions.selection.PopulationTooSmallException
import exceptions.selection.SelectionTooSmallException
import extensions.randomSequence
import individual.BaseIndividual

/**
 * Selects the best k individuals in the population.
 *
 * Selecting the same individual multiple times is not possible.
 */
class SelectBest: BaseSelection() {

    /**
     * Selects the best [k] individuals in the [pop].
     *
     * Selecting the same individual multiple times is not possible.
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
     *  that implements [Comparable]).
     *
     * @throws PopulationTooSmallException
     * If the size of the population is less than [k] since that would mean that there
     * are not enough individuals to select.
     *
     * @throws SelectionTooSmallException
     * If [k] < 1 since it makes no sense to select 0 individuals.
     */
    override operator fun <INDIVIDUAL, FITNESS: Comparable<FITNESS>>
            invoke(pop: List<INDIVIDUAL>,
                   k: Int,
                   getFitness: (INDIVIDUAL) -> FITNESS): List<INDIVIDUAL> {

        if (pop.size < k)
            throw PopulationTooSmallException("pop size = ${pop.size} < k = $k")

        if (k < 1)
            throw SelectionTooSmallException("k = $k < 1")

        return pop.sortedBy { getFitness(it) }.takeLast(k)
    }
}
