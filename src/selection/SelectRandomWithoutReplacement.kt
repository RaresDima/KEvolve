package selection

import exceptions.selection.PopulationTooSmallException
import exceptions.selection.SelectionTooSmallException
import utils.extensions.randomSequence
import individual.BaseIndividual

/**
 * Selects k random individuals in the population without replacement.
 *
 * Selecting the same individual multiple times is not possible.
 */
class SelectRandomWithoutReplacement: BaseSelection() {

    /**
     * Selects [k] random individuals in the [pop] without replacement.
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

        return (0..pop.lastIndex)
            .randomSequence()
            .distinct()
            .take(k)
            .map { pop[it] }
            .toMutableList()
    }

}