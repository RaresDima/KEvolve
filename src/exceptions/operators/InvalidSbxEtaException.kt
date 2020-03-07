package exceptions.operators

import crossover.CrossoverSimulatedBinary

/**
 * Thrown when a [CrossoverSimulatedBinary] eta is -1.0.
 */
class InvalidSbxEtaException(msg: String): Exception(msg)
