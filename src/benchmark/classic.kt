package benchmark

import kotlin.math.PI
import kotlin.math.cos

fun rastrigin(x: List<Double>): Double {
    val A = 10
    val n = x.size
    return A * n + x.sumByDouble { xi -> xi * xi - A * cos(2 * PI * xi) }
}
