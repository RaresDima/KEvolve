package mutation

import utils.Bit
import utils.extensions.randomSequence

/**
 * Flip N random bits ([nBits]) in the individual.
 *
 * The changes will be done IN PLACE.
 * A reference to the original individual will be returned.
 *
 * @property nBits The number of bits to flip.
 *
 * @constructor
 * @param nBits The number of bits to flip.
 * @param getDna
 *  A function that takes one Individual and returns a reference to its DNA. Most
 *  classes used as individuals will have a property that represents their DNA.
 *  This function should return a reference to that DNA property. Usually this can
 *  be as simple as `{ return individual.myBits }`.
 */
class MutateFlipNBits<INDIVIDUAL, DNA: MutableList<Bit>>(val nBits: Int, getDna: (INDIVIDUAL) -> DNA):
    BaseMutation<INDIVIDUAL, DNA>(getDna) {

    init {
        if (tournSize < 2)
            throw InvalidTournamentSizeException("tournSize = $tournSize < 2")
    }

    /**
     * Flip [nBits] random bits in the individual.
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
        (0..dna.lastIndex)
            .randomSequence()
            .distinct()
            .take(nBits)
            .forEach {
                dna[it].flip()
            }
        return ind
    }

}
