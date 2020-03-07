package crossover

import exceptions.operators.DnaTooSmallException
import exceptions.operators.InvalidNCuttingPointsException
import exceptions.operators.InvalidProbabilityException
import utils.extensions.randomSequence
import utils.extensions.remove
import kotlin.random.Random

/**
 * Crossover with every gene having a chance to be exchanged.
 *
 * @property genePb The chance to exchange a gene.
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
 * @param genePb The chance to exchange a gene.
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
class CrossoverUniform<INDIVIDUAL, DNA: MutableList<GENE>, GENE>(
    val genePb: Double,
    getDna: (INDIVIDUAL) -> DNA):

    BaseCrossover<INDIVIDUAL, DNA>(getDna) {

    init {
        if (genePb <= 0.0)
            throw InvalidProbabilityException("genePb = $genePb <= 0.0")

        if (genePb > 1.0)
            throw InvalidProbabilityException("genePb = $genePb > 1.0")
    }

    /**
     * Crossover with every gene having a chance ([genePb]) to be exchanged.
     *
     * @param ind1 The individual to crossover.
     * @param ind2 The individual to crossover.
     *
     * @return A [Pair] with the children of the individuals.
     *
     * @throws DnaTooSmallException
     *  If the dna.size of either individual is < 1.
     */
    override operator fun invoke(ind1: INDIVIDUAL, ind2: INDIVIDUAL): Pair<INDIVIDUAL, INDIVIDUAL> {
        val dna1 = getDna(ind1)
        val dna2 = getDna(ind2)

        if (dna1.size < 2)
            throw DnaTooSmallException("ind1.dna.size = ${dna1.size} < 1")

        if (dna2.size < 2)
            throw DnaTooSmallException("ind2.dna.size = ${dna2.size} < 1")

        val minDnaSize = minOf(dna1.size, dna2.size)

        for (i in 0 until minDnaSize)
            if (Random.nextDouble(1.0) < genePb) {
                val aux = dna1[i]
                dna1[i] = dna2[i]
                dna2[i] = aux
            }
        
        return ind1 to ind2
    }

}
