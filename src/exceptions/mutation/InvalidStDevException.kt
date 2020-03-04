package exceptions.mutation

import mutation.MutateGaussianReplace

/**
 * Thrown when a [MutateGaussianReplace] has std <= 0.
 */
class InvalidStDevException(msg: String): Exception(msg)
