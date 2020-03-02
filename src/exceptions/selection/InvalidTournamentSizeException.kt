package exceptions.selection

import selection.SelectTournament

/**
 * Thrown when a [SelectTournament] has tournSize < 1.
 */
class InvalidTournamentSizeException(msg: String): Exception(msg)
