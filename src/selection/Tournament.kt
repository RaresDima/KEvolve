package selection

import exceptions.selection.InvalidTournamentSizeException

class Tournament(val tournSize: Int) {

    init {
        if (tournSize < 1)
            throw InvalidTournamentSizeException("tournSize = $tournSize < 1")
    }



}