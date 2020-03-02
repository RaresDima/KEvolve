package extensions

import kotlin.random.Random

fun IntRange.randomSequence(random: Random = Random.Default): Sequence<Int> = sequence {
    while(true) {
        yield(this@randomSequence.random(random))
    }
}
