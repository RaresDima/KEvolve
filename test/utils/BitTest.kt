package utils

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import utils.Bit
import java.util.stream.Stream
import kotlin.test.assertEquals

internal class BitTest {

    companion object {

        @JvmStatic
        fun ctorNumberArgsProvider(): Stream<Arguments> = Stream.of(

            Arguments.of(-1, 1),
            Arguments.of( 0, 0),
            Arguments.of( 1, 1),
            Arguments.of( 2, 1),

            Arguments.of(-0.5f, 1),
            Arguments.of(   0f, 0),
            Arguments.of( 0.5f, 1),
            Arguments.of(   1f, 1),
            Arguments.of( 1.5f, 1)

        )

        @JvmStatic
        fun ctorBoolArgsProvider(): Stream<Arguments> = Stream.of(
            Arguments.of(true,  1),
            Arguments.of(false, 0)
        )


        @JvmStatic
        fun gtCompareArgsProvider(): Stream<Arguments> = Stream.of(
            Arguments.of(0, 0, false),
            Arguments.of(0, 1, false),
            Arguments.of(1, 0, true),
            Arguments.of(1, 1, false)
        )

        @JvmStatic
        fun geCompareArgsProvider(): Stream<Arguments> = Stream.of(
            Arguments.of(0, 0, true),
            Arguments.of(0, 1, false),
            Arguments.of(1, 0, true),
            Arguments.of(1, 1, true)
        )


        @JvmStatic
        fun bitAndArgsProvider(): Stream<Arguments> = Stream.of(
            Arguments.of(0, 0, false),
            Arguments.of(0, 1, false),
            Arguments.of(1, 0, false),
            Arguments.of(1, 1, true)
        )

        @JvmStatic
        fun bitOrArgsProvider(): Stream<Arguments> = Stream.of(
            Arguments.of(0, 0, false),
            Arguments.of(0, 1, true),
            Arguments.of(1, 0, true),
            Arguments.of(1, 1, true)
        )

        @JvmStatic
        fun bitXorArgsProvider(): Stream<Arguments> = Stream.of(
            Arguments.of(0, 0, false),
            Arguments.of(0, 1, true),
            Arguments.of(1, 0, true),
            Arguments.of(1, 1, false)
        )

    }


    @ParameterizedTest
    @MethodSource("ctorNumberArgsProvider")
    fun `Bit(Number)`(n: Number, expectedValue: Int) = assertEquals(Bit(n).value, expectedValue)

    @ParameterizedTest
    @MethodSource("ctorBoolArgsProvider")
    fun `Bit(Bool)`(b: Boolean, expectedValue: Int) = assertEquals(Bit(b).value, expectedValue)


    @ParameterizedTest
    @ValueSource(ints = [1, 0])
    fun flip(i: Int) = assertEquals(Bit(i).flip().value, 1 - i)

    @ParameterizedTest
    @ValueSource(ints = [1, 0])
    fun flipCopy(i: Int) = assertEquals(Bit(i).flipCopy().value, 1 - i)


    @ParameterizedTest
    @ValueSource(ints = [1, 0])
    fun copy(i: Int) = assertEquals(Bit(i).copy().value, i)


    @ParameterizedTest
    @ValueSource(ints = [1, 0])
    fun toString(i: Int) = assertEquals(Bit(i).toString(), "b$i")


    @ParameterizedTest
    @ValueSource(ints = [1, 0])
    fun `==`(i: Int) = assert(Bit(i) == Bit(i))

    @ParameterizedTest
    @ValueSource(ints = [1, 0])
    fun `!=`(i: Int) = assert(Bit(i) != Bit(1 - i))


    @ParameterizedTest
    @MethodSource("gtCompareArgsProvider")
    fun `greater than`(i1: Int, i2: Int, expected: Boolean) = assertEquals(Bit(i1) > Bit(i2), expected)

    @ParameterizedTest
    @MethodSource("geCompareArgsProvider")
    fun `greater or equal to`(i1: Int, i2: Int, expected: Boolean) = assertEquals(Bit(i1) >= Bit(i2), expected)


    @ParameterizedTest
    @MethodSource("geCompareArgsProvider")
    fun `lower than`(i1: Int, i2: Int, expected: Boolean) = assertEquals(Bit(i1) < Bit(i2), !expected)

    @ParameterizedTest
    @MethodSource("gtCompareArgsProvider")
    fun `lower or equal to`(i1: Int, i2: Int, expected: Boolean) = assertEquals(Bit(i1) <= Bit(i2), !expected)


    @ParameterizedTest
    @MethodSource("bitAndArgsProvider")
    fun `Bit and Bit`(i1: Int, i2: Int, expected: Boolean) = assertEquals((Bit(i1) and Bit(i2)).toBoolean(), expected)

    @ParameterizedTest
    @MethodSource("bitOrArgsProvider")
    fun `Bit or Bit`(i1: Int, i2: Int, expected: Boolean) = assertEquals((Bit(i1) or Bit(i2)).toBoolean(), expected)

    @ParameterizedTest
    @MethodSource("bitXorArgsProvider")
    fun `Bit xor Bit`(i1: Int, i2: Int, expected: Boolean) = assertEquals((Bit(i1) xor Bit(i2)).toBoolean(), expected)

    @ParameterizedTest
    @ValueSource(ints = [1, 0])
    fun `!Bit`(i: Int) = assertEquals(!Bit(i), Bit(i).flip())

}