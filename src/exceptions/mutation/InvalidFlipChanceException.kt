package exceptions.mutation

import mutation.MutateFlipBits

/**
 * Thrown when a [MutateFlipBits] has bitPb outside of [0, 1].
 */
class InvalidFlipChanceException(msg: String): Exception(msg)
