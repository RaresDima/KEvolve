package fitness

abstract class BaseFitness {
    var valid: Boolean = false

    fun invalidate() { valid = false }

    abstract fun value(): Double
}
