package utils.extensions

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import utils.Bit
import java.util.stream.Stream

internal class ListKtTest {

    companion object {

        @JvmStatic
        fun bitStringPairArgsProvider(): Stream<Arguments> = Stream.of(

            Arguments.of(
                mutableListOf(Bit(0), Bit(0), Bit(0), Bit(0), Bit(0)),
                mutableListOf(Bit(0), Bit(0), Bit(0), Bit(0), Bit(0)),
                0
            ),

            Arguments.of(
                mutableListOf(Bit(1), Bit(1), Bit(1), Bit(1), Bit(1)),
                mutableListOf(Bit(1), Bit(1), Bit(1), Bit(1), Bit(1)),
                0
            ),

            Arguments.of(
                mutableListOf(Bit(0), Bit(0), Bit(0), Bit(0), Bit(0)),
                mutableListOf(Bit(1), Bit(1), Bit(1), Bit(1), Bit(1)),
                5
            ),

            Arguments.of(
                mutableListOf(Bit(0), Bit(1), Bit(0), Bit(1), Bit(0)),
                mutableListOf(Bit(1), Bit(0), Bit(1), Bit(0), Bit(1)),
                5
            ),

            Arguments.of(
                mutableListOf(Bit(0), Bit(0), Bit(0), Bit(0), Bit(0)),
                mutableListOf(Bit(1), Bit(1), Bit(1), Bit(1), Bit(0)),
                4
            ),

            Arguments.of(
                mutableListOf(Bit(1), Bit(0), Bit(1), Bit(0), Bit(0)),
                mutableListOf(Bit(1), Bit(1), Bit(1), Bit(0), Bit(1)),
                2
            ),

            Arguments.of(
                mutableListOf(Bit(1), Bit(1), Bit(1), Bit(1), Bit(1)),
                mutableListOf(Bit(0), Bit(1), Bit(1), Bit(1), Bit(1)),
                1
            )
        )

        @JvmStatic
        fun intListArgsProvider(): Stream<Arguments> = Stream.of(
            Arguments.of(MutableList(10) { it }, 2..5),
            Arguments.of(MutableList(100) { it }, 3 until 77),
            Arguments.of(MutableList(1000) { it }, 666..900),
            Arguments.of(MutableList(10000) { it }, 9990..9995)
        )
    }

    @ParameterizedTest
    @MethodSource("bitStringPairArgsProvider")
    fun `hamming distance`(bitString1: MutableList<Bit>, bitString2: MutableList<Bit>, expected: Int) =
        assertEquals(bitString1 hammingDistance bitString2, expected)


    @ParameterizedTest
    @MethodSource("intListArgsProvider")
    fun `remove slice removes correctly`(lst: MutableList<Int>, range: IntRange) {
        val initSet = (0..lst.lastIndex).toSet()
        val removedSet = range.toSet()
        lst.remove(range)
        assertEquals(lst.toSet(), initSet - removedSet)
    }

    @ParameterizedTest
    @MethodSource("intListArgsProvider")
    fun `remove slice returns removed slice`(lst: MutableList<Int>, range: IntRange) {
        val setToRemove = range.toSet()
        val removedSet = lst.remove(range).toSet()
        assertEquals(setToRemove, removedSet)
    }

}
