package selection

import individual.BaseIndividual

/**
 * Base Selection class that exposes functionalities all Selection algorithms
 * should have.
 */
abstract class BaseSelection<INDIVIDUAL: BaseIndividual<*>> {

    /**
     * Select the desired number of individuals.
     *
     * @param pop The population to select from.
     * @param k How many individuals to select.
     *
     * @return A [List] with the selected individuals.
     */
    abstract fun invoke(pop: List<INDIVIDUAL>, k: Int): List<INDIVIDUAL>
}