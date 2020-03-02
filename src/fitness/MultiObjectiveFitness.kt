package fitness

import exceptions.fitness.IncorrectFitnessValuesLengthException
import exceptions.fitness.EmptyWeightsException

/**
 * Multi-objective fitness.
 *
 * @property weights The weights of each objective value.
 *
 * @property values The objective values.
 * @throws IncorrectFitnessValuesLengthException
 *  On assignment if the size of the objective values is different from the size of
 *  [weights].
 *
 * @constructor
 *  Sets the [weights] (passed as a [List] parameter) for this
 *  [MultiObjectiveFitness].
 *
 * @param weights The weights of each objective value.
 *
 * @throws EmptyWeightsException If [weights] is empty.
 */
class MultiObjectiveFitness(val weights: List<Double>): BaseFitness() {

    init {
        if (weights.isEmpty())
            throw EmptyWeightsException("weights.size = ${weights.size} ")
    }

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

    /**
     * Retrieves the weighted objective values.
     * I.e. Each objective value multiplied by its weight.
     *
     * @return The weighted objective values
     */
    fun weightedValues(): List<Double> = weights.zip(_values, Double::times)

    /**
     * Retrieves the sum of the weighted objective values.
     *
     * @return The sum of the weighted objective values
     */
    fun weightedValue(): Double {
        var sum = 0.0
        for (i in 0..weights.lastIndex)
            sum += weights[i] * _values[i]
        return sum
    }

    /**
     * Retrieves the value of this Fitness object. This is a single value.
     * For multi-objective purposes this is computed using the weighted sum of the
     * objective values.
     */
    override fun value(): Double = weightedValue()

    override fun copy(): MultiObjectiveFitness =
        MultiObjectiveFitness(weights).also {
            it.values  = this._values
            it.valid = this.valid
        }
}
