package crossover

import exceptions.operators.DnaTooSmallException
import exceptions.operators.InvalidNCuttingPointsException
import utils.extensions.randomSequence
import utils.extensions.remove

/**
 * Crossover with n cutting points.
 *
 * N cutting points is randomly determined and the individuals are crossed over at
 * that cutting point.
 *
 * @property nCuttingPoints The number of cutting points to use.
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
 * @param nCuttingPoints The number of cutting points to use.
 * @param getDna
 *  A function that takes one Individual and returns a reference to its DNA. Most
 *  classes used as individuals will have a property that represents their DNA.
 *  This function should return a reference to that DNA property. Usually this can
 *  be as simple as `{ return individual.myBits }`. In the case that the individual
 *  IS the DNA (the individual inherits [List] for example) this function can
 *  simply be the identity function.
 *
 * @throws InvalidNCuttingPointsException
 *  If [nCuttingPoints] < 1 since that would mean no cutting points.
 */
class CrossoverNPoints<INDIVIDUAL, DNA: MutableList<GENE>, GENE>(
    val nCuttingPoints: Int,
    getDna: (INDIVIDUAL) -> DNA):

    BaseCrossover<INDIVIDUAL, DNA>(getDna) {

    init {
        if (nCuttingPoints < 1)
            throw InvalidNCuttingPointsException("nCuttingPoints = $nCuttingPoints < 1")
    }

    /**
     * Crossover with n cutting points.
     *
     * N cutting points is randomly determined and the individuals are crossed over at
     * that cutting point.
     *
     * @param ind1 The individual to crossover.
     * @param ind2 The individual to crossover.
     *
     * @return A [Pair] with the children of the individuals.
     *
     * @throws DnaTooSmallException
     *  If the dna.size of either individual is < 2.
     *  Or if the dna.size is < [nCuttingPoints] + 1 since that would mean more cutting
     *  points than possible segments of dna.
     */
    override operator fun invoke(ind1: INDIVIDUAL, ind2: INDIVIDUAL): Pair<INDIVIDUAL, INDIVIDUAL> {
        val dna1 = getDna(ind1)
        val dna2 = getDna(ind2)

        if (dna1.size < 2)
            throw DnaTooSmallException("ind1.dna.size = ${dna1.size} < 2")

        if (dna2.size < 2)
            throw DnaTooSmallException("ind2.dna.size = ${dna2.size} < 2")

        val minDnaSize = minOf(dna1.size, dna2.size)

        if (minDnaSize < nCuttingPoints + 1)
            throw DnaTooSmallException("dna.size = $minDnaSize < nCuttingPoints + 1 = ${nCuttingPoints + 1}")

        val cuttingPointRange = 1..minDnaSize

        val cuttingPoints = cuttingPointRange.randomSequence().distinct().take(nCuttingPoints).sorted().toMutableList()
        cuttingPoints.add(0, 0)
        if (cuttingPoints.size % 2 == 1)
            cuttingPoints.add(minDnaSize)

        for ((cuttingPoint1, cuttingPoint2) in cuttingPoints.chunked(2)) {

            val dnaPartRange = cuttingPoint1 until cuttingPoint2

            val dna1part = dna1.slice(dnaPartRange)
            val dna2part = dna2.slice(dnaPartRange)

            dna1.remove(dnaPartRange)
            dna1.addAll(cuttingPoint1, dna2part)

            dna2.remove(dnaPartRange)
            dna2.addAll(cuttingPoint1, dna1part)
        }

        return ind1 to ind2
    }

}
