package fitness

/**
 * Single-objective fitness.
 *
 * @property value The objective value.
 */
class Fitness: BaseFitness() {
    var value: Double = Double.NEGATIVE_INFINITY
        set(value) {
            field = value
            valid = true
        }

    override fun value(): Double = value
}
