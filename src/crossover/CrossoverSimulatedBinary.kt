package crossover

import exceptions.operators.DnaTooSmallException
import exceptions.operators.InvalidNCuttingPointsException
import exceptions.operators.InvalidProbabilityException
import exceptions.operators.InvalidSbxEtaException
import utils.extensions.randomSequence
import utils.extensions.remove
import kotlin.math.pow
import kotlin.random.Random

/**
 * Simulated Binary Crossover.
 *
 * This crossover works with [Double] values but simulates a binary crossover.
 *
 * Every gene has a change ([genePb]) to be crossed.
 *
 * @property eta The crowding degree. Large eta means children similar to parents.
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
 * @property eta The crowding degree. Large eta means children similar to parents.
 * @param genePb The chance to exchange a gene. 1.0 by default.
 * @param getDna
 *  A function that takes one Individual and returns a reference to its DNA. Most
 *  classes used as individuals will have a property that represents their DNA.
 *  This function should return a reference to that DNA property. Usually this can
 *  be as simple as `{ return individual.myBits }`. In the case that the individual
 *  IS the DNA (the individual inherits [List] for example) this function can
 *  simply be the identity function.
 *
 * @throws InvalidProbabilityException If [genePb] <= 0 or [genePb] > 1.
 * @throws InvalidSbxEtaException If [eta] == -1.0.
 */
class CrossoverSimulatedBinary<INDIVIDUAL, DNA: MutableList<Double>>(
    val eta: Double,
    val genePb: Double = 1.0,
    getDna: (INDIVIDUAL) -> DNA):

    BaseCrossover<INDIVIDUAL, DNA>(getDna) {

    init {
        if (genePb <= 0.0)
            throw InvalidProbabilityException("genePb = $genePb <= 0.0")

        if (genePb > 1.0)
            throw InvalidProbabilityException("genePb = $genePb > 1.0")

        if (eta == -1.0)
            throw InvalidSbxEtaException("eta = $eta == -1.0")
    }

    /**
     * Simulated Binary Crossover.
     *
     * This crossover works with [Double] values but simulates a binary crossover.
     *
     * Every gene has a change ([genePb]) to be crossed.
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
                val (newGene1, newGene2) = dna1[i] sbx dna2[i]
                dna1[i] = newGene1
                dna2[i] = newGene2
            }
        
        return ind1 to ind2
    }

    private infix fun Double.sbx(gene2: Double): Pair<Double, Double> {
        val gene1 = this

        // Random 0..1
        val rand = Random.nextDouble(1.0)

        // beta =
        // if rand is small, twice rand
        // if rand is big, twice rand complement then invert
        var beta =
            if (rand <= 0.5)
                2.0 * rand
            else
                1.0 / (2.0 * (1.0 - rand))

        // beta to pow 1
        // larger eta -> lower power here
        beta = beta.pow(1.0 / (eta + 1.0))

        /*
                /
               |  2 rand    ;    rand small
        beta = |
               |            1
               |  ---------------------    ;    rand big
               |  2 complement of rand
               \

                    1
                   ---------
                    1 + eta
        beta = beta                  // Decrease exponentially by eta
        */

        val newGene1 = 0.5 * (((1.0 + beta) * gene1) + ((1.0 - beta) * gene2))
        val newGene2 = 0.5 * (((1.0 - beta) * gene1) + ((1.0 + beta) * gene2))

        return newGene1 to newGene2
    }

}
