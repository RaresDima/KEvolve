package mutation

import individual.BaseIndividual

/**
 * Base Mutation class that exposes functionalities all Mutation algorithms should
 * have.
 *
 * The changes will be done IN PLACE.
 */
abstract class BaseMutation {

    /**
     * Mutate the provided number of individual.
     *
     * The changes will be done IN PLACE.
     *
     * @param ind The individual to mutate.
     *
     * @return The mutated individual.
     */
    abstract fun <INDIVIDUAL: BaseIndividual<*>> invoke(ind: INDIVIDUAL): INDIVIDUAL

}
