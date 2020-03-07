package exceptions.operators

import crossover.CrossoverNPoints

/**
 * Thrown when a [CrossoverNPoints] has nCuttingPoints < 1.
 */
class InvalidNCuttingPointsException(msg: String): Exception(msg)
