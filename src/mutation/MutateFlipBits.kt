package mutation

import exceptions.operators.InvalidProbabilityException
import utils.Bit
import kotlin.random.Random

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
 * @throws InvalidProbabilityException If [bitPb] <= 0 or [bitPb] > 1.
 */
class MutateFlipBits<INDIVIDUAL, DNA: MutableList<Bit>>(val bitPb: Double, getDna: (INDIVIDUAL) -> DNA):
    BaseMutation<INDIVIDUAL, DNA>(getDna) {

    init {
        if (bitPb <= 0.0)
            throw InvalidProbabilityException("bitPb = $bitPb <= 0.0")

        if (bitPb > 1.0)
            throw InvalidProbabilityException("bitPb = $bitPb > 1.0")
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
     */
    override operator fun invoke(ind: INDIVIDUAL): INDIVIDUAL {
        val dna = getDna(ind)

        for (bit in dna)
            if (Random.nextDouble(1.0) < bitPb)
                bit.flip()

        return ind
    }

}
