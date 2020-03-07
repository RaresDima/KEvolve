package exceptions.operators

/**
 * Thrown when a probability is outside of [0, 1].
 */
class InvalidProbabilityException(msg: String): Exception(msg)
