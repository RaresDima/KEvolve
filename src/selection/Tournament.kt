package selection

import exceptions.selection.InvalidTournamentSizeException
import individual.BaseIndividual
import java.util.*
import kotlin.random.Random

class Tournament<INDIVIDUAL: BaseIndividual<*>>(val tournSize: Int): BaseSelection<INDIVIDUAL>() {

    init {
        if (tournSize < 1)
            throw InvalidTournamentSizeException("tournSize = $tournSize < 1")
    }

    override fun invoke(pop: List<INDIVIDUAL>, k: Int): List<INDIVIDUAL> =
            List(k) {
                Collections.
            }

}