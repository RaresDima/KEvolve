package fitness

import exceptions.IncorrectFitnessValuesLengthException

class Fitness {
    var valid: Boolean = false
    var value: Double = Double.NEGATIVE_INFINITY
        set(value) {
            field = value
            valid = true
        }

    fun invalidate() { valid = false }
}
