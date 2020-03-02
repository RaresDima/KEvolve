package exceptions.utils

import utils.BinaryRepresentation

/**
 * Thrown when a [BinaryRepresentation] is created with min >= max.
 */
class InvalidDomainBoundsException(msg: String): Exception(msg)
