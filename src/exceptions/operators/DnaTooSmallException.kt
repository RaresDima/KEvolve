package exceptions.operators

/**
 * Thrown when a DNA is too small to have an operation performed on it.
 */
class DnaTooSmallException(msg: String): Exception(msg)
