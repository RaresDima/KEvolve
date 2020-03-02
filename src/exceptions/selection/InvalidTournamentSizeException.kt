package exceptions.selection

import selection.Tournament

/**
 * Thrown when a [Tournament] has tournSize < 1.
 */
class InvalidTournamentSizeException(msg: String): Exception(msg)
