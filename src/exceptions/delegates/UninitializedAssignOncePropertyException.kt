package exceptions.delegates

import extensions.delegates.AssignOnce

/**
 * Thrown when an [AssignOnce] property is accessed before being assigned its
 * value.
 */
class UninitializedAssignOncePropertyException(msg: String): Exception(msg)
