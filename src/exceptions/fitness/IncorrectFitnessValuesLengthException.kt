package exceptions.fitness

import fitness.MultiObjectiveFitness

/**
 * Thrown when the number of values to be inserted into a [MultiObjectiveFitness]
 * is either too long or too short.
 */
class IncorrectFitnessValuesLengthException(msg: String): Exception(msg)
