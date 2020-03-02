package exceptions.fitness

import fitness.MultiObjectiveFitness

/**
 * Thrown when the list of weights for a [MultiObjectiveFitness] is empty.
 */
class EmptyWeightsException(msg: String): Exception(msg)
