package fitness

import exceptions.IncorrectFitnessValuesLengthException

class MultiObjectiveFitness(val weights: List<Double>) {

    var valid: Boolean = false

    private val _values: MutableList<Double> = MutableList(weights.size) { Double.NEGATIVE_INFINITY }
    var values: List<Double>
        get() = _values.toList()
        set(value) {
            if (weights.size != value.size)
                throw IncorrectFitnessValuesLengthException("weight.size=${weights.size} != input.size=${value.size}")

            for (i in 0..weights.lastIndex)
                _values[i] = value[i]

            valid = true
        }


    fun weightedValues(): List<Double> = weights.zip(_values, Double::times)

    fun weightedValue(): Double {
        var sum = 0.0
        for (i in 0..weights.lastIndex)
            sum += weights[i] * _values[i]
        return sum
    }

    fun invalidate() { valid = false }

}
