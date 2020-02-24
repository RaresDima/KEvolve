import extensions.toInt

class Bit(value: Number) {
    constructor(value: Boolean): this(value.toInt())

    var value: Int = (value.toDouble() != 0.0).toInt()


    fun flip(): Bit { value = 1 - value ; return this }
    fun flipCopy(): Bit = Bit(1 - value)

    fun copy(): Bit = Bit(value)

    fun toBoolean(): Boolean = value != 0
    override fun toString(): String = "b$value"

    override operator fun equals(other: Any?): Boolean = if (other is Bit) value == other.value else false
    operator fun compareTo(bit: Bit): Int = value - bit.value

    infix fun and(other: Bit): Bit = Bit(this.toBoolean() && other.toBoolean())
    infix fun or(other: Bit): Bit = Bit(this.toBoolean() || other.toBoolean())
    infix fun xor(other: Bit): Bit = Bit(this.toBoolean().xor(other.toBoolean()))
    operator fun not(): Bit = flipCopy()

    override fun hashCode(): Int = value.hashCode()
}