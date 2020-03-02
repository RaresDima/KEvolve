package selection

import individual.BaseIndividual

/**
 *
 */
abstract class BaseSelection<INDIVIDUAL: BaseIndividual<*>> {
    abstract fun invoke(pop: List<INDIVIDUAL>): List<INDIVIDUAL>
}
