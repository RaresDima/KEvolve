package utils.extensions

import utils.Bit

/**
 * The Hamming distance between 2 [List]s of [Bit]s.
 *
 * @receiver A [List] of [Bit]s.
 * @param other A [List] of [Bit]s.
 *
 * @return The Hamming distance between [this] and [other].
 */
infix fun List<Bit>.hammingDistance(other: List<Bit>): Int =
    this.zip(other).filter { (b1, b2) -> b1 != b2 }.size

/**
 * Remove the elements in a certain range and return the removed elements.
 *
 * @receiver A [MutableList].
 * @param range An [IntRange].
 *
 * @return A [MutableList] with the removed elements.
 */
fun <T> MutableList<T>.remove(range: IntRange): MutableList<T> {
    val removed = this.slice(range).toMutableList()
    for (i in range)
        removeAt(range.first)
    return removed
}
