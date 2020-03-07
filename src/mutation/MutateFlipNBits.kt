package mutation

import exceptions.operators.InvalidNBitsException
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
 *  be as simple as `{ return individual.myBits }`. In the case that the individual
 *  IS the DNA (the individual inherits [List] for example) this function can
 *  simply be the identity function.
 *
 * @throws InvalidNBitsException If [nBits] < 1.
 */
class MutateFlipNBits<INDIVIDUAL, DNA: MutableList<Bit>>(val nBits: Int, getDna: (INDIVIDUAL) -> DNA):
    BaseMutation<INDIVIDUAL, DNA>(getDna) {

    init {
        if (nBits < 1)
            throw InvalidNBitsException("nBits = $nBits < 1")
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
     *
     * @throws InvalidNBitsException If [nBits] > [ind].dna.size.
     */
    override operator fun invoke(ind: INDIVIDUAL): INDIVIDUAL {
        val dna = getDna(ind)

        if (nBits > dna.size)
            throw InvalidNBitsException("nBits = $nBits > dna.size = ${dna.size}")

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
