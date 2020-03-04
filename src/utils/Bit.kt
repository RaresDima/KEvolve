package utils

import utils.extensions.toInt
import kotlin.random.Random

/**
 * Represents a bit.
 *
 * @property value And [Int] with the value of the bit (0 or 1).
 *
 * @constructor Constructs a [Bit] from a [Number].
 * @param value
 *  A [Number] ([Int], [Long], [Float], etc.). The bit is 0 if [value] is 0, else
 *  it is 1.
 */
class Bit(value: Number): Comparable<Bit> {

    /**
     * Constructs a [Bit] from a [Boolean].
     *
     * @param value A [Boolean] representing the value of the bit.
     */
    constructor(value: Boolean): this(value.toInt())

    var value: Int = (value.toDouble() != 0.0).toInt()

    companion object {

        /**
         * Create a [Bit] with a random value.
         *
         * @return A [Bit] with a random value.
         */
        fun random() = Bit(Random.nextBoolean())
    }

    /**
     * Flip the [Bit] in place.
     *
     * @return This [Bit].
     */
    fun flip(): Bit { value = 1 - value ; return this }

    /**
     * Create another [Bit] with the value of this [Bit] flipped.
     *
     * @return This [Bit].
     */
    fun flipCopy(): Bit = Bit(1 - value)

    /**
     * Copy this [Bit].
     *
     * @return A copy of this [Bit].
     */
    fun copy(): Bit = Bit(value)

    /**
     * Convert to a [Boolean] value.
     *
     * @return The [Boolean] value of this [Bit].
     */
    fun toBoolean(): Boolean = value != 0

    override fun toString(): String = "b$value"

    override fun equals(other: Any?): Boolean = if (other is Bit) value == other.value else false
    override fun compareTo(other: Bit): Int = value - other.value

    /**
     * Bit AND.
     *
     * @return this AND [other]
     */
    infix fun and(other: Bit): Bit = Bit(this.toBoolean() && other.toBoolean())

    /**
     * Bit OR.
     *
     * @return this OR [other]
     */
    infix fun or(other: Bit): Bit = Bit(this.toBoolean() || other.toBoolean())

    /**
     * Bit XOR.
     *
     * @return this XOR [other]
     */
    infix fun xor(other: Bit): Bit = Bit(this.toBoolean().xor(other.toBoolean()))

    /**
     * Bit NOT.
     *
     * @return NOT this
     */
    operator fun not(): Bit = flipCopy()

    override fun hashCode(): Int = value.hashCode()
}