package exceptions.selection

import population.PopulationFactory

/**
 * Thrown when a [PopulationFactory] tries to [PopulationFactory.spawn] an
 * instance that does not derive from an Individual class.
 */
class InvalidTournamentSizeException(msg: String): Exception(msg)
