package exceptions.selection

import selection.SelectTournament
import selection.SelectBest

/**
 * Thrown when a Selection tries to select from a population with too few
 * individuals.
 *
 * E.g.
 *
 * - Population size less that tournSize for [SelectTournament] (too few
 * individuals to fill even one tournament).
 *
 * - [SelectBest] tries to select more individuals that the size of the
 * population.
 */
class PopulationTooSmallException(msg: String): Exception(msg)
