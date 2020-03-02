package exceptions.selection

import selection.SelectTournament

/**
 * Thrown when a [SelectTournament] tries to select from a population with size
 * less than tournSize (too few individuals to fill even one tournament).
 */
class PopulationTooSmallException(msg: String): Exception(msg)
