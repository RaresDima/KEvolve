package selection

import exceptions.selection.InvalidTournamentSizeException
import exceptions.selection.PopulationTooSmallException
import exceptions.selection.SelectionTooSmallException
import extensions.randomSequence
import individual.BaseIndividual
import kotlin.random.Random

/**
 * Selects k individuals using k spins of a roulette.
 *
 * The selection is made from a weighted probability distribution.
 *
 * First, the sum of all the fitness scores in the population is computed.
 *
 * The fraction that an individual contributed to this total fitness is its chance
 * to be selected.
 *
 * E.g. If individual X's fitness represents 3% of the total population's fitness
 * then X has a 3% chance of being selected.
 *
 * Selecting the same individual multiple times is possible.
 */
class SelectRoulette: BaseSelection() {

    /**
     * Selects [k] individuals using [k] spins of a roulette.
     *
     * The selection is made from a weighted probability distribution.
     *
     * The sum of all the fitness scores in the population is computed.
     *
     * The fraction that an individual contributed to this total fitness is its chance
     * to be selected.
     *
     * E.g. If individual X's fitness represents 3% of the total population's fitness
     * then X has a 3% chance of being selected.
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

        // Select

        val selected = MutableList(k) { pop[0] }
        for (i in 0..(k-1)) {
            val rand = Random.nextDouble(fitnessSum)
            val selectedIndex = (0..fitnessCumSum.lastIndex).find { rand <= fitnessCumSum[it] }!! - 1
            selected[i] = pop[selectedIndex]
        }

        return selected
    }
}
