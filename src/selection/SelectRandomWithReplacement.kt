package selection

import exceptions.selection.PopulationTooSmallException
import exceptions.selection.SelectionTooSmallException
import extensions.randomSequence
import individual.BaseIndividual

/**
 * Selects k random individuals in the population with replacement.
 */
class SelectRandomWithReplacement<INDIVIDUAL: BaseIndividual<*>>(): BaseSelection<INDIVIDUAL>() {

    /**
     * Selects [k] random individuals in the [pop] with replacement.
     *
     * @param pop The population to select from.
     * @param k The number of individuals to select.
     *
     * @throws SelectionTooSmallException
     * If [k] < 1 since it makes no sense to select 0 individuals.
     */
    override operator fun invoke(pop: List<INDIVIDUAL>, k: Int): List<INDIVIDUAL> {

        if (k < 1)
            throw SelectionTooSmallException("k = $k < 1")

        return List(k) { pop.random() }
    }
}
