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
    }

    @ParameterizedTest
    @MethodSource("bitStringPairArgsProvider")
    fun toByte(bitString1: MutableList<Bit>, bitString2: MutableList<Bit>, expected: Int) =
        assertEquals(bitString1 hammingDistance bitString2, expected)

}
