import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream
import kotlin.test.assertEquals

internal class BitStringTest {

    companion object {

        const val RANDOM_TEST_COUNT = 1000

        @JvmStatic
        fun ctorBitArgsProvider(): Stream<Arguments> {

            val bitValues = (1..RANDOM_TEST_COUNT).map { listOf(0, 1).random() }
            val bits = bitValues.map(::Bit)

            val arguments =
                bits.zip(bitValues)
                    .map { (bit, bitValue) -> Arguments.of(bit, bitValue) }
                    .toTypedArray()

            return Stream.of(*arguments)
        }

        @JvmStatic
        fun ctorBoolArgsProvider(): Stream<Arguments> = Stream.of(
            Arguments.of(true,  1),
            Arguments.of(false, 0)
        )


    }


//    @ParameterizedTest
//    @MethodSource("ctorBitArgsProvider")
//    fun `BitString(List(Bit))`(l: List<Bit>, expectedValues: List<Int>) = assertEquals(, expectedValue)

//    @ParameterizedTest
//    @MethodSource("ctorBoolArgsProvider")
//    fun `Bit(Bool)`(b: Boolean, expectedValue: Int) = assertEquals(Bit(b).value, expectedValue)
//
//
//    @ParameterizedTest
//    @ValueSource(ints = [1, 0])
//    fun flip(i: Int) = assertEquals(Bit(i).flip().value, 1 - i)


}