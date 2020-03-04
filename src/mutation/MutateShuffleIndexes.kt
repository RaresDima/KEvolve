package mutation

import exceptions.mutation.DnaTooSmallException
import exceptions.mutation.InvalidProbabilityException
import utils.Bit
import kotlin.random.Random

/**
 * Swap indices in the individual with a probability.
 *
 * Each gene will have a probability to switch places with another gene.
 *
 * The changes will be done IN PLACE.
 * A reference to the original individual will be returned.
 *
 * @property genePb The chance to swap a gene.
 *
 * @constructor
 * @param genePb The chance to swap a gene.
 * @param getDna
 *  A function that takes one Individual and returns a reference to its DNA. Most
 *  classes used as individuals will have a property that represents their DNA.
 *  This function should return a reference to that DNA property. Usually this can
 *  be as simple as `{ return individual.myBits }`. In the case that the individual
 *  IS the DNA (the individual inherits [List] for example) this function can
 *  simply be the identity function.
 *
 * @throws InvalidProbabilityException If [genePb] <= 0 or [genePb] > 1.
 */
class MutateShuffleIndexes<INDIVIDUAL, DNA: MutableList<GENE>, GENE>(val genePb: Double, getDna: (INDIVIDUAL) -> DNA):
    BaseMutation<INDIVIDUAL, DNA>(getDna) {

    init {
        if (genePb <= 0.0)
            throw InvalidProbabilityException("genePb = $genePb <= 0.0")

        if (genePb > 1.0)
            throw InvalidProbabilityException("genePb = $genePb > 1.0")
    }

    /**
     * Swap indices in the individual with a probability.
     *
     * Each gene will have a probability to switch places with another gene.
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

        if (dna.size < 2)
            throw DnaTooSmallException("dna.size = ${dna.size} < 2")

        for (i in 0..dna.lastIndex) {
            if (Random.nextDouble(1.0) < genePb) {
                var pairIndex = (0..(dna.lastIndex - 1)).random()

                if (pairIndex == i)
                    when (pairIndex) {
                        0 -> pairIndex++
                        dna.lastIndex -> pairIndex--
                    }

                val aux = dna[i]
                dna[i] = dna[pairIndex]
                dna[pairIndex] = aux
            }
        }

        return ind
    }

}
