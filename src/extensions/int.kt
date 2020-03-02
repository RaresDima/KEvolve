package extensions

import kotlin.random.Random

/**
 * Returns a [Sequence] of [Int]s from [this] range.
 *
 * @param random The [Random] instance to use (optional).
 *
 * @return A [Sequence] of [Int]s from [this] range.
 */
fun IntRange.randomSequence(random: Random = Random.Default): Sequence<Int> = sequence {
    while(true) {
        yield(this@randomSequence.random(random))
    }
}
