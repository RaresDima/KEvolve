package exceptions.extensions.delegates

import utils.extensions.delegates.AssignOnce

/**
 * Thrown when a value is assigned to an [AssignOnce] property that already has a
 * value.
 */
class AlreadyAssignedPropertyAssignmentException(msg: String): Exception(msg)
