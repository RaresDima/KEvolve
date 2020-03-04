package utils.extensions

/**
 * Converts the [Boolean] to a [Byte]
 *
 * @return 1 if the [Boolean] is true, else 0.
 */
fun Boolean.toByte():  Byte  = if (this) 1 else 0

/**
 * Converts the [Boolean] to a [Short]
 *
 * @return 1 if the [Boolean] is true, else 0.
 */
fun Boolean.toShort(): Short = if (this) 1 else 0

/**
 * Converts the [Boolean] to an [Int]
 *
 * @return 1 if the [Boolean] is true, else 0.
 */
fun Boolean.toInt():   Int   = if (this) 1 else 0

/**
 * Converts the [Boolean] to a [Long]
 *
 * @return 1 if the [Boolean] is true, else 0
 */
fun Boolean.toLong():  Long  = if (this) 1 else 0

/**
 * Converts the [Boolean] to a [Float]
 *
 * @return 1.0 if the [Boolean] is true, else 0.0
 */
fun Boolean.toFloat():  Float  = if (this) 1f  else 0f

/**
 * Converts the [Boolean] to a [Double]
 *
 * @return 1.0 if the [Boolean] is true, else 0.0
 */
fun Boolean.toDouble(): Double = if (this) 1.0 else 0.0
