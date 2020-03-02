package selection

import exceptions.selection.InvalidTournamentSizeException
import exceptions.selection.PopulationTooSmallException
import exceptions.selection.SelectionTooSmallException
import extensions.randomSequence
import individual.BaseIndividual

class Tournament<INDIVIDUAL: BaseIndividual<*>>(val tournSize: Int): BaseSelection<INDIVIDUAL>() {

    init {
        if (tournSize < 2)
            throw InvalidTournamentSizeException("tournSize = $tournSize < 2")
    }

    override operator fun invoke(pop: List<INDIVIDUAL>, k: Int): List<INDIVIDUAL> {

        if (pop.size < tournSize)
            throw PopulationTooSmallException("pop size = ${pop.size} | tournSize = $tournSize")

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