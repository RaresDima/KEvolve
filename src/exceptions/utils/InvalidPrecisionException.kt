package exceptions.utils

import utils.BinaryRepresentation

/**
 * Thrown when a [BinaryRepresentation] is created with digits < 0.
 */
class InvalidPrecisionException(msg: String): Exception(msg)
