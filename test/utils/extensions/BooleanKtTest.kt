package utils.extensions

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class BooleanKtTest {

    companion object {
        @JvmStatic
        fun boolArgsProvider(): Stream<Arguments> = Stream.of(
            Arguments.of(true,  1),
            Arguments.of(false, 0)
        )
    }

    @ParameterizedTest
    @MethodSource("boolArgsProvider")
    fun toByte(bool: Boolean, expected: Int) = assertEquals(bool.toByte(), expected.toByte())

    @ParameterizedTest
    @MethodSource("boolArgsProvider")
    fun toShort(bool: Boolean, expected: Int) = assertEquals(bool.toShort(), expected.toShort())

    @ParameterizedTest
    @MethodSource("boolArgsProvider")
    fun toInt(bool: Boolean, expected: Int) = assertEquals(bool.toInt(), expected)

    @ParameterizedTest
    @MethodSource("boolArgsProvider")
    fun toLong(bool: Boolean, expected: Int) = assertEquals(bool.toLong(), expected.toLong())

    @ParameterizedTest
    @MethodSource("boolArgsProvider")
    fun toFloat(bool: Boolean, expected: Int) = assertEquals(bool.toFloat(), expected.toFloat())

    @ParameterizedTest
    @MethodSource("boolArgsProvider")
    fun toDouble(bool: Boolean, expected: Int) = assertEquals(bool.toDouble(), expected.toDouble())

}
