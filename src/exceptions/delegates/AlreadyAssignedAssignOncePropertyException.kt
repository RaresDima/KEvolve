package exceptions.delegates

import extensions.delegates.AssignOnce

/**
 * Thrown when a value is assigned to an [AssignOnce] property that already has a
 * value.
 */
class AlreadyAssignedAssignOncePropertyException(msg: String): Exception(msg)
