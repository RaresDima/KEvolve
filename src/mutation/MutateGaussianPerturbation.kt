package mutation

import exceptions.mutation.InvalidProbabilityException
import exceptions.mutation.InvalidStDevException

/**
 * Change values by adding other values extracted from a Gauss curve.
 *
 * Each value will have a chance to be replaced.
 *
 * The changes will be done IN PLACE.
 * A reference to the original individual will be returned.
 *
 * @property genePb The chance to replace a value.
 * @property mean The mean of the Gaussian distribution.
 * @property std The standard error of the Gaussian distribution.
 *
 * @constructor
 * @param genePb The chance to replace a value.
 * @param mean The mean of the Gaussian distribution.
 * @param std The standard error of the Gaussian distribution.
 * @param getDna
 *  A function that takes one Individual and returns a reference to its DNA. Most
 *  classes used as individuals will have a property that represents their DNA.
 *  This function should return a reference to that DNA property. Usually this can
 *  be as simple as `{ return individual.myBits }`. In the case that the individual
 *  IS the DNA (the individual inherits [List] for example) this function can
 *  simply be the identity function.
 *
 * @throws InvalidProbabilityException If [genePb] <= 0 or [genePb] > 1 or [std] < 0.0.
 */
class MutateGaussianPerturbation<INDIVIDUAL, DNA: MutableList<Number>>(
    val genePb: Double,
    val mean: Double,
    val std: Double, getDna: (INDIVIDUAL) -> DNA
): BaseMutation<INDIVIDUAL, DNA>(getDna) {

    init {
        if (genePb <= 0.0)
            throw InvalidProbabilityException("genePb = $genePb <= 0.0")

        if (genePb > 1.0)
            throw InvalidProbabilityException("genePb = $genePb > 1.0")

        if (std <= 0.0)
            throw InvalidStDevException("std = $std < 0.0")
    }

    private val rand = java.util.Random()
    private val mutateGaussian = MutateValueReplace(
        genePb = genePb,
        getValue = { it.toDouble() + (rand.nextGaussian() * std + mean) },
        getDna = getDna
    )

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
    override operator fun invoke(ind: INDIVIDUAL): INDIVIDUAL = mutateGaussian(ind)

}
