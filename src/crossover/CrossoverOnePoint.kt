package crossover

import utils.extensions.remove

/**
 * Crossover with 1 cutting point.
 *
 * A cutting point is randomly determines and the individuals are crossed over at
 * that cutting point.
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
 * @param getDna
 *  A function that takes one Individual and returns a reference to its DNA. Most
 *  classes used as individuals will have a property that represents their DNA.
 *  This function should return a reference to that DNA property. Usually this can
 *  be as simple as `{ return individual.myBits }`. In the case that the individual
 *  IS the DNA (the individual inherits [List] for example) this function can
 *  simply be the identity function.
 */
abstract class CrossoverOnePoint<INDIVIDUAL, DNA: MutableList<GENE>, GENE>(getDna: (INDIVIDUAL) -> DNA):
    BaseCrossover<INDIVIDUAL, DNA>(getDna) {

    /**
     * Crossover with 1 cutting point.
     *
     * @param ind1 The individual to crossover.
     * @param ind2 The individual to crossover.
     *
     * @return A [Pair] with the children of the individuals.
     */
    override operator fun invoke(ind1: INDIVIDUAL, ind2: INDIVIDUAL): Pair<INDIVIDUAL, INDIVIDUAL> {
        val dna1 = getDna(ind1)
        val dna2 = getDna(ind2)

        val minDnaSize = minOf(dna1.size, dna2.size)
        val cuttingPointRange = 0 until minDnaSize
        val cuttingPoint = cuttingPointRange.random()

        val dna1half1 = dna1.slice(0..cuttingPoint)
        val dna2half1 = dna2.slice(0..cuttingPoint)

        dna1.remove(0..cuttingPoint)
        dna1.addAll(0, dna2half1)

        dna2.remove(0..cuttingPoint)
        dna2.addAll(0, dna1half1)

        return ind1 to ind2
    }

}
