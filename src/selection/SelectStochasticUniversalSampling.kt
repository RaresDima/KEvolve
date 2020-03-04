package selection

import exceptions.selection.PopulationTooSmallException
import exceptions.selection.SelectionTooSmallException
import individual.BaseIndividual
import kotlin.random.Random

/**
 * Selects k individuals using SUS.
 *
 * The selection is made from a weighted probability distribution.
 *
 * First, the sum of all the fitness scores in the population is computed.
 *
 * On an axis between 0 and 1 each individual gets a segment.
 * The size of each segment is a fraction of 1.0 equal to the contribution of the
 * corresponding individual to the total fitness.
 *
 * E.g. If individual X's fitness represents 3% of the total population's fitness
 * then X has a segment that represents 3% of the total size of the axis.
 *
 * Selecting the same individual multiple times is possible.
 */
class SelectStochasticUniversalSampling: BaseSelection() {

    /**
     * Selects [k] individuals using SUS.
     *
     * The selection is made from a weighted probability distribution.
     *
     * First, the sum of all the fitness scores in the population is computed.
     *
     * On an axis between 0 and 1 each individual gets a segment.
     * The size of each segment is a fraction of 1.0 equal to the contribution of the
     * corresponding individual to the total fitness.
     *
     * E.g. If individual X's fitness represents 3% of the total population's fitness
     * then X has a segment that represents 3% of the total size of the axis.
     *
     * Selecting the same individual multiple times is possible.
     *
     * @param pop The population to select from.
     * @param k The number of individuals to select.
     *
     * @return A [List] with the selected individuals.
     *
     * @throws PopulationTooSmallException
     * If the population is empty.
     *
     * @throws SelectionTooSmallException
     * If [k] < 1 since it makes no sense to select 0 individuals.
     */
    override operator fun <INDIVIDUAL: BaseIndividual<*>> invoke(pop: List<INDIVIDUAL>, k: Int): MutableList<INDIVIDUAL> {

        if (pop.isEmpty())
            throw PopulationTooSmallException("population size = ${pop.size} (empty)")

        if (k < 1)
            throw SelectionTooSmallException("k = $k < 1")

        // Fitness Sum and CumSum

        var fitnessSum = pop[0].fitness.value()
        val fitnessCumSum = MutableList(pop.size + 1) { 0.0 }

        fitnessCumSum[1] = fitnessSum

        for (i in 1..pop.lastIndex) {
            fitnessSum += pop[i].fitness.value()
            fitnessCumSum[i+1] = fitnessSum
        }

        // Pointers

        val nPointers = k
        val pointerDist = fitnessSum / nPointers.toDouble()
        val startPos = Random.nextDouble(pointerDist)

        // Select

        var pointerPos = startPos
        val selected = MutableList(k) { pop[0] }
        for (i in 0..(k-1)) {
            val selectedIndex = (0..fitnessCumSum.lastIndex).find { pointerPos <= fitnessCumSum[it] }!! - 1
            selected[i] = pop[selectedIndex]
            pointerPos += pointerDist
        }

        return selected
    }
}
