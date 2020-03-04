package crossover

/**
 * Base Crossover class that exposes functionalities all n-ary Crossover
 * algorithms should have.
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
abstract class BaseNAryCrossover<INDIVIDUAL, DNA>(val getDna: (INDIVIDUAL) -> DNA) {

    /**
     * Mutate the provided number of individual.
     *
     * @param inds The individuals to mutate.
     *
     * @return The child of the individuals.
     */
    abstract operator fun invoke(vararg inds: INDIVIDUAL): INDIVIDUAL

}
