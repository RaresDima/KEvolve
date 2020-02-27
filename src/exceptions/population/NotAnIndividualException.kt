package exceptions.population

import population.PopulationFactory

/**
 * Thrown when a [PopulationFactory] tries to [PopulationFactory.spawn] an
 * instance that does not derive from an Individual class.
 */
class NotAnIndividualException(msg: String): Exception(msg)
