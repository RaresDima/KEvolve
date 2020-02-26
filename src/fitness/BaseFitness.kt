package fitness

import java.lang.IllegalArgumentException
import kotlin.math.sign

abstract class BaseFitness {
    var valid: Boolean = false

    fun invalidate() { valid = false }

    abstract fun value(): Double

    override operator fun equals(other: Any?): Boolean =
        if (other is BaseFitness)
            this.value() == other.value()
        else
            throw IllegalArgumentException("Can only compare to another class derived from BaseFitness.")

    operator fun compareTo(other: Any?): Int =
        if (other is BaseFitness)
            this.value().minus(other.value()).sign.toInt()
        else
            throw IllegalArgumentException("Can only compare to another class derived from BaseFitness.")

}
