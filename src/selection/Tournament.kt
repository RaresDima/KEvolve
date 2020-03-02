package selection

import exceptions.selection.InvalidTournamentSizeException
import extensions.randomSequence
import individual.BaseIndividual

class Tournament<INDIVIDUAL: BaseIndividual<*>>(val tournSize: Int): BaseSelection<INDIVIDUAL>() {

    init {
        if (tournSize < 1)
            throw InvalidTournamentSizeException("tournSize = $tournSize < 1")
    }

    override fun invoke(pop: List<INDIVIDUAL>, k: Int): List<INDIVIDUAL> {
        if (pop.size < tournSize)
            throw
        List(k) {
            (0..pop.lastIndex)
                .randomSequence()
                .distinct()
                .take(tournSize)
                .map { pop[it] }
                .maxBy { it.fitness.value() }!!
        }
    }

}