package mutation

import exceptions.mutation.InvalidProbabilityException
import exceptions.mutation.InvalidStDevException
import exceptions.utils.InvalidDomainBoundsException
import kotlin.random.Random

/**
 * Modify values with other random values.
 *
 * Each value will have a chance to be perturbed.
 *
 * The changes will be done IN PLACE.
 * A reference to the original individual will be returned.
 *
 * @property genePb The chance to replace a value.
 * @property min The min value to be assigned.
 * @property max The max value to be assigned.
 *
 * @constructor
 * @param genePb The chance to replace a value.
 * @param min The min value to be assigned.
 * @param max The max value to be assigned.
 * @param getDna
 *  A function that takes one Individual and returns a reference to its DNA. Most
 *  classes used as individuals will have a property that represents their DNA.
 *  This function should return a reference to that DNA property. Usually this can
 *  be as simple as `{ return individual.myBits }`. In the case that the individual
 *  IS the DNA (the individual inherits [List] for example) this function can
 *  simply be the identity function.
 *
 * @throws InvalidProbabilityException If [genePb] <= 0 or [genePb] > 1.
 * @throws InvalidDomainBoundsException If [min] >= [max].
 */
class MutateUniformPerturbation<INDIVIDUAL, DNA: MutableList<Double>>(
    val genePb: Double,
    val min: Double,
    val max: Double,
    getDna: (INDIVIDUAL) -> DNA
): BaseMutation<INDIVIDUAL, DNA>(getDna) {

    init {
        if (genePb <= 0.0)
            throw InvalidProbabilityException("genePb = $genePb <= 0.0")

        if (genePb > 1.0)
            throw InvalidProbabilityException("genePb = $genePb > 1.0")

        if (min >= max)
            throw InvalidDomainBoundsException("min = $min | max = $max | min >= max")
    }

    private val mutateUniform = MutateValueReplace(
        genePb = genePb,
        getValue = { it + Random.nextDouble(min, max) },
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
    override operator fun invoke(ind: INDIVIDUAL): INDIVIDUAL = mutateUniform(ind)

}
