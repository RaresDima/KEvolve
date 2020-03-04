package mutation

import exceptions.mutation.InvalidProbabilityException
import exceptions.mutation.InvalidStDevException
import kotlin.random.Random

/**
 * Replace values with other values returned by a function.
 *
 * Each value will have a chance to be replaced.
 *
 * The changes will be done IN PLACE.
 * A reference to the original individual will be returned.
 *
 * @property genePb The chance to replace a value.
 * @property getValue
 *  A function that takes the current gene value and returns a [Double].
 *
 * @constructor
 * @param genePb The chance to replace a value.
 * @param getValue
 *  A function that takes the current gene value and returns a [Double].
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
class MutateValueReplace<INDIVIDUAL, DNA: MutableList<GENE>, GENE: Number>(
    val genePb: Double,
    val getValue: (GENE) -> GENE,
    getDna: (INDIVIDUAL) -> DNA
): BaseMutation<INDIVIDUAL, DNA>(getDna) {

    init {
        if (genePb <= 0.0)
            throw InvalidProbabilityException("genePb = $genePb <= 0.0")

        if (genePb > 1.0)
            throw InvalidProbabilityException("genePb = $genePb > 1.0")
    }

    /**
     * Replace values with other values extracted from a Gauss curve.
     *
     * Each value will have a chance ([genePb]) to be replaced.
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

        for (i in 0..dna.lastIndex)
            if (Random.nextDouble(1.0) < genePb)
                dna[i] = getValue(dna[i])

        return ind
    }

}
