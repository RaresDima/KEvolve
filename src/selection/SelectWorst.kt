package selection

import exceptions.selection.InvalidTournamentSizeException
import exceptions.selection.PopulationTooSmallException
import exceptions.selection.SelectionTooSmallException
import extensions.randomSequence
import individual.BaseIndividual

/**
 * Selects the worst k individuals in the population.
 *
 * Selecting the same individual multiple times is not possible.
 */
class SelectWorst: BaseSelection() {

    /**
     * Selects the worst [k] individuals in the [pop].
     *
     * Selecting the same individual multiple times is not possible.
     *
     * @param pop The population to select from.
     * @param k The number of individuals to select.
     *
     * @return A [List] with the selected individuals.
     *
     * @throws PopulationTooSmallException
     * If the size of the population is less than [k] since that would mean that there
     * are not enough individuals to select.
     *
     * @throws SelectionTooSmallException
     * If [k] < 1 since it makes no sense to select 0 individuals.
     */
    override operator fun <INDIVIDUAL: BaseIndividual<*>> invoke(pop: List<INDIVIDUAL>, k: Int): MutableList<INDIVIDUAL> {

        if (pop.size < k)
            throw PopulationTooSmallException("pop size = ${pop.size} < k = $k")

        if (k < 1)
            throw SelectionTooSmallException("k = $k < 1")

        return pop.sortedBy { it.fitness }.take(k).toMutableList()
    }

}
