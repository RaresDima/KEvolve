package fitness

class Fitness: BaseFitness() {
    var value: Double = Double.NEGATIVE_INFINITY
        set(value) {
            field = value
            valid = true
        }

    override fun value(): Double = value
}
