package benchmark

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
fun rastrigin(x: List<Double>): Double {
    val A = 10
    val n = x.size
    return A * n + x.sumByDouble { xi -> xi * xi - A * cos(2 * PI * xi) }
}
