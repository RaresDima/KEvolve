package exceptions.selection

import selection.BaseSelection

/**
 * Thrown when a [BaseSelection] tries to select less than 1 individual.
 */
class SelectionTooSmallException(msg: String): Exception(msg)
