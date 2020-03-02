package benchmark.continuous.singleobjective

import kotlin.math.PI
import kotlin.math.cos

/**
 * The Rastrigin function.
 *
 * This is a classic multimodal function used in optimization tasks.
 * R(x) = A n + SUM( xi^2 - A cos(2 pi xi) )
 * With A = 10 and n being the size of the input.
 *
 * @param x The function arguments.
 *
 * @return The value of the Rastrigin function R(x).
 */
class Rastrigin {
    companion object {
        const val A = 10
    }

    /**
     * The Rastrigin function.
     *
     * This is a classic multimodal function used in optimization tasks.
     * R(x) = A n + SUM( xi^2 - A cos(2 pi xi) )
     * With A = 10 and n being the size of the input.
     *
     * @param x The function arguments.
     *
     * @return The value of the Rastrigin function R(x).
     */
    operator fun invoke(x: List<Double>): Double {
        val n = x.size
        return A * n + x.sumByDouble { xi -> xi * xi - A * cos(2 * PI * xi) }
    }
}
