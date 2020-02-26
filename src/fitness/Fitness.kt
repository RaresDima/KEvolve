package fitness

import java.lang.IllegalArgumentException
import kotlin.math.sign

class Fitness: BaseFitness() {
    var value: Double = Double.NEGATIVE_INFINITY
        set(value) {
            field = value
            valid = true
        }

    override fun value(): Double = value
}
