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
     * @param pop The population to select from.
     * @param k The number of individuals to select.
     *
     * @return A [List] with the selected individuals.
     *
     * @throws PopulationTooSmallException
     * If the population is empty.
     *
     * @throws SelectionTooSmallException
     * If [k] < 1 since it makes no sense to select 0 individuals.
     */
    override operator fun <INDIVIDUAL: BaseIndividual<*>> invoke(pop: List<INDIVIDUAL>, k: Int): MutableList<INDIVIDUAL> {

        if (pop.isEmpty())
            throw PopulationTooSmallException("population size = ${pop.size} (empty)")

        if (k < 1)
            throw SelectionTooSmallException("k = $k < 1")

        return MutableList(k) { pop.random() }
    }
}
