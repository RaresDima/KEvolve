package fitness

import java.lang.IllegalArgumentException
import kotlin.math.sign

/**
 * Base Fitness class that exposes functionalities all Fitness classes should
 * have.
 *
 * @property valid
 *  Whether this Fitness object contains a valid fitness value or the
 *  individual that owns this Fitness needs to be evaluated again.
 */
abstract class BaseFitness {
    var valid: Boolean = false

    /**
     * Invalidates this Fitness object, requiring the corresponding individual to be
     * evaluated again.
     */
    fun invalidate() { valid = false }

    /**
     * Retrieves the value of this Fitness object. This is a single value.
     * For multi-objective purposes this might be computed using a weighted sum of the
     * objective values.
     */
    abstract fun value(): Double

    /**
     * Checks if the [value] of a Fitness object is  the same as that of [other].
     *
     * @param other The Fitness object to compare this to.
     *
     * @return Whether the 2 Fitness objects have the same value.
     *
     * @throws IllegalArgumentException If [other] is not derived from [BaseFitness].
     */
    override operator fun equals(other: Any?): Boolean =
        if (other is BaseFitness)
            this.value() == other.value()
        else
            throw IllegalArgumentException("Can only compare to another class derived from BaseFitness.")

    /**
     * Compares the [value] of 2 Fitness objects.
     *
     * @param other The Fitness object to compare this to.
     *
     * @return
     *  - 1  if this Fitness is greater than [other].
     *  - -1 if this Fitness is lower than [other].
     *  - 0 if this and [other] are equal.
     *
     *  @throws IllegalArgumentException If [other] is not derived from [BaseFitness].
     */
    operator fun compareTo(other: Any?): Int =
        if (other is BaseFitness)
            this.value().minus(other.value())
                .let { if (it.isNaN()) 0.0 else it }
                .sign.toInt()
        else
            throw IllegalArgumentException("Can only compare to another class derived from BaseFitness.")

    /**
     * Returns a copy of this Fitness object.
     *
     * @return A copy of this fitness object.
     */
    abstract fun copy(): BaseFitness
}
