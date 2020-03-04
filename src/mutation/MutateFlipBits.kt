package mutation

import exceptions.mutation.InvalidNBitsException
import utils.Bit
import utils.extensions.randomSequence

/**
 * Flip bits with a probability in the individual.
 *
 * Each bit will have a chance to be flipped.
 *
 * The changes will be done IN PLACE.
 * A reference to the original individual will be returned.
 *
 * @property bitPb The chance to flip a bit.
 *
 * @constructor
 * @param bitPb The chance to flip a bit.
 * @param getDna
 *  A function that takes one Individual and returns a reference to its DNA. Most
 *  classes used as individuals will have a property that represents their DNA.
 *  This function should return a reference to that DNA property. Usually this can
 *  be as simple as `{ return individual.myBits }`. In the case that the individual
 *  IS the DNA (the individual inherits [List] for example) this function can
 *  simply be the identity function.
 *
 * @throws InvalidNBitsException If [bitPb] < 0.
 */
class MutateFlipBits<INDIVIDUAL, DNA: MutableList<Bit>>(val bitPb: Double, getDna: (INDIVIDUAL) -> DNA):
    BaseMutation<INDIVIDUAL, DNA>(getDna) {

    init {
        if (bitPb < 1)
            throw InvalidNBitsException("nBits = $bitPb < 1")
    }

    /**
     * Flip bits with a probability ([bitPb]) in the individual.
     *
     * Each bit will have a chance to be flipped.
     *
     * The changes will be done IN PLACE.
     * A reference to the original individual will be returned.
     *
     * @param ind The individual to mutate.
     *
     * @return The mutated individual.
     *
     * @throws InvalidNBitsException If [bitPb] > [ind].dna.size.
     */
    override operator fun invoke(ind: INDIVIDUAL): INDIVIDUAL {
        val dna = getDna(ind)

        if (bitPb > dna.size)
            throw InvalidNBitsException("nBits = $bitPb > dna.size = ${dna.size}")

        (0..dna.lastIndex)
            .randomSequence()
            .distinct()
            .take(bitPb)
            .forEach {
                dna[it].flip()
            }

        return ind
    }

}
