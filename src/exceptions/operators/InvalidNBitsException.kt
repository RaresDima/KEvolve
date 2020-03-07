package exceptions.operators

import mutation.MutateFlipNBits

/**
 * Thrown when a [MutateFlipNBits] has nBits < 1.
 */
class InvalidNBitsException(msg: String): Exception(msg)
