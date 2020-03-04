package utils

infix fun List<Bit>.hammingDistance(other: List<Bit>): Int =
    this.zip(other).filter { (b1, b2) -> b1 != b2 }.size
