package mutation

import individual.BaseIndividual
import utils.Bit

/**
 * Base Mutation class that exposes functionalities all Mutation algorithms should
 * have.
 *
 * @property getDna
 *  A function that takes one Individual and returns a reference to its DNA. Most
 *  classes used as individuals will have a property that represents their DNA.
 *  This function should return a reference to that DNA property. Usually this can
 *  be as simple as `{ return individual.myBits }`. In the case that the individual
 *  IS the DNA (the individual inherits [List] for example) this function can
 *  simply be the identity function.
 *
 * @constructor
 * @param getDna
 *  A function that takes one Individual and returns a reference to its DNA. Most
 *  classes used as individuals will have a property that represents their DNA.
 *  This function should return a reference to that DNA property. Usually this can
 *  be as simple as `{ return individual.myBits }`. In the case that the individual
 *  IS the DNA (the individual inherits [List] for example) this function can
 *  simply be the identity function.
 */
abstract class BaseMutation<INDIVIDUAL, DNA>(val getDna: (INDIVIDUAL) -> DNA) {

    /**
     * Mutate the provided number of individual.
     *
     * @param ind The individual to mutate.
     *
     * @return The mutated individual.
     */
    abstract operator fun invoke(ind: INDIVIDUAL): INDIVIDUAL

}
