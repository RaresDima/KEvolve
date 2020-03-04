package mutation

import exceptions.mutation.InvalidProbabilityException
import utils.Bit
import kotlin.random.Random

/**
 * Shuffle indices in the individual.
 *
 * The changes will be done IN PLACE.
 * A reference to the original individual will be returned.
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
class MutateShuffleIndexes<INDIVIDUAL, DNA: MutableList<GENE>, GENE>(getDna: (INDIVIDUAL) -> DNA):
    BaseMutation<INDIVIDUAL, DNA>(getDna) {

    /**
     * Shuffle indices in the individual.
     *
     * The changes will be done IN PLACE.
     * A reference to the original individual will be returned.
     *
     * @param ind The individual to mutate.
     *
     * @return The mutated individual.
     */
    override operator fun invoke(ind: INDIVIDUAL): INDIVIDUAL {
        val dna = getDna(ind)
        dna.shuffle()
        return ind
    }

}
