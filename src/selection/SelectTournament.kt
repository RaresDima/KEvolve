package selection

import exceptions.selection.InvalidTournamentSizeException
import exceptions.selection.PopulationTooSmallException
import exceptions.selection.SelectionTooSmallException
import extensions.randomSequence
import individual.BaseIndividual

/**
 * Tournament Selection selects the best individual from a small pool of randomly
 * chosen [tournSize] individuals.
 *
 * This repeats k times (until the desired number of individuals are selected).
 *
 * @property tournSize The size of each tournament pool.
 *
 * @constructor
 * @param tournSize The size of each tournament pool.
 *
 * @throws InvalidTournamentSizeException
 * If [tournSize] < 2. Since that would make each tournament either to small to be
 * able to choose from (no point in choosing from a tournament of 1 individual) or
 * have a size of 0 or less.
 */
class SelectTournament<INDIVIDUAL: BaseIndividual<*>>(val tournSize: Int): BaseSelection<INDIVIDUAL>() {

    init {
        if (tournSize < 2)
            throw InvalidTournamentSizeException("tournSize = $tournSize < 2")
    }

    /**
     * Tournament Selection selects the best individual from a small pool of randomly
     * chosen [tournSize] individuals.
     *
     * This repeats [k] times (until the desired number of individuals are selected).
     *
     * @param pop The population to select from.
     * @param k The number of individuals to select.
     *
     * @throws PopulationTooSmallException
     * If the size of the population is less than [tournSize] since that would mean
     * that there are not enough individuals to from a tournament.
     *
     * @throws SelectionTooSmallException
     * If [k] < 1 since it makes no sense to select 0 individuals.
     */
    override operator fun invoke(pop: List<INDIVIDUAL>, k: Int): List<INDIVIDUAL> {

        if (pop.size < tournSize)
            throw PopulationTooSmallException("pop size = ${pop.size} < tournSize = $tournSize")

        if (k < 1)
            throw SelectionTooSmallException("k = $k < 1")

        return List(k) {
            (0..pop.lastIndex)
                .randomSequence()
                .distinct()
                .take(tournSize)
                .map { pop[it] }
                .maxBy { it.fitness.value() }!!
        }
    }
}